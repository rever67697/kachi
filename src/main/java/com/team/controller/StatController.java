package com.team.controller;

import com.team.annotation.PermissionLog;
import com.team.model.ChannelCard;
import com.team.service.ChannelCardService;
import com.team.service.StatService;
import com.team.service.TerminalChargeService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import com.team.vo.stat.StatBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Wzq 2017年12月21日 下午8:33:47
 * 
 */
@Controller
@PermissionLog("报表统计")
@RequestMapping("/stat")
public class StatController {

	@Autowired
	private TerminalChargeService terminalChargeService;
	@Autowired
	private StatService statService;
	

	@PostMapping("/chargeList")
	@ResponseBody
	public ResultList list(Integer tsid,Integer type,Date startDate,Date endDate,int page, int rows) {
		return terminalChargeService.list(tsid,startDate,endDate, page, rows);
	}

	@PostMapping("/chargeCount")
	@ResponseBody
	public ReturnMsg count(Integer tsid,Integer type,Date startDate,Date endDate) {
		return terminalChargeService.count(tsid,startDate,endDate);
	}

	@RequestMapping("/index")
	public String index(Model model){
		Map<String,Object> terminalCountMap = statService.terminalCount();
		Map<String,Object> terminalCostMap = statService.terminalCost();

		StatBean statBean = (StatBean) terminalCountMap.get("statBean");
		statBean = statService.fixInformation(statBean);

		model.addAttribute("terminalCountMap",terminalCountMap);
		model.addAttribute("terminalCostMap",terminalCostMap);
		model.addAttribute("stat",statBean);
		return "stat";
	}


}