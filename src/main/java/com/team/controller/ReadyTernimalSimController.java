package com.team.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.annotation.PermissionLog;
import com.team.model.ReadyTerminalSim;
import com.team.service.ReadyTerminalSimService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

@RestController
@PermissionLog("指定卡管理")
@RequestMapping("/readyTerminalSim")
public class ReadyTernimalSimController {
	
	@Autowired
	private ReadyTerminalSimService ReadyTerminalSimService;

	@PostMapping("/list")
	public ResultList list(Integer tsid, Long imsi, int page,int rows,HttpServletRequest request) {
		Integer dId = CommonUtil.getUser(request).getDepartmentId();
		return ReadyTerminalSimService.list(tsid, imsi, dId,page,rows);
	}

	@PostMapping("/save")
	@PermissionLog
	public ReturnMsg save(Integer tsid, Integer type, String args,HttpServletRequest request) {
		Integer userId = CommonUtil.getUser(request).getId();
		return ReadyTerminalSimService.save(tsid, type, args,userId);
	}

	@PostMapping("/update")
	@PermissionLog
	public ReturnMsg update(ReadyTerminalSim readyTerminalSim,HttpServletRequest request) {
		Integer operator = CommonUtil.getUser(request).getId();
		readyTerminalSim.setOperator(operator);
		return ReadyTerminalSimService.update(readyTerminalSim);
	}

	@PostMapping("/delete")
	@PermissionLog
	public ReturnMsg delete(ReadyTerminalSim readyTerminalSim) {
		return ReadyTerminalSimService.delete(readyTerminalSim);
	}
}
