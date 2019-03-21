package com.team.dto;

import com.team.model.SelectCard;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 18:41 2019-03-08
 */
public class SelectCardDTO extends SelectCard {

    private Integer departmentId;

    private String phoneNumber;

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
}
