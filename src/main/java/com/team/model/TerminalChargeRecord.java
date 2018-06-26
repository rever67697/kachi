package com.team.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description : 终端充值记录
 * @Date Created in 下午4:06 2018/3/26
 */
public class TerminalChargeRecord implements Serializable {

    private Integer id;

    private Integer tsid;

    private Long chargeFlow;

    private Long allowFlow;

    private Integer chargeDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date validityDate;

    private String operator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    @JsonIgnore
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTsid() {
        return tsid;
    }

    public void setTsid(Integer tsid) {
        this.tsid = tsid;
    }

    public Long getChargeFlow() {
        return chargeFlow;
    }

    public void setChargeFlow(Long chargeFlow) {
        this.chargeFlow = chargeFlow;
    }

    public Long getAllowFlow() {
        return allowFlow==null?0L:allowFlow;
    }

    public void setAllowFlow(Long allowFlow) {
        this.allowFlow = allowFlow;
    }

    public Integer getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(Integer chargeDate) {
        this.chargeDate = chargeDate;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
