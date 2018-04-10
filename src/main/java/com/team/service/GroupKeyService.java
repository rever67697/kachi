package com.team.service;

import com.hqrh.rw.common.model.GroupKey;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午2:33 2018/4/10
 */
public interface GroupKeyService {

    GroupKey getGroupKey(String groupKey);

    void addGroupKey(GroupKey groupKey);

}
