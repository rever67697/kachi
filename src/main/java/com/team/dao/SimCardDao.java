package com.team.dao;

import java.util.List;

import com.team.model.SimCard;

/**
 * 卡表的相关操作	m_simcard
 * 创建日期：2017-12-18下午3:38:21
 * author:wuzhiheng
 */
public interface SimCardDao {
	
	/**
	 * 根据卡池id查找其下面的卡，为了在页面显示什么位置有卡，卡的状态是什么
	 *@param cpId
	 *@return
	 *return
	 */
	public List<SimCard> getSimCardByPool(int cpId);
	
}
