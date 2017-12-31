package com.team.dao;

import java.util.List;

import com.team.model.auth.TbAuthMenu;



public interface AuthDao {
	
	public List<TbAuthMenu> queryMenuByRoleId();
	
}
