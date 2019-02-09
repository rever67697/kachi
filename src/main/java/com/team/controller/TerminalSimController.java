package com.team.controller;

import javax.servlet.http.HttpServletRequest;

import com.team.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.annotation.PermissionLog;
import com.team.service.TerminalSimService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-28上午9:31:08
 * author:wuzhiheng
 */
@RestController
@PermissionLog("在线终端管理")
@RequestMapping("/terminalSim")
public class TerminalSimController {
	
	@Autowired
	private TerminalSimService terminalSimService;
	@Autowired
	private TerminalService terminalService;

	@PostMapping("/list")
	public ResultList list(Integer departmentId,Integer tsid,Long imsi,int page,int rows,HttpServletRequest request){
		Integer dId = CommonUtil.getUser(request).getDepartmentId();
		return terminalSimService.list(departmentId,dId,tsid, imsi, page, rows);
	}

	/**
	 * 终端换卡
	 */
	@PostMapping("/changeCard")
	@PermissionLog
	public ReturnMsg changeCard(Integer tsid){
		return terminalSimService.changeCard(tsid);
	}

	/**
	 * 终端下线
	 */
	@PostMapping("/offline")
	@PermissionLog()
	public ReturnMsg offline(Integer tsid){
		return terminalService.offline(tsid);
	}
	
}
