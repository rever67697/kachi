package com.team.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午11:10 2018/7/2
 */
public class QuartzCron implements Serializable {

    private Integer minute;//扫描时间间隔，针对选卡时间

    private String cronStr;//定时任务描述

    private Integer status;//是否扫描

    private Integer isHandle;//是否处理问题卡

    private Integer count;//达到多少次异常后处理问题卡

    private Date lastTime;

    private Integer alarmCount;//用于首页显示选卡错误警报的阈值

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public String getCronStr() {
        return cronStr;
    }

    public void setCronStr(String cronStr) {
        this.cronStr = cronStr;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(Integer isHandle) {
        this.isHandle = isHandle;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAlarmCount() {
        return alarmCount;
    }

    public void setAlarmCount(Integer alarmCount) {
        if(alarmCount==null){
            alarmCount=0;
        }
        this.alarmCount = alarmCount;
    }
}
