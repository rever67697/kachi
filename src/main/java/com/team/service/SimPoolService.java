package com.team.service;


import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 卡池表相关操作	m_simpool
 * 创建日期：2017-12-18下午3:40:42
 * author:wuzhiheng
 */
public interface SimPoolService {
	
	public ResultList getSimPoolByDeparment(String departmentId,String spid,String name,String isActive,int page,int rows);
	
	public ReturnMsg getOutlineInfo(Integer departmentId);
	
}
