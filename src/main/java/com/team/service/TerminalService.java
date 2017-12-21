package com.team.service;


import com.team.model.ResultList;

/**
 * 终端的相关操作	m_terminal
 * 创建日期：2017-12-18下午3:40:42
 * author:wuzhiheng
 */
public interface TerminalService {
	
	public ResultList getTerminalByDeparment(String departmentId,String tsid,String status,String activated,int page,int rows);
	
	
}
