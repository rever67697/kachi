package com.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.service.CommonService;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2017-12-23上午1:59:12
 * author:wuzhiheng
 */
@RestController
public class CommonController {
	
	@Autowired
	private CommonService commonService;
	
	@PostMapping("/getCountryDic")
	public ReturnMsg getCountryDic(){
		return commonService.getCountryDic();
	}
	
	@PostMapping("/getDepartmentDic")
	public ReturnMsg getDepartmentDic(){
		return commonService.getDepartmentDic();
	}
	
	@PostMapping("/getOperatorDic")
	public ReturnMsg getOperatorDic(){
		return commonService.getOperatorDic();
	}

}
