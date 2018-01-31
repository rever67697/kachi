package com.team.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.aop.PermissionLog;
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
public class TerminalSimController {
	
	@Autowired
	private TerminalSimService terminalSimService;
	
	@PostMapping("/getTerminalSimList")
	public ResultList getTerminalSimList(Integer departmentId,Integer tsid,Long imsi,int page,int rows,HttpServletRequest request){
		Integer dId = CommonUtil.getUser(request).getDepartmentId();
		return terminalSimService.getTerminalSimList(departmentId,dId,tsid, imsi, page, rows);
	}
	
	@PostMapping("/deleteTerSimByIds")
	@PermissionLog(key="TSIDs_终端编号的集合")
	public ReturnMsg deleteTerSimByIds(String ids){
		return terminalSimService.deleteTerminalByIds(ids);
	}
	
}
