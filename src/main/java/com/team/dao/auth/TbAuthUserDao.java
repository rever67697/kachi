package com.team.dao.auth;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


import com.team.model.auth.TbAuthUser;

/**
 * 创建日期：2018-1-18下午10:16:12
 * author:wuzhiheng
 */
public interface TbAuthUserDao {

	/**
	 * 根据条件查找用户列表
	 *@param map
	 *@return
	 *return
	 */
	public List<TbAuthUser> getUserList(Map<String, Object> map);
	
	/**
	 * 添加用户
	 *@param user
	 *return
	 */
	public void saveUser(TbAuthUser user);
	
	/**
	 * 修改用户
	 *@param user
	 *return
	 */
	public int updateUser(TbAuthUser user);
	
	/**
	 * 更改用户账号状态
	 *@param user
	 *return
	 */
	public int updateStatus(TbAuthUser user);
	
	/**
	 * 创建用户时保证在库里面登录名是唯一的
	 *@param loginName
	 *@return
	 *return
	 */
	public int getUserCount(@Param("loginName") String loginName);
	
	/**
	 * 删除用户
	 *@param id
	 *@return
	 *return
	 */
	public int deleteUser(@Param("id") Integer id);
	
}
