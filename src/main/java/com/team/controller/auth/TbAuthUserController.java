package com.team.controller.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.model.auth.TbAuthUser;
import com.team.service.auth.TbAuthUserService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

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
			Integer departmentId,int page,int rows,HttpServletRequest  request){
		Integer dId = CommonUtil.getUser(request).getDepartmentId(); 
		return tbAuthUserService.getUserList(status, name,departmentId,dId, page, rows);
	}
	
	@PostMapping("/saveUser")
	public ReturnMsg saveUser(TbAuthUser user){
		return tbAuthUserService.saveOrUpdateUser(user);
	}
	
	@PostMapping("/updateUserStatus")
	public ReturnMsg updateUserStatus(TbAuthUser user){
		return tbAuthUserService.updateUserStatus(user);
	}
	
	@PostMapping("/deleteUser")
	public ReturnMsg deleteUser(Integer id){
		return tbAuthUserService.deleteUser(id);
	}
	
	@GetMapping("/getUserCount")
	public int getUserCount(String name){
		return tbAuthUserService.getUserCount(name);
	}
	
}
