package com.team.dao.auth;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team.model.auth.TbAuthRole;



public interface TbAuthRoleDao {
	
	
	public List<TbAuthRole> getRolesByUser(@Param("id")Integer id);
	
}
