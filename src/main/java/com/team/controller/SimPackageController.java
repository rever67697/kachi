package com.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.model.SimPackage;
import com.team.service.SimPackageService;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-23下午6:52:19
 * author:wuzhiheng
 */
@RestController
public class SimPackageController {

	@Autowired
	private SimPackageService simPackageService;
	
	@PostMapping("/getSimPackage")
	public ResultList getSimPackage(Integer status,String name,int page,int rows){
		return simPackageService.getSimPackage(status, name, page, rows);
	}
	
	@PostMapping("/savePackage")
	public ReturnMsg savePackage(SimPackage simPackage,Integer compareFlow,Integer compareRoamFlow){
		return simPackageService.savePackage(simPackage,compareFlow,compareRoamFlow);
	} 
	
	@PostMapping("/deletePackageById")
	public ReturnMsg deleteTerminalByIds(Integer id){
		return simPackageService.deletePackage(id);
	}
	
}
