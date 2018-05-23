package com.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team.util.Cache;
import com.team.util.CacheFactory;
import com.team.util.MConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.FlowMonthDao;
import com.team.dao.SimCardDao;
import com.team.dao.SimPackageDao;
import com.team.model.SimPackage;
import com.team.service.SimPackageService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 卡套餐的相关操作	m_simpackage
 * 创建日期：2017-12-23下午6:07:26
 * author:wuzhiheng
 */
@Transactional
@Service
public class SimPackageServiceImpl extends BaseService implements SimPackageService{
	
	@Autowired
	private SimPackageDao simPackageDao;
	@Autowired
	private SimCardDao simCardDao;
	@Autowired
	private FlowMonthDao flowMonthDao;

	//卡缓存
	private static final Cache simCache = CacheFactory.getCache(MConstant.MEM_SIM);


	@Override
	/**
	 * 查找卡套餐信息
	 */
	public ResultList getPackageList(Integer dId,Integer status, String name, int page, int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("status", status);
		map.put("dId", CommonUtil.changeDepartmentId(dId));
		List<SimPackage> list = simPackageDao.getPackageList(map);
		PageInfo<SimPackage> pageInfo = new PageInfo<>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	@Override
	/**
	 * 单条删除卡套餐
	 */
	public ReturnMsg deletePackage(Integer id) {
		ReturnMsg returnMsg;
		if(!CommonUtil.StringIsNull(simCardDao.getPackageExist(id))){
			returnMsg = super.errorTip();
			returnMsg.setMsg("删除失败，有SIM卡正在使用该套餐！");
		}else{
			int count = simPackageDao.deletePackage(id);
			if(count>0){
				//删除缓存
				simCache.remove(MConstant.CACHE_PACKAGE_KEY_PREF + id);
			}
			returnMsg = super.successTip();
		}
		return returnMsg;
	}

	@Override
	public ReturnMsg savePackage(SimPackage simPackage) {
		int count = 0;
		if(simPackage.getId()!=null){
			SimPackage origin = simPackageDao.getPackage(simPackage.getId());

			count = simPackageDao.updatePackage(simPackage);
			if(!origin.getMaxFlow().equals(simPackage.getMaxFlow()) || !origin.getMaxRoamFlow().equals(simPackage.getMaxRoamFlow())) {
				//需要更新流量大小
				flowMonthDao.updateMonthFlowByPackage(simPackage);
			}
		}else{
			count = simPackageDao.insertPackage(simPackage);
		}
		if(count>0){
			//更新缓存
			simCache.set(MConstant.CACHE_PACKAGE_KEY_PREF + simPackage.getId(),
					CommonUtil.convertBean(simPackage, com.hqrh.rw.common.model.SimPackage.class));

			return super.successTip();
		}
		return super.errorTip();
	}

}
