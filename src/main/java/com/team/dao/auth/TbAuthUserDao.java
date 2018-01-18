package com.team.dao.auth;

import java.util.List;
import java.util.Map;


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
	
	
}
