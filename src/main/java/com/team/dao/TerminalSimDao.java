package com.team.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team.model.TerminalSim;

/**
 * 终端对应的卡的相关操作	m_terminal_sim
 * 创建日期：2017-12-18下午3:38:21
 * author:wuzhiheng
 */
public interface TerminalSimDao {
	
	/**
	 * 通过终端tsid找到对应的SIM卡
	 *@param tsid
	 *@return
	 *return
	 */
	public List<TerminalSim> getTerminalSimByTsid(@Param("tsid")Integer tsid);
	
}
