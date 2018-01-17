package com.team.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team.model.auth.TbAuthMenu;



public interface AuthDao {
	
	public List<TbAuthMenu> getMenuByRoleId();
	
	public List<TbAuthMenu> getFunByRoleId(@Param("parentId")Integer parentId);
	
}
