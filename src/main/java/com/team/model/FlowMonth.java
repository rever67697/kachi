package com.team.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**卡的月流量	m_flow_month
 * 创建日期：2017-12-15下午3:15:00
 * author:wuzhiheng
 */
public class FlowMonth implements Serializable{
	
	private Integer id;//主键
	
	private Long imsi;
	
	private String iccid;
	
	private String number;
	
	private String date;//记录消费的年月
	
	private Integer maxFlow;//本国流量最大使用流量额度
	
	private Integer usedFlow;//本国流量已使用的额度
	
	private Integer residueFlow;//本国流量剩余额度
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date lastUpDatetime;//最后更新时间
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date accountPeriodStartDate;//本账期起始时间
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date accountPeriodEndDate;//本帐期结束时间
	
	private Integer maxRoamFlow;//最大漫游流量（省内流量）
	
	private Integer usedRoamFlow;//使用的漫游流量（省内流量）
	
	private Integer residueRoamFlow;//漫游流量剩余额度
	
	private Integer operatorCode;//运营商编号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getImsi() {
		return imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getMaxFlow() {
		return maxFlow;
	}

	public void setMaxFlow(Integer maxFlow) {
		this.maxFlow = maxFlow;
	}

	public Integer getUsedFlow() {
		return usedFlow;
	}

	public void setUsedFlow(Integer usedFlow) {
		this.usedFlow = usedFlow;
	}

	public Integer getResidueFlow() {
		return residueFlow;
	}

	public void setResidueFlow(Integer residueFlow) {
		this.residueFlow = residueFlow;
	}

	public Date getAccountPeriodEndDate() {
		return accountPeriodEndDate;
	}

	public void setAccountPeriodEndDate(Date accountPeriodEndDate) {
		this.accountPeriodEndDate = accountPeriodEndDate;
	}

	public Integer getMaxRoamFlow() {
		return maxRoamFlow;
	}

	public void setMaxRoamFlow(Integer maxRoamFlow) {
		this.maxRoamFlow = maxRoamFlow;
	}

	public Integer getUsedRoamFlow() {
		return usedRoamFlow;
	}

	public void setUsedRoamFlow(Integer usedRoamFlow) {
		this.usedRoamFlow = usedRoamFlow;
	}

	public Integer getResidueRoamFlow() {
		return residueRoamFlow;
	}

	public void setResidueRoamFlow(Integer residueRoamFlow) {
		this.residueRoamFlow = residueRoamFlow;
	}

	public Integer getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(Integer operatorCode) {
		this.operatorCode = operatorCode;
	}


	public Date getAccountPeriodStartDate() {
		return accountPeriodStartDate;
	}

	public void setAccountPeriodStartDate(Date accountPeriodStartDate) {
		this.accountPeriodStartDate = accountPeriodStartDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "FlowMonth{" +
				"id=" + id +
				", imsi=" + imsi +
				", iccid='" + iccid + '\'' +
				", number='" + number + '\'' +
				", date='" + date + '\'' +
				", maxFlow=" + maxFlow +
				", usedFlow=" + usedFlow +
				", residueFlow=" + residueFlow +
				", lastUpDatetime=" + lastUpDatetime +
				", accountPeriodStartDate=" + accountPeriodStartDate +
				", accountPeriodEndDate=" + accountPeriodEndDate +
				", maxRoamFlow=" + maxRoamFlow +
				", usedRoamFlow=" + usedRoamFlow +
				", residueRoamFlow=" + residueRoamFlow +
				", operatorCode=" + operatorCode +
				'}';
	}

	public Date getLastUpDatetime() {
		return lastUpDatetime;
	}

	public void setLastUpDatetime(Date lastUpDatetime) {
		this.lastUpDatetime = lastUpDatetime;
	}
}
