package com.team.model.auth;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description : 部门登录受ip限制
 * @Date Created in 14:46 2019-03-21
 */
public class DepartmentIpConfig implements Serializable {

    private Integer id;

    private Integer departmentId;

    private String ip;

    private Date createDate;

    private Date modifyDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
