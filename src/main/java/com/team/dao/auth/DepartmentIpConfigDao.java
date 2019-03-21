package com.team.dao.auth;

import com.team.model.auth.Department;
import com.team.model.auth.DepartmentIpConfig;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 15:02 2019-03-21
 */
public interface DepartmentIpConfigDao {

    int save(Department department);

    int update(Department department);

}
