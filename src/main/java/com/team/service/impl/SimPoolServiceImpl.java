package com.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.SimPoolDao;
import com.team.model.ResultList;
import com.team.model.SimPool;
import com.team.service.SimPoolService;
import com.team.util.StringUtil;

/**
 * 创建日期：2017-12-18下午3:40:55
 * author:wuzhiheng
 */
@Service
public class SimPoolServiceImpl implements SimPoolService{

	@Autowired
	private SimPoolDao simPoolDao;
	
	@Override
	/**
	 * 根据代理商id查找其所有的卡池
	 */
	public ResultList getSimPoolByDeparment(String departmentId,String spid,String name,String isActive,int page,int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("departmentId", StringUtil.putInteger(departmentId));
		map.put("spid", StringUtil.putInteger(spid));
		map.put("name", name);
		map.put("isActive", StringUtil.putInteger(isActive));
		List<SimPool> list = simPoolDao.getSimPoolByDeparment(map);
		PageInfo<SimPool> pageInfo = new PageInfo<>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

}
