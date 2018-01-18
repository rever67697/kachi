package com.team.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.service.auth.TbAuthUserService;
import com.team.vo.ResultList;

/**
 * 创建日期：2018-1-18下午10:27:37
 * author:wuzhiheng
 */
@RestController
public class TbAuthUserController {

	@Autowired
	private TbAuthUserService tbAuthUserService;
	
	@PostMapping("/getUserList")
	public ResultList getUserList(Integer status, String name,
			Integer departmentId,int page,int rows){
		return tbAuthUserService.getUserList(status, name, departmentId, page, rows);
	}
	
}
