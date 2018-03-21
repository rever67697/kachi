package com.team.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午5:46 2018/3/20
 */
public class TerminalVersion implements Serializable{

    private Long id;

    private String describe;

    private String oVersion;

    private String tVersion;

    private String downUrl;

    private Integer status;

    private Integer type;

    private String terminalList;

    private String operatorMan;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operatorTime;

    public TerminalVersion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getoVersion() {
        return oVersion;
    }

    public void setoVersion(String oVersion) {
        this.oVersion = oVersion;
    }

    public String gettVersion() {
        return tVersion;
    }

    public void settVersion(String tVersion) {
        this.tVersion = tVersion;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTerminalList() {
        return terminalList;
    }

    public void setTerminalList(String terminalList) {
        this.terminalList = terminalList;
    }

    public String getOperatorMan() {
        return operatorMan;
    }

    public void setOperatorMan(String operatorMan) {
        this.operatorMan = operatorMan;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }
}
