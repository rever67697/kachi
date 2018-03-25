package com.team.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.service.auth.OperationLogService;
import com.team.vo.ResultList;

/**
 * 创建日期：2018-1-29下午5:12:13
 * author:wuzhiheng
 */
@RestController
@RequestMapping("/log")
public class OperationLogController {

	@Autowired
	private OperationLogService operationLogService;
	
	@PostMapping("/list")
	public ResultList getLogList(String username,String bussinesstype,int page,int rows){
		return operationLogService.getLogList(username,bussinesstype, page, rows);
	}
}
