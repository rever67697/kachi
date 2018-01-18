package com.team.service.auth;

import java.util.List;


import com.team.model.auth.TbAuthPermission;
import com.team.model.auth.TbAuthRole;
import com.team.model.auth.TbAuthUser;
import com.team.vo.ReturnMsg;



public interface AuthService {
	
	public List<TbAuthPermission> getMenuByUser(TbAuthUser user);
	
	public ReturnMsg getFunByUser(Integer id);
	
	public List<TbAuthPermission> getPermissionTree();
	
	public TbAuthUser getUserByName(String name);
	
	public List<TbAuthRole> getRolesByUser(TbAuthUser user);
	
}
