package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.OperatorDao;
import com.team.dao.OperatorPreferDao;
import com.team.model.Operator;
import com.team.model.OperatorPrefer;
import com.team.service.OperatorPreferService;
import com.team.util.Cache;
import com.team.util.CacheFactory;
import com.team.util.CommonUtil;
import com.team.util.MConstant;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午2:26 2018/4/23
 */
@Service
@Transactional
public class OperatorPreferImpl extends BaseService implements OperatorPreferService {

    private static final Cache publicCache = CacheFactory.getCache(MConstant.MEM_PUBLIC);

    @Autowired
    private OperatorPreferDao operatorPreferDao;
    @Autowired
    private OperatorDao operatorDao;

    @Override
    public ResultList list(Integer countryCode,Integer operatorCode,int page,int rows) {
        PageHelper.startPage(page, rows);
        Map<String,Object> map = new HashMap<>();
        map.put("countryCode",countryCode);
        map.put("operatorCode",operatorCode);
        List<OperatorPrefer> list = operatorPreferDao.list(map);
        PageInfo<OperatorPrefer> pageInfo = new PageInfo<OperatorPrefer>(list);
        return new ResultList(pageInfo.getTotal(), list);
    }

    @Override
    public ReturnMsg saveOrUpdate(OperatorPrefer operatorPrefer) {
        int count = 0;
        //更新operatorPrefer
        if(operatorPrefer.getId()!=null){
            count = operatorPreferDao.update(operatorPrefer);
        }else if(operatorPrefer.getId()==null &&
                ((operatorPrefer.getfPlmn()!=null && !"".equals(operatorPrefer.getfPlmn())) ||
                        (operatorPrefer.getpPlmn()!=null && !"".equals(operatorPrefer.getpPlmn())))){
            count = operatorPreferDao.insert(operatorPrefer);
        }
        //更新operator
        Operator operator = operatorDao.getOperatorById(operatorPrefer.getOperatorId());
        operator.setLevel(operatorPrefer.getLevel()==null?0:operatorPrefer.getLevel());
        count = operatorDao.updateLevel(operator);
        if(count>0){
            //更新运营商的缓存
            publicCache.set(MConstant.CACHE_OPERATOR_KEY_PREE +  operator.getOperatorCode(),
                    CommonUtil.convertBean(operator, com.hqrh.rw.common.model.Operator.class),
                    new Date(1000*60*60*24));
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
