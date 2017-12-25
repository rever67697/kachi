package com.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.SimPoolDao;
import com.team.vo.OutlineInfo;
import com.team.vo.ResultList;
import com.team.model.SimPool;
import com.team.service.SimPoolService;
import com.team.util.CommonUtil;

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
		map.put("departmentId", CommonUtil.putInteger(departmentId));
		map.put("spid", CommonUtil.putInteger(spid));
		map.put("name", name);
		map.put("isActive", CommonUtil.putInteger(isActive));
		List<SimPool> list = simPoolDao.getSimPoolByDeparment(map);
		PageInfo<SimPool> pageInfo = new PageInfo<>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	@Override
	/**
	 * 根据代理商查找卡池的总览信息
	 *@param departmentId
	 *@return
	 *return
	 */
	public OutlineInfo getOutlineInfo(Integer departmentId) {
		List<OutlineInfo> list = simPoolDao.getOutlineInfo(departmentId);
		OutlineInfo info = null;
		if(list!=null&&list.size()>0){
			info = list.get(0);
			info.setOfflinePoolCount(info.getSimPoolCount()-info.getOnlinePoolCount());
		}
		return info;
	}

}
