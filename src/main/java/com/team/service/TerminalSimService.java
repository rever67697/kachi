package com.team.service;






import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 终端的相关操作	m_terminal
 * 创建日期：2017-12-18下午3:40:42
 * author:wuzhiheng
 */
public interface TerminalSimService {
	
	public ReturnMsg getTerminalSimByTsid(Integer tsid);
	
	public ResultList getTerminalSimList(Integer tsid,Long imsi,int page,int rows);
	
	public ReturnMsg deleteTerminalByIds(String ids);
}
