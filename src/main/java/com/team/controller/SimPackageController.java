package com.team.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.annotation.PermissionLog;
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
@RequestMapping("/package")
public class SimPackageController {

	@Autowired
	private SimPackageService simPackageService;
	
	@PostMapping("/list")
	public ResultList list(Integer departmentId,Integer status,String name,int page,int rows){
		return simPackageService.list(departmentId,status, name, page, rows);
	}
	
	@PostMapping("/save")
	@PermissionLog
	public ReturnMsg save(SimPackage simPackage){
		simPackage.setDepartmentId(CommonUtil.getDepartmentId());
		return simPackageService.save(simPackage);
	} 
	
	@PostMapping("/delete")
	@PermissionLog
	public ReturnMsg delete(Integer id){
		return simPackageService.delete(id);
	}

	
}
