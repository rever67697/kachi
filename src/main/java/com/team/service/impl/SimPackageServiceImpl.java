package com.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.team.util.IConstant;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 卡套餐的相关操作	m_simpackage
 * 创建日期：2017-12-23下午6:07:26
 * author:wuzhiheng
 */
@Transactional
@Service
public class SimPackageServiceImpl implements SimPackageService{
	
	@Autowired
	private SimPackageDao simPackageDao;
	@Autowired
	private SimCardDao simCardDao;
	@Autowired
	private FlowMonthDao flowMonthDao;
	
	@Override
	/**
	 * 查找卡套餐信息
	 */
	public ResultList getSimPackage(Integer status, String name, int page, int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("status", status);
		List<SimPackage> list = simPackageDao.getSimPackage(map);
		PageInfo<SimPackage> pageInfo = new PageInfo<>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	@Override
	/**
	 * 单条删除卡套餐
	 */
	public ReturnMsg deletePackage(Integer id) {
		ReturnMsg returnMsg = null;
		if(!CommonUtil.StringIsNull(simCardDao.getPackageExist(id))){
			returnMsg = IConstant.MSG_OPERATE_ERROR;
			returnMsg.setMsg("删除失败，有SIM卡正在使用该套餐！");
		}else{
			simPackageDao.deletePackage(id);
			returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		}
		return returnMsg;
	}

	@Override
	public ReturnMsg savePackage(SimPackage simPackage,Integer compareFlow,Integer compareRoamFlow) {
		int count = 0;
		if(simPackage.getId()!=null){
			count = simPackageDao.updatePackage(simPackage);
			if(!(simPackage.getMaxFlow()).equals(compareFlow)
					|| !(simPackage.getMaxRoamFlow()).equals(compareRoamFlow)){
				//需要更新流量大小
				flowMonthDao.updateMonthFlowByPackage(simPackage);
			}
		}else{
			count = simPackageDao.insertPackage(simPackage);
		}
		if(count > 0){
			return IConstant.MSG_OPERATE_SUCCESS;
		}
		return IConstant.MSG_OPERATE_ERROR;
	}

}
