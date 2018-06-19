package com.team.service;




import java.util.Date;
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
	
	public ResultList getTerminalList(Integer departmentId, Integer dId, Integer tsid, Integer status,
									  Integer activated, Date startDate, Date endDate,int page, int rows);

	public ResultList getSelectedList(String terminalList);

	public ReturnMsg deleteTerminalByIds(String ids);
	
	public ReturnMsg saveTerminal(Terminal terminal);
	
	public ReturnMsg getTerminalList(MultipartFile file);
	
	public void insertBatch(List<Terminal> list);
	
	public ReturnMsg updateDepartment(String ids,Integer departmentId);

	public ReturnMsg updateStatus(Integer tsid);

	public ReturnMsg updateWiFiPass(Integer tsid);
	
}
