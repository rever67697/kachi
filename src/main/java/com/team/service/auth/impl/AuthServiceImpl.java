package com.team.service.auth.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public ReturnMsg getFunByUser(TbAuthUser user,Integer id) {
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", id);
		map.put("list", user.getRoles());
		returnMsg.setData(authDao.getFunByRole(map));
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
