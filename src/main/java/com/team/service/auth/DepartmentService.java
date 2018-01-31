package com.team.service.auth;

import java.util.List;

import com.team.model.auth.Department;
import com.team.model.auth.TbAuthPermission;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2018-1-30下午5:41:33
 * author:wuzhiheng
 */
public interface DepartmentService {

	public ReturnMsg saveOrUpdate(Department d);
	
	public ReturnMsg delete(Integer id);
	
	public List<TbAuthPermission> list();
	
}
