package com.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.dao.CommonDao;
import com.team.service.CommonService;
import com.team.util.CommonUtil;
import com.team.vo.Dictionary;
import com.team.vo.ReturnMsg;

/**
 * 一些公用的功能
 * 创建日期：2017-12-23上午1:55:56
 * author:wuzhiheng
 */
@Service
public class CommonServiceImpl extends BaseService implements CommonService {

    @Autowired
    private CommonDao commonDao;

    @Override
    /**
     * 查找所有的国家代码字典
     */
    public ReturnMsg getCountryDic() {
        ReturnMsg returnMsg = super.successTip();
        List<Dictionary> list = commonDao.getCountryDic();
        if (list != null && list.size() > 0) {
            returnMsg.setData(list);
        }
        return returnMsg;
    }

    @Override
    /**
     * 查找所有的代理商
     */
    public ReturnMsg getDepartmentDic(Integer dId) {
        ReturnMsg returnMsg = super.successTip();
        List<Dictionary> list = commonDao.getDepartmentDic(CommonUtil.changeDepartmentId(dId));
        if (list != null && list.size() > 0) {
            returnMsg.setData(list);
        }
        return returnMsg;
    }

    @Override
    /**
     * 查找运营商
     */
    public ReturnMsg getOperatorDic(Integer countryCode) {
        ReturnMsg returnMsg = super.successTip();
        List<Dictionary> list = commonDao.getOperatorDic(countryCode);
        if (list != null && list.size() > 0) {
            returnMsg.setData(list);
        }
        return returnMsg;
    }

    @Override
    public ReturnMsg getSimPoolDic(Integer dId) {
        ReturnMsg returnMsg = super.successTip();
        List<Dictionary> list = commonDao.getSimPoolDic(CommonUtil.changeDepartmentId(dId));
        if (list != null && list.size() > 0) {
            returnMsg.setData(list);
        }
        return returnMsg;
    }

    @Override
    public ReturnMsg getPackageDic(Integer operatorCode,Integer dId) {
        ReturnMsg returnMsg = super.successTip();
        Map<String,Object> map = new HashMap<>();
        map.put("dId",CommonUtil.changeDepartmentId(dId));
        map.put("operatorCode",operatorCode);
        List<Dictionary> list = commonDao.getPackageDic(map);
        if (list != null && list.size() > 0) {
            returnMsg.setData(list);
        }
        return returnMsg;
    }

    @Override
    public ReturnMsg getProvinceDic(Integer countryCode) {
        ReturnMsg returnMsg = super.successTip();
        List<Dictionary> list = commonDao.getProvinceDic(countryCode);
        if (list != null && list.size() > 0) {
            returnMsg.setData(list);
        }
        return returnMsg;
    }

}
