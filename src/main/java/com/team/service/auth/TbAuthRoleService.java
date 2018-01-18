package com.team.service.auth;

import java.util.List;


import com.team.model.auth.TbAuthRole;
import com.team.model.auth.TbAuthUser;



public interface TbAuthRoleService {
	
	public List<TbAuthRole> getRolesByUser(TbAuthUser user);
	
}
