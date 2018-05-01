package com.team.controller.auth;

import com.team.annotation.PermissionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.model.auth.TbAuthPermission;
import com.team.service.auth.TbAuthPermissionService;
import com.team.vo.ReturnMsg;

/**
 * 菜单相关，权限
 * 创建日期：2018-1-19上午12:55:55
 * author:wuzhiheng
 */
@RestController
@RequestMapping("/permission")
@PermissionLog("用户管理")
public class TbAuthPermissionController {

	@Autowired
	private TbAuthPermissionService tbAuthPermissionService;
	
	@PostMapping("/getByUser")
	public Object getPermissionByUser(Integer id){
		return tbAuthPermissionService.getPermissionByUser(id);
	}
	
	@PostMapping("/grant")
	@PermissionLog
	public ReturnMsg grantPermission(Integer userId,String ids){
		return tbAuthPermissionService.grantPermission(userId, ids);
	}
	
	@PostMapping("/delete")
	public ReturnMsg deletePermission(Integer id){
		return tbAuthPermissionService.updateStatus(id);
	}
	
	@PostMapping("/save")
	public ReturnMsg savePermission(TbAuthPermission permission,String icon){
		permission.setIconCls(icon);
		return tbAuthPermissionService.saveOrUpdatePermission(permission);
	}
	
}
