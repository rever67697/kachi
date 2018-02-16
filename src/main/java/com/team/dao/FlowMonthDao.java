package com.team.dao;

import com.team.model.FlowMonth;
import com.team.model.SimPackage;

import java.util.Map;

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

	/**
	 * 卡参数发生变化，需要重新计算卡的月流量
	 * @param flowMonth
	 * @return
	 */
	public int updateMonthFlowBySimCard(FlowMonth flowMonth);

	/**
	 * 获取当前卡的月流量信息
	 * @param map
	 * @return
	 */
	public FlowMonth get(Map<String,Object> map);
	
}
