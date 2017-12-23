package com.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
@Service
public class SimPackageServiceImpl implements SimPackageService{
	
	@Autowired
	private SimPackageDao simPackageDao;
	
	@Override
	/**
	 * 查找卡套餐信息
	 */
	public ResultList getSimPackage(String status, String name, int page, int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("status", CommonUtil.putInteger(status));
		List<SimPackage> list = simPackageDao.getSimPackage(map);
		PageInfo<SimPackage> pageInfo = new PageInfo<>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	@Override
	/**
	 * 单条删除卡套餐
	 */
	public ReturnMsg deletePackage(Integer id) {
		return simPackageDao.deletePackage(id) > 0?IConstant.MSG_OPERATE_SUCCESS:IConstant.MSG_OPERATE_ERROR;
	}

	@Override
	/**
	 * 更新卡套餐信息
	 */
	public int updatePackage(SimPackage simPackage) {
		return simPackageDao.updatePackage(simPackage);
	}

	@Override
	/**
	 * 添加卡套餐
	 */
	public int insertPackage(SimPackage simPackage) {
		simPackage.setId(CommonUtil.getNewId());
		return simPackageDao.insertPackage(simPackage);
	}

}
