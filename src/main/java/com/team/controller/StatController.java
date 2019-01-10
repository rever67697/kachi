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
import java.util.HashMap;
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
	public ResultList list(Integer tsid,Integer departmentId,Date startDate,Date endDate,int page, int rows,HttpServletRequest request) {
		Integer dId = CommonUtil.getUser(request).getDepartmentId();
		return terminalChargeService.list(tsid,startDate,endDate,departmentId,dId, page, rows);
	}

	@RequestMapping("/")
	@ResponseBody
	public Object index(HttpServletRequest request){

		Integer dId = CommonUtil.getUser(request).getDepartmentId();

		Map<String,Object> terminalCountMap = statService.terminalCount(dId);
		Map<String,Object> terminalCostMap = statService.terminalCost(dId);

		StatBean statBean = (StatBean) terminalCountMap.get("statBean");
		statBean = statService.fixInformation(statBean,dId);

		Map<String,Object> map = new HashMap<>();

		map.put("terminalCountMap",terminalCountMap);
		map.put("terminalCostMap",terminalCostMap);
		map.put("stat",statBean);
		return map;
	}


}