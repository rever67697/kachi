package com.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.service.TerminalService;

/**
 * 创建日期：2017-12-21下午4:56:08
 * author:wuzhiheng
 */
@RestController
public class TerminalController {

	@Autowired
	private TerminalService terminalService;
	
	@PostMapping("/getTerminalByDept")
	public Object getTerminalByDeparment(String status,String tsid,String activated,int page,int rows){
		return terminalService.getTerminalByDeparment(null, tsid, status,activated, page, rows);
	}
	
}
