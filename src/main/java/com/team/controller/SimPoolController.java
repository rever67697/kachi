package com.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.service.SimPoolService;

/**
 * 创建日期：2017-12-18下午3:51:14
 * author:wuzhiheng
 */
@RestController
public class SimPoolController {

	@Autowired
	private SimPoolService simPoolService;
	
	@PostMapping("/getSimPoolByDept")
	public Object getSimPoolByDeparment(String spid,String name,String isActive,int page,int rows){
		return simPoolService.getSimPoolByDeparment(null,spid,name,isActive, page, rows);
	}
	
}
