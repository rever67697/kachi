package com.team.controller;

import com.team.annotation.PermissionLog;
import com.team.model.ChannelCard;
import com.team.service.ChannelCardService;
import com.team.service.TerminalChargeService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author Wzq 2017年12月21日 下午8:33:47
 * 
 */
@RestController
@PermissionLog("报表统计")
@RequestMapping("/stat")
@SuppressWarnings("all")
public class StatController {

	@Autowired
	private TerminalChargeService terminalChargeService;
	

	@PostMapping("/chargeList")
	public ResultList list(Integer tsid,Integer type,Date startDate,Date endDate,int page, int rows) {
		return terminalChargeService.list(tsid,startDate,endDate, page, rows);
	}

	@PostMapping("/chargeCount")
	public ReturnMsg count(Integer tsid,Integer type,Date startDate,Date endDate) {
		return terminalChargeService.count(tsid,startDate,endDate);
	}


}