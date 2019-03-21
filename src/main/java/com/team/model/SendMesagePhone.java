package com.team.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description : 用来记录发送短信的手机号码
 * @Date Created in 11:50 2019-03-08
 */
public class SendMesagePhone implements Serializable {

    private Integer id;

    private Integer departmentId;

    private String phoneNumber;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
