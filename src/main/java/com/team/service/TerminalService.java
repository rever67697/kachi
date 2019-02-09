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
	
	ResultList getTerminalList(Integer departmentId, Integer dId, Integer tsid, Integer status,
									  Integer activated, Date startDate, Date endDate,int page, int rows);

	ResultList getSelectedList(String terminalList);

	ReturnMsg deleteTerminalByIds(String ids);
	
	ReturnMsg saveTerminal(Terminal terminal);
	
	ReturnMsg getTerminalList(MultipartFile file);
	
	void insertBatch(List<Terminal> list);
	
	ReturnMsg updateDepartment(String ids,Integer departmentId);

	ReturnMsg offline(Integer tsid);

	ReturnMsg updateWiFiPass(Integer tsid,String wifiPassword,String ssid);

	ReturnMsg updateSSID(Terminal terminal);
	
}
