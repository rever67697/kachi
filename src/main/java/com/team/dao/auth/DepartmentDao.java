package com.team.dao.auth;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team.model.auth.Department;
import com.team.model.auth.TbAuthPermission;

/**
 * 创建日期：2018-1-30下午5:40:02
 * author:wuzhiheng
 */
public interface DepartmentDao {
	
	public int insert(Department d);
	
	public int update(Department d);
	
	public int delete(@Param("id") Integer id);
	
	public List<TbAuthPermission> list();

}
