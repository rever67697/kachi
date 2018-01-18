package com.team.service.auth.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.dao.AuthDao;
import com.team.model.auth.TbAuthPermission;
import com.team.model.auth.TbAuthRole;
import com.team.model.auth.TbAuthUser;
import com.team.service.auth.AuthService;
import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.vo.ReturnMsg;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private AuthDao authDao;
	
	public List<TbAuthPermission> getMenuByUser(TbAuthUser user){
		List<TbAuthPermission> list = null;
		if(CommonUtil.listNotBlank(user.getRoles())){
			list = authDao.getMenuByRole(user.getRoles());
		}
		
		return  CommonUtil.bulidTree(list,false);
	}

	@Override
	public ReturnMsg getFunByUser(Integer id) {
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		returnMsg.setData(authDao.getFunByRoleId(id));
		return returnMsg;
	}

	@Override
	public List<TbAuthPermission> getPermissionTree() {
		List<TbAuthPermission> list = authDao.getAllPermission();
		return CommonUtil.bulidTree(list,true);
	}

	@Override
	public TbAuthUser getUserByName(String name) {
		List<TbAuthUser> list = authDao.getUserByName(name);
		return CommonUtil.listNotBlank(list)?list.get(0):null;
	}

	@Override
	public List<TbAuthRole> getRolesByUser(TbAuthUser user) {
		return authDao.getRolesByUser(user.getId());
	}

	
}
