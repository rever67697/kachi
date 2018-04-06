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
	public ResultList list(Integer status,String name,int page,int rows,HttpServletRequest request){
		Integer dId= CommonUtil.getUser(request).getDepartmentId();
		return simPackageService.getPackageList(dId,status, name, page, rows);
	}
	
	@PostMapping("/save")
	@PermissionLog(key="name_套餐名称;operatorCode_运营商编号;maxFlow_本国流量;maxRoamFlow_漫游流量;isChange_流量参数是否改变")
	public ReturnMsg save(SimPackage simPackage,boolean isChange,HttpServletRequest request){
		simPackage.setDepartmentId(CommonUtil.getUser(request).getDepartmentId());
		return simPackageService.savePackage(simPackage,isChange);
	} 
	
	@PostMapping("/delete")
	@PermissionLog(key="name_套餐名称")
	public ReturnMsg delete(Integer id){
		return simPackageService.deletePackage(id);
	}

	
}
