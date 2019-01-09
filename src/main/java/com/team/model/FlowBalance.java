package com.team.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description : 终端剩余流量和有效期信息
 * @Date Created in 下午4:18 2018/3/26
 */
public class FlowBalance implements Serializable {

    private Integer id;

    /**终端编号**/
    private Integer tsid;

    /**剩余流量**/
    private Long allowFlow;

    /**有效期截止时间**/
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date validityDate;

    /**备注**/
    private String note;

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

    public Long getAllowFlow() {
        return allowFlow==null?0L:allowFlow;
    }

    public void setAllowFlow(Long allowFlow) {
        this.allowFlow = allowFlow;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
