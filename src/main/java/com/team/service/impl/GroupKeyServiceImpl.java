package com.team.service.impl;

import com.hqrh.rw.common.model.GroupKey;
import com.team.dao.GroupKeyDao;
import com.team.service.GroupKeyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午2:40 2018/4/10
 */
@Service
public class GroupKeyServiceImpl implements GroupKeyService {

    private Logger logger = Logger.getLogger(GroupKeyServiceImpl.class);

    @Autowired
    private GroupKeyDao groupKeyDao;

    @Override
    public GroupKey getGroupKey(String groupKey) {
        List<GroupKey> groupKeys = groupKeyDao.getGroupKey(groupKey);

        logger.debug("getGropKey,key: " + groupKey + "/ " + groupKeys);
        if(groupKeys == null || groupKeys.size() == 0) {
            return null;
        }
        return groupKeys.get(0);
    }

    @Override
    public void addGroupKey(GroupKey groupKey) {
        groupKeyDao.addGroupKey(groupKey);
    }
}
