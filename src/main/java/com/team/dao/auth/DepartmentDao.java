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
	
	int insert(Department d);
	
	int update(Department d);
	
	int delete(@Param("id") Integer id);
	
	List<TbAuthPermission> list(@Param("dId") Integer dId);

	List<Department> getAllDepartment();

}
