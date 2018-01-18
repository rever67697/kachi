package com.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.team.model.auth.TbAuthPermission;
import com.team.model.auth.TbAuthRole;
import com.team.model.auth.TbAuthUser;



public interface AuthDao {
	
	public List<TbAuthPermission> getMenuByRole(List<TbAuthRole> list);
	
	public List<TbAuthPermission> getFunByRole(Map<String, Object> map);
	
	public List<TbAuthPermission> getAllPermission();
	
	public List<TbAuthUser> getUserByName(@Param("name")String name);
	
	public List<TbAuthRole> getRolesByUser(@Param("id")Integer id);
	
}
