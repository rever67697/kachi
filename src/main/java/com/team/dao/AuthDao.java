package com.team.dao;

import java.util.List;


import com.team.model.TbAuthMenu;

public interface AuthDao {
	
	public List<TbAuthMenu> queryMenuByRoleId();
	
}
