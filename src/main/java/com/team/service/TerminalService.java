package com.team.service;



import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import com.team.model.Terminal;

/**
 * 终端的相关操作	m_terminal
 * 创建日期：2017-12-18下午3:40:42
 * author:wuzhiheng
 */
public interface TerminalService {
	
	public ResultList getTerminalByDeparment(String departmentId,String tsid,String status,String activated,int page,int rows);
	
	public int deleteTerminalByIds(String ids);
	
	public int insertTerminal(Terminal terminal);
	
	public int updateTerminalById(Terminal terminal);
	
	public ReturnMsg getTerminalList(MultipartFile file);
	
}
