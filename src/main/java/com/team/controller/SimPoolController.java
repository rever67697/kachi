package com.team.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.vo.OutlineInfo;
import com.team.model.ReadPoolDept;
import com.team.service.ReadPoolDeptService;
import com.team.service.SimPoolService;
import com.team.util.IConstant;
import com.team.util.CommonUtil;
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
	@Autowired
	private ReadPoolDeptService readPoolDeptService;
	
	@PostMapping("/getSimPoolByDept")
	public ResultList getSimPoolByDeparment(String spid,String name,String isActive,int page,int rows){
		return simPoolService.getSimPoolByDeparment(null,spid,name,isActive, page, rows);
	}
	
	@PostMapping("/getPoolOutlineInfo")
	public ReturnMsg getPoolOutlineInfo(String departmentId){
		OutlineInfo info = simPoolService.getOutlineInfo(CommonUtil.putInteger(departmentId));
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		returnMsg.setData(info);
		return returnMsg;
		
	}
	
	@PostMapping("/giveSimPool")
	public ReturnMsg giveSimPool(ReadPoolDept readPoolDept){
		return readPoolDeptService.saveReadPoolDept(readPoolDept);
	}
	
}
