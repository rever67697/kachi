package com.team.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.team.model.TerminalChargeRecord;
import com.team.service.TerminalChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.team.annotation.PermissionLog;
import com.team.model.Terminal;
import com.team.service.CostDayService;
import com.team.service.TerminalService;
import com.team.service.TerminalSimService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-21下午4:56:08
 * author:wuzhiheng
 */
@RestController
@PermissionLog("终端管理")
@RequestMapping("/terminal")
@SuppressWarnings("all")
public class TerminalController {

	@Autowired
	private TerminalService terminalService;
	@Autowired
	private CostDayService costDayService;
	@Autowired
	private TerminalSimService terminalSimService;
	@Autowired
	private TerminalChargeService terminalChargeService;
	
	@PostMapping("/list")
	public ResultList list(Integer departmentId,Integer status,Integer tsid,Integer activated,
			int page,int rows,HttpServletRequest request){
		Integer dId = CommonUtil.getUser(request).getDepartmentId();
		return terminalService.getTerminalList(departmentId,dId, tsid, status,activated, page, rows);
	}
	
	@PostMapping("/delete")
	@PermissionLog(key="TSIDs_终端编号的集合")
	public ReturnMsg delete(String ids){
		return terminalService.deleteTerminalByIds(ids);
	}
	
	@PostMapping("/save")
	@PermissionLog(key="tsid_终端编号")
	public ReturnMsg save(Terminal terminal){
		return terminalService.saveTerminal(terminal);
	} 
	
	@PostMapping("/upload")
	@PermissionLog
	public ReturnMsg upload(MultipartFile file){
		ReturnMsg returnMsg = terminalService.getTerminalList(file);
		if("200".equals(returnMsg.getCode())){
			List<Terminal> list = (List<Terminal>) returnMsg.getData();
			terminalService.insertBatch(list);
			returnMsg.setData(list.size());
		}
		return returnMsg;
	} 
	
	@PostMapping("/getCostDay")
	@PermissionLog(key="tsid_终端编号")
	public ResultList getCostDay(Integer tsid,int page,int rows){
		return costDayService.getCostDayByTsid(tsid, page, rows);
	}
	
	@PostMapping("/getSimcard")
	@PermissionLog(key="tsid_终端编号")
	public ReturnMsg getSimcard(Integer tsid){
		return terminalSimService.getTerminalSimByTsid(tsid);
	} 
	
	@PostMapping("/batchUpdate")
	@PermissionLog(key="tsids_终端编号的集合;departmentId_部门编号")
	public ReturnMsg batchUpdate(String ids,Integer departmentId){
		return terminalService.updateDepartment(ids, departmentId);
	}

	@PostMapping("/charge")
	@PermissionLog(key = "tsid_终端编号;chargeFlow_充值流量;allowFlow_剩余流量;chargeDate_充值天数;validityDate_有效期;note_备注")
	public ReturnMsg charge(TerminalChargeRecord terminalChargeRecord,HttpServletRequest request){
		terminalChargeRecord.setCreateDate(new Date());
		terminalChargeRecord.setOperator(CommonUtil.getUser(request).getName());
		return terminalChargeService.charge(terminalChargeRecord);
	}

	@PostMapping("/selectedList")
	public ResultList selectedList(String terminalList){
		return terminalService.getSelectedList(terminalList);
	}


}
