package com.team.model;

import java.io.Serializable;

/**
 * @Author : wuzhiheng
 * @Description :   限速配置
 * @Date Created in 15:28 2019-06-06
 */
public class RuleSpeed implements Serializable {

    private Integer id;
    private Integer departmentId;
    private Long maxData;           //每月最大流量
    private Integer speedLimit;     //每月限制速度kbps
    private Long maxDayData;        //每日最大流量
    private Integer daySpeedLimit;  //每日限制速度kbps
    private String departmentName;

    public RuleSpeed() {
    }

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

    public Long getMaxData() {
        return maxData;
    }

    public void setMaxData(Long maxData) {
        this.maxData = maxData;
    }

    public Integer getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(Integer speedLimit) {
        this.speedLimit = speedLimit;
    }

    public Long getMaxDayData() {
        return maxDayData;
    }

    public void setMaxDayData(Long maxDayData) {
        this.maxDayData = maxDayData;
    }

    public Integer getDaySpeedLimit() {
        return daySpeedLimit;
    }

    public void setDaySpeedLimit(Integer daySpeedLimit) {
        this.daySpeedLimit = daySpeedLimit;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
