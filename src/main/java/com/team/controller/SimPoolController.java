package com.team.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.aop.PermissionLog;
import com.team.model.ReadPoolDept;
import com.team.model.SimPool;
import com.team.service.ReadPoolDeptService;
import com.team.service.SimPoolService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-18下午3:51:14
 * author:wuzhiheng
 */
@RestController
@PermissionLog("卡池管理")
@RequestMapping("/simpool")
public class SimPoolController {

	@Autowired
	private SimPoolService simPoolService;
	@Autowired
	private ReadPoolDeptService readPoolDeptService;
	
	@PostMapping("/list")
	public ResultList list(Integer spid,String name,Integer isActive,
			int page,int rows,HttpServletRequest request){
		Integer dId = CommonUtil.getUser(request).getDepartmentId();
		return simPoolService.getSimPoolList(dId,spid,name,isActive, page, rows);
	}
	
	@PostMapping("/outlineInfo")
	public ReturnMsg outlineInfo(HttpServletRequest request){
		Integer dId = CommonUtil.getUser(request).getDepartmentId();
		return simPoolService.getOutlineInfo(dId);
	}
	
	@PostMapping("/give")
	@PermissionLog(key="spid_卡池编号;departmentId_部门id")
	public ReturnMsg give(ReadPoolDept readPoolDept){
		return readPoolDeptService.saveReadPoolDept(readPoolDept);
	}
	
	@PostMapping("/update")
	@PermissionLog(key="spid_卡池编号;name_卡池名称;departmentId_部门编号")
	public ReturnMsg update(SimPool simPool){
		ReturnMsg returnMsg =  simPoolService.update(simPool);
		Boolean flag = true;
		if(flag.equals((returnMsg.getData()))){
			System.out.println("需要更新卡缓存");
		}
		return returnMsg;
	}
	
	@PostMapping("/save")
	@PermissionLog(key="name_卡池名称;spid_卡池编号;departmentId_部门编号")
	public ReturnMsg save(SimPool simPool) {
		return simPoolService.saveSimPool(simPool);
	}

}
