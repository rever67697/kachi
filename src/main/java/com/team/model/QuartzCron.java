package com.team.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description : 一些定时任务相关的配置
 * @Date Created in 下午11:10 2018/7/2
 */
public class QuartzCron implements Serializable {

    private int problemcardMinute;//扫描时间间隔，针对选卡时间

    private String problemcardCronstr;//定时任务描述

    private int statusProblemcard;//是否扫描

    private int isHandleProblemcard;//是否处理问题卡

    private int thresholdProblemcard;//达到多少次异常后处理问题卡

    private Date lastTime;//修改时间

    private int thresholdAlarm;//用于首页显示选卡错误警报的阈值

    private int statusMsg;//是否开启发送短信

    private int msgMinute;//扫描取卡异常

    private String msgCronstr;//定时任务描述-扫描取卡异常

    private String msgPhone;//短信接收手机

    private String msgUrl;//短信接口地址

    private int statTerminalMinute ;//每隔多少分钟就去统计一次在线终端

    public int getProblemcardMinute() {
        return problemcardMinute;
    }

    public void setProblemcardMinute(int problemcardMinute) {
        this.problemcardMinute = problemcardMinute;
    }

    public String getProblemcardCronstr() {
        return problemcardCronstr;
    }

    public void setProblemcardCronstr(String problemcardCronstr) {
        this.problemcardCronstr = problemcardCronstr;
    }

    public int getStatusProblemcard() {
        return statusProblemcard;
    }

    public void setStatusProblemcard(int statusProblemcard) {
        this.statusProblemcard = statusProblemcard;
    }

    public int getIsHandleProblemcard() {
        return isHandleProblemcard;
    }

    public void setIsHandleProblemcard(int isHandleProblemcard) {
        this.isHandleProblemcard = isHandleProblemcard;
    }

    public int getThresholdProblemcard() {
        return thresholdProblemcard;
    }

    public void setThresholdProblemcard(int thresholdProblemcard) {
        this.thresholdProblemcard = thresholdProblemcard;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public int getThresholdAlarm() {
        return thresholdAlarm;
    }

    public void setThresholdAlarm(int thresholdAlarm) {
        this.thresholdAlarm = thresholdAlarm;
    }

    public int getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(int statusMsg) {
        this.statusMsg = statusMsg;
    }

    public int getMsgMinute() {
        return msgMinute;
    }

    public void setMsgMinute(int msgMinute) {
        this.msgMinute = msgMinute;
    }

    public String getMsgCronstr() {
        return msgCronstr;
    }

    public void setMsgCronstr(String msgCronstr) {
        this.msgCronstr = msgCronstr;
    }

    public String getMsgPhone() {
        return msgPhone;
    }

    public void setMsgPhone(String msgPhone) {
        this.msgPhone = msgPhone;
    }

    public String getMsgUrl() {
        return msgUrl;
    }

    public void setMsgUrl(String msgUrl) {
        this.msgUrl = msgUrl;
    }

    public int getStatTerminalMinute() {
        return statTerminalMinute;
    }

    public void setStatTerminalMinute(int statTerminalMinute) {
        this.statTerminalMinute = statTerminalMinute;
    }
}
