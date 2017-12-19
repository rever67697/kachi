package com.team.service;


import com.team.model.ResultList;

/**
 * 卡池表相关操作	m_simpool
 * 创建日期：2017-12-18下午3:40:42
 * author:wuzhiheng
 */
public interface SimPoolService {
	
	public ResultList getSimPoolByDeparment(int departmentId,int page,int rows);
	
}
