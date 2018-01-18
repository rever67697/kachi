package com.team.service.auth;

import java.util.List;

import com.team.model.auth.TbAuthPermission;
import com.team.model.auth.TbAuthUser;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2018-1-18下午10:18:27
 * author:wuzhiheng
 */
public interface TbAuthPermissionService {
	
	public List<TbAuthPermission> getMenuByUser(TbAuthUser user);
	
	public ReturnMsg getFunByUser(TbAuthUser user,Integer id);
	
	public List<TbAuthPermission> getPermissionTree();
	
	public List<Integer> getPermissionByUser(Integer id);
	
}
