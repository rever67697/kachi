package com.team.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.model.OutlineInfo;
import com.team.service.SimPoolService;
import com.team.util.IConstant;
import com.team.util.StringUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-18下午3:51:14
 * author:wuzhiheng
 */
@RestController
public class SimPoolController {

	@Autowired
	private SimPoolService simPoolService;
	
	@PostMapping("/getSimPoolByDept")
	public ResultList getSimPoolByDeparment(String spid,String name,String isActive,int page,int rows){
		return simPoolService.getSimPoolByDeparment(null,spid,name,isActive, page, rows);
	}
	
	@GetMapping("/getPoolOutlineInfo")
	public ReturnMsg getPoolOutlineInfo(String departmentId,HttpServletRequest request){
		OutlineInfo info = simPoolService.getOutlineInfo(StringUtil.putInteger(departmentId));
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		returnMsg.setData(info);
		return returnMsg;
		
	}
	
}
