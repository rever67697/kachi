package com.team.service.auth;

import com.team.model.auth.TbAuthUser;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2018-1-18下午10:18:27
 * author:wuzhiheng
 */
public interface TbAuthUserService {
	
	public TbAuthUser getUserByName(String name);
	
	public ResultList getUserList(Integer status,String name,
			Integer departmentId,Integer dId,int page,int rows);
	
	public ReturnMsg saveOrUpdateUser(TbAuthUser user);
	
	public ReturnMsg updateUserStatus(TbAuthUser user);
	
	public int getUserCount(String name);
	
	public ReturnMsg deleteUser(Integer id);
	
	public ReturnMsg modifyPwd(TbAuthUser user,String oldPwd,String newPwd);
	
	public ReturnMsg resetPwd(Integer id);
	
}
