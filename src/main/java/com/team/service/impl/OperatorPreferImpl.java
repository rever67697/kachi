package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.OperatorPreferDao;
import com.team.model.OperatorPrefer;
import com.team.service.OperatorPreferService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午2:26 2018/4/23
 */
@Service
@Transactional
public class OperatorPreferImpl extends BaseService implements OperatorPreferService {

    @Autowired
    private OperatorPreferDao operatorPreferDao;

    @Override
    public ResultList list(int page,int rows) {
        PageHelper.startPage(page, rows);
        List<OperatorPrefer> list = operatorPreferDao.list();
        PageInfo<OperatorPrefer> pageInfo = new PageInfo<OperatorPrefer>(list);
        return new ResultList(pageInfo.getTotal(), list);
    }

    @Override
    public ReturnMsg saveOrUpdate(OperatorPrefer operatorPrefer) {
        int count = 0;
        if(operatorPrefer.getId()!=null){
            count = operatorPreferDao.update(operatorPrefer);
        }else {
            count = operatorPreferDao.insert(operatorPrefer);
        }

        return count >0 ?super.successTip():super.errorTip();
    }

    @Override
    public ReturnMsg delete(String ids) {
        int count = 0;

        List<Integer> idList = CommonUtil.getIntList(ids);

        count = operatorPreferDao.delete(idList);

        return count >0 ?super.successTip():super.errorTip();
    }
}
