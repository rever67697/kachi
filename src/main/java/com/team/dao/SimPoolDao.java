package com.team.dao;

import java.util.List;
import java.util.Map;

import com.team.model.SimPool;

/**
 * 卡池表的相关操作	m_simpool
 * 创建日期：2017-12-18下午3:38:21
 * author:wuzhiheng
 */
public interface SimPoolDao {
	
	/**
	 * 根据代理商id查找其所有的卡池
	 *@param departmentId
	 *@return
	 *return
	 */
	public List<SimPool> getSimPoolByDeparment(Map<String, Object> map);
	
}
