package com.team.model.auth;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description : 针对不同的ip地址可以设置不同的登录背景和系统显示名称
 * @Date Created in 17:46 2019-03-23
 */
public class IpPersonalConfig implements Serializable {

    private Integer id;
    private String ip;
    private String content;
    private String type;    //0-背景 1-文字
    private String operator;
    private Date createDate;
    private Date modifyDate;

    public IpPersonalConfig() {
    }

    public IpPersonalConfig(String ip, String content, String type, String operator) {
        this.ip = ip;
        this.content = content;
        this.type = type;
        this.operator = operator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
