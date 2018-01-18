package com.team.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.service.auth.TbAuthPermissionService;

/**
 * 创建日期：2018-1-19上午12:55:55
 * author:wuzhiheng
 */
@RestController
public class TbAuthPermissionController {

	@Autowired
	private TbAuthPermissionService tbAuthPermissionService;
	
	@PostMapping("/getPermissionByUser")
	public Object getPermissionByUser(Integer id){
		return tbAuthPermissionService.getPermissionByUser(id);
	}
	
}
