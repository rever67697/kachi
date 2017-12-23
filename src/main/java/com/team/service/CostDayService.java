package com.team.service;

import com.team.vo.ResultList;


/**
 * 终端的日消费的相关操作	m_cost_day
 * 创建日期：2017-12-18下午3:40:42
 * author:wuzhiheng
 */
public interface CostDayService {
	
	public ResultList getCostDayByTsid(Integer tsid,int page,int rows);
	
}
