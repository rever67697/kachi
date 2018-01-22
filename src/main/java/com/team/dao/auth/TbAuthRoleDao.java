package com.team.dao.auth;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.team.model.auth.TbAuthRole;



public interface TbAuthRoleDao {
	
	/**
	 * 通过userid查找角色列表
	 *@param id
	 *@return
	 *return
	 */
	public List<TbAuthRole> getRolesByUser(@Param("id")Integer id);
	
	/**
	 * 通过userid查找单前用户的专属角色
	 *@param id
	 *@return
	 *return
	 */
	public List<TbAuthRole> getUserRole(@Param("id")Integer id);
	
	/**
	 * 添加角色
	 *@param map
	 *@return
	 *return
	 */
	public void insertRole(TbAuthRole role);
	
	/**
	 * 添加用户-角色的关系
	 *@param map
	 *@return
	 *return
	 */
	public int inserUserRole(Map<String, Object> map);
	
	/**
	 * 把管理员的角色赋予给用户
	 *@param userId
	 *@return
	 *return
	 */
	public int inserAdminUserRole(@Param("userId") Integer userId);
	
	/**
	 * 删除用户拥有的管理员角色
	 *@param userId
	 *@return
	 *return
	 */
	public int deleteAdminUserRole(@Param("userId") Integer userId);
	
}
