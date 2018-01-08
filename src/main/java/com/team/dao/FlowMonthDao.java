package com.team.dao;

import com.team.model.SimPackage;

/**
 * 月流量 m_flow_month
 * 创建日期：2018-1-7下午4:38:21
 * author:wuzhiheng
 */
public interface FlowMonthDao {

	/**
	 * 修改套餐时，如果套餐大小发生变化
	 *@param simPackage
	 *@return
	 *return
	 */
	public int updateMonthFlowByPackage(SimPackage simPackage);
	
}
