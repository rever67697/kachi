package com.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.model.ReadyTerminalSim;
import com.team.service.ReadyTerminalSimService;
import com.team.service.SimCardService;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

@RestController
public class ReadyTernimalSimController {
	
	@Autowired
	private ReadyTerminalSimService ReadyTerminalSimService;
	@Autowired
	private SimCardService simCardService;

	@PostMapping("/getReadyTerminalSim")
	public ResultList getReadyTerminalSim(String tsid, String imsi, int page,
			int rows) {
		return ReadyTerminalSimService.getReadyTerminalSim(tsid, imsi, page,
				rows);
	}

	@PostMapping("/saveReadyTerminalSim")
	public ReturnMsg saveReadyTerminalSim(Integer tsid, Integer type,
			String args) {
		return ReadyTerminalSimService.saveReadyTerminalSim(tsid, type, args);
	}

	@PostMapping("/updateReadyTerminalSim")
	public ReturnMsg updateReadyTerminalSim(ReadyTerminalSim readyTerminalSim) {
		return ReadyTerminalSimService.updateReadyTerminalSim(readyTerminalSim);
	}

	@PostMapping("/deleteReadyTerminalSim")
	public ReturnMsg deleteReadyTerminalSim(String ids) {
		return ReadyTerminalSimService.deleteReadyTerminalSim(ids);
	}

	@PostMapping("/getSimCardsInAppointCard")
	public ResultList getSimCard(String number,String cpId,String departmentId,
			int page, int rows) {
		return simCardService.getSimCardInAppointCard(departmentId, cpId, number, page, rows);
	}
}
