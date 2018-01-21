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
	
	/**
	 * 插入角色-权限关系前，先把原本的关联关系清了
	 *@param roleId
	 *@return
	 *return
	 */
	public int updateRolePermission(@Param("userId") Integer userId);
	
	/**
	 * 插入角色-权限的关联关系
	 *@param map
	 *@return
	 *return
	 */
	public int insertRolePermission(Map<String, Object> map);
	
	/**
	 * 删除节点
	 *@param id
	 *@return
	 *return
	 */
	public int updateStatus(@Param("id") Integer id);
	
	/**
	 * 添加节点
	 *@param permission
	 *@return
	 *return
	 */
	public int insertPermission(TbAuthPermission permission);
	
	/**
	 * 更新节点
	 *@param permission
	 *@return
	 *return
	 */
	public int updatePermission(TbAuthPermission permission);
	
	/**
	 * 添加一个节点时候，添加系统管理员->这个节点的关联关系
	 *@param permissionId
	 *@return
	 *return
	 */
	public int insertRP(@Param("permissionId") Integer permissionId);
	
}
