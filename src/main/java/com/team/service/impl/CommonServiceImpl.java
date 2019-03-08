package com.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hqrh.rw.common.model.GroupCacheSim;
import com.schooner.MemCached.MemcachedItem;
import com.team.model.SimGroup;
import com.team.service.SimGroupService;
import com.team.util.Cache;
import com.team.util.CacheFactory;
import com.team.util.MConstant;
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
    @Autowired
    private SimGroupService simGroupService;

    private static final Cache simCache = CacheFactory.getCache(MConstant.MEM_SIM);
    private static final Cache simGroupCache = CacheFactory.getCache(MConstant.MEM_SIM_GROUP);


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
    public ReturnMsg getDepartmentDic() {
        ReturnMsg returnMsg = super.successTip();
        List<Dictionary> list = commonDao.getDepartmentDic(CommonUtil.getDId());
        if (list != null && list.size() > 0) {
            returnMsg.setData(list);
        }
        return returnMsg;
    }

    @Override
    /**
     * 查找运营商
     */
    public ReturnMsg getOperatorDic(Integer countryCode,Integer mcc) {
        ReturnMsg returnMsg = super.successTip();
        Map<String,Object> map = new HashMap<>();
        map.put("countryCode",countryCode);
        map.put("mcc",mcc);
        List<Dictionary> list = commonDao.getOperatorDic(map);
        if (list != null && list.size() > 0) {
            returnMsg.setData(list);
        }
        return returnMsg;
    }

    @Override
    public ReturnMsg getSimPoolDic() {
        ReturnMsg returnMsg = super.successTip();
        List<Dictionary> list = commonDao.getSimPoolDic(CommonUtil.getDId());
        if (list != null && list.size() > 0) {
            returnMsg.setData(list);
        }
        return returnMsg;
    }

    @Override
    public ReturnMsg getPackageDic(Integer operatorCode) {
        ReturnMsg returnMsg = super.successTip();
        Map<String,Object> map = new HashMap<>();
        map.put("dId",CommonUtil.getDId());
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

    @Override
    public ReturnMsg getMccDic() {
        ReturnMsg returnMsg = super.successTip();
        List<Dictionary> list = commonDao.getMccDic();
        if (list != null && list.size() > 0) {
            returnMsg.setData(list);
        }
        return returnMsg;
    }

    @Override
    public Object q(Long imsi) {

        SimGroup simGroup = simGroupService.getSimGroup(imsi);
        if(simGroup!=null){
            Map<String,Object> map = new HashMap<>();
            map.put("卡组缓存：","");
            String groupKey = simGroup.getGroupKey();
            MemcachedItem m = simCache.gets(groupKey);
            if(m!=null){
                List<GroupCacheSim> list = (List<GroupCacheSim>) m.getValue();
                if(CommonUtil.listNotBlank(list)){
                    for (int i=0;i<list.size();i++) {
                        GroupCacheSim gcs = CommonUtil.convertBean(list.get(i),GroupCacheSim.class);
                        if((Long.valueOf(gcs.getImsi()).toString().equals(imsi.toString()))){
                            map.put("卡组缓存：",gcs);
                            break;
                        }
                    }
                }
            }

            map.put("卡缓存：",simGroupCache.get("SIM_"+imsi));

            return successTip(map);
        }

        return successTip("没有卡信息");
    }

}
