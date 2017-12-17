package com.team.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 终端的日消费记录，以终端当天所在国家的时区来计算	m_cost_day
 * 创建日期：2017-12-15下午4:15:16
 * author:wuzhiheng
 */
public class CostDay implements Serializable{
	
	private Integer id;//主键
	
	private Date date;//日期（当天所在国家的本地时区）
	
	private Date begIntegerime;//日期开始的时间（北京时间）
	
	private Integer tsid;//设备号
	
	private Integer duration;//使用时长（分钟）
	
	private Integer flow;//使用流量
	
	private Double cost;//超额费用
	
	private Integer rxFlow;//下行流量
	
	private Integer txFlow;//上行流量
	
	private Integer additionalFlow;//超额流量
	
	private Integer isLocal;//是否在归属地使用
	
	private Integer countryCode;//使用国家(当天第一次开机的国家)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getBegIntegerime() {
		return begIntegerime;
	}

	public void setBegIntegerime(Date begIntegerime) {
		this.begIntegerime = begIntegerime;
	}

	public Integer getTsid() {
		return tsid;
	}

	public void setTsid(Integer tsid) {
		this.tsid = tsid;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getFlow() {
		return flow;
	}

	public void setFlow(Integer flow) {
		this.flow = flow;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getRxFlow() {
		return rxFlow;
	}

	public void setRxFlow(Integer rxFlow) {
		this.rxFlow = rxFlow;
	}

	public Integer getTxFlow() {
		return txFlow;
	}

	public void setTxFlow(Integer txFlow) {
		this.txFlow = txFlow;
	}

	public Integer getAdditionalFlow() {
		return additionalFlow;
	}

	public void setAdditionalFlow(Integer additionalFlow) {
		this.additionalFlow = additionalFlow;
	}

	public Integer getIsLocal() {
		return isLocal;
	}

	public void setIsLocal(Integer isLocal) {
		this.isLocal = isLocal;
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}
	
	
}
