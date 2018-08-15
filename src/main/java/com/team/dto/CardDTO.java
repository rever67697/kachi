package com.team.dto;

import com.team.model.SimCard;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description :  只要用于扫描问题卡的装载bean
 * @Date Created in 下午1:53 2018/8/15
 */
public class CardDTO extends SimCard implements Serializable {

    private Integer tsid;//终端编号

    private Date selectDate;//

    private Long released;//释放卡

    private Date authTime;//

    public Date getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(Date selectDate) {
        this.selectDate = selectDate;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public Integer getTsid() {
        return tsid;
    }

    public void setTsid(Integer tsid) {
        this.tsid = tsid;
    }

    public Long getReleased() {
        return released;
    }

    public void setReleased(Long released) {
        this.released = released;
    }
}
