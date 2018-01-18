package com.team.service.auth;

import com.team.model.auth.TbAuthUser;
import com.team.vo.ResultList;

/**
 * 创建日期：2018-1-18下午10:18:27
 * author:wuzhiheng
 */
public interface TbAuthUserService {
	
	public TbAuthUser getUserByName(String name);
	
	public ResultList getUserList(Integer status,String name,Integer departmentId,int page,int rows);

}
