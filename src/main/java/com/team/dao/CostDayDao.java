package com.team.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team.model.CostDay;

/**
 * 终端的日消费的相关操作	m_cost_day
 * 创建日期：2017-12-18下午3:38:21
 * author:wuzhiheng
 */
public interface CostDayDao {
	
	/**
	 * 通过终端tsid查找它的相关消费记录
	 *@param tsid
	 *@return
	 *return
	 */
	public List<CostDay> getCostDayByTsid(@Param("tsid")Integer tsid);
	
}
