package com.team.dao.auth;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;



import com.team.model.auth.TbAuthPermission;
import com.team.model.auth.TbAuthRole;

/**
 * 创建日期：2018-1-18下午10:16:12
 * author:wuzhiheng
 */
public interface TbAuthPermissionDao {
	
	/**
	 * 根据用户的角色获取首页菜单
	 *@param list
	 *@return
	 *return
	 */
	public List<TbAuthPermission> getMenuByRole(List<TbAuthRole> list);
	
	/**
	 * 根据用户的角色和菜单id获取功能
	 *@param map
	 *@return
	 *return
	 */
	public List<TbAuthPermission> getFunByRole(Map<String, Object> map);
	
	/**
	 * 获取权限树
	 *@return
	 *return
	 */
	public List<TbAuthPermission> getAllPermission();
	
	/**
	 * 通过用户id查找所拥有的权限id
	 *@param id
	 *@return
	 *return
	 */
	public List<Integer> getPermissionByUser(@Param("id") Integer id);
	
}
