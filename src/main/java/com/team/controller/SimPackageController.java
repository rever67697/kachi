package com.team.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.aop.PermissionLog;
import com.team.model.SimPackage;
import com.team.service.SimPackageService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-23下午6:52:19
 * author:wuzhiheng
 */
@RestController
@PermissionLog("套餐管理")
public class SimPackageController {

	@Autowired
	private SimPackageService simPackageService;
	
	@PostMapping("/getPackageList")
	public ResultList getSimPackage(Integer status,String name,int page,int rows,HttpServletRequest request){
		Integer dId= CommonUtil.getUser(request).getDepartmentId();
		return simPackageService.getPackageList(dId,status, name, page, rows);
	}
	
	@PostMapping("/savePackage")
	@PermissionLog(key="name_套餐名称;operatorCode_运营商编号;maxFlow_本国流量;maxRoamFlow_漫游流量")
	public ReturnMsg savePackage(SimPackage simPackage,Integer compareFlow,Integer compareRoamFlow,HttpServletRequest request){
		simPackage.setDepartmentId(CommonUtil.getUser(request).getDepartmentId());
		return simPackageService.savePackage(simPackage,compareFlow,compareRoamFlow);
	} 
	
	@PostMapping("/deletePackageById")
	@PermissionLog(key="name_套餐名称")
	public ReturnMsg deletePackageById(Integer id){
		return simPackageService.deletePackage(id);
	}
	
}
