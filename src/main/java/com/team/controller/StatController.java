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
public class StatController{

	@Autowired
	private TerminalChargeService terminalChargeService;
	@Autowired
	private StatService statService;


    /**
     * 充值记录列表查询
     * @return
     */
	@PostMapping("/chargeList")
	@ResponseBody
	public ResultList list(Integer tsid,Integer departmentId,Date startDate,Date endDate,int page, int rows) {
		return terminalChargeService.list(tsid,startDate,endDate,departmentId, page, rows);
	}

    /**
     * 首页统计查询
     * @return
     */
	@RequestMapping("/")
	@ResponseBody
	public Object index(){

		//查询终端近7天数据量图表信息
		Map<String,Object> terminalCountMap = statService.terminalCount();
		//查询终端当月消耗排名前十
		Map<String,Object> terminalCostMap = statService.terminalCost();

		StatBean statBean = (StatBean) terminalCountMap.get("statBean");
		//查询页面其他展示参数信息
		statBean = statService.fixInformation(statBean);

		Map<String,Object> map = new HashMap<>();

		map.put("terminalCountMap",terminalCountMap);
		map.put("terminalCostMap",terminalCostMap);
		map.put("stat",statBean);
		return map;
	}

	/**
	 * 首页统计查询
	 * @return
	 */
	@RequestMapping("/statTerminal")
	@ResponseBody
	public Object statTerminal(Date startDate,Date endDate){

		return statService.statTerminal(startDate, endDate);
	}



}