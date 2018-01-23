package com.team.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.aop.PermissionLog;
import com.team.model.ReadyTerminalSim;
import com.team.service.ReadyTerminalSimService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

@RestController
@PermissionLog("指定卡管理")
public class ReadyTernimalSimController {
	
	@Autowired
	private ReadyTerminalSimService ReadyTerminalSimService;

	@PostMapping("/getReadyTerminalSim")
	public ResultList getReadyTerminalSim(Integer tsid, Long imsi, int page,
			int rows) {
		return ReadyTerminalSimService.getReadyTerminalSim(tsid, imsi, page,
				rows);
	}

	@PostMapping("/saveReadyTerminalSim")
	@PermissionLog
	public ReturnMsg saveReadyTerminalSim(Integer tsid, Integer type,
			String args,HttpServletRequest request) {
		Integer userId = CommonUtil.getUser(request).getId();
		return ReadyTerminalSimService.saveReadyTerminalSim(tsid, type, args,userId);
	}

	@PostMapping("/updateReadyTerminalSim")
	public ReturnMsg updateReadyTerminalSim(ReadyTerminalSim readyTerminalSim) {
		return ReadyTerminalSimService.updateReadyTerminalSim(readyTerminalSim);
	}

	@PostMapping("/deleteReadyTerminalSim")
	public ReturnMsg deleteReadyTerminalSim(String ids) {
		return ReadyTerminalSimService.deleteReadyTerminalSim(ids);
	}
}
