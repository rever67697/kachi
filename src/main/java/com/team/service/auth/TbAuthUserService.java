package com.team.service.auth;

import com.team.model.auth.TbAuthUser;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2018-1-18下午10:18:27
 * author:wuzhiheng
 */
public interface TbAuthUserService {
	
	TbAuthUser getUserByName(String name);
	
	ResultList getUserList(Integer status,String name,Integer departmentId,int page,int rows);
	
	ReturnMsg saveOrUpdateUser(TbAuthUser user);
	
	ReturnMsg updateUserStatus(TbAuthUser user);
	
	int getUserCount(String name);
	
	ReturnMsg deleteUser(Integer id);
	
	ReturnMsg modifyPwd(TbAuthUser user,String oldPwd,String newPwd);
	
	ReturnMsg resetPwd(Integer id);
	
}
