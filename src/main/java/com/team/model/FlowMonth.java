package com.team.model;

import java.io.Serializable;
import java.util.Date;

/**卡的月流量	m_flow_month
 * 创建日期：2017-12-15下午3:15:00
 * author:wuzhiheng
 */
public class FlowMonth implements Serializable{
	
	private Integer id;//主键
	
	private Long imsi;
	
	private String iccid;
	
	private String number;
	
	private Date date;//记录消费的年月
	
	private Integer maxFlow;//本国流量最大使用流量额度
	
	private Integer usedFlow;//本国流量已使用的额度
	
	private Integer residueFlow;//本国流量剩余额度
	
	private Date lastUpdateTime;//最后更新时间
	
	private Date accountPeriodStratDate;//本账期起始时间
	
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getAccountPeriodStratDate() {
		return accountPeriodStratDate;
	}

	public void setAccountPeriodStratDate(Date accountPeriodStratDate) {
		this.accountPeriodStratDate = accountPeriodStratDate;
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
	
	
}
