package com.hqrh.rw.common.model;

import java.io.Serializable;
import java.util.Date;


public class FlowMonth implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -115846979458559246L;
	private int id;
	private long imsi;
	private String iccid;
	private String number;
	private String date;  //yyyy-MM
	private int maxFlow;
	private int usedFlow;
	private int residueFlow;
	private Date lastUpDatetime;
	private Date accountPeriodStartDate;
	private Date accountPeriodEndDate;
	private int maxRoamFlow;
	private int usedRoamFlow;
	private int residueRoamFlow;
	private int operatorCode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public long getImsi() {
		return imsi;
	}
	public void setImsi(long imsi) {
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
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getMaxFlow() {
		return maxFlow;
	}
	public void setMaxFlow(int maxFlow) {
		this.maxFlow = maxFlow;
	}
	
	public int getUsedFlow() {
		return usedFlow;
	}
	public void setUsedFlow(int usedFlow) {
		this.usedFlow = usedFlow;
	}
	
	public int getResidueFlow() {
		return residueFlow;
	}
	public void setResidueFlow(int residueFlow) {
		this.residueFlow = residueFlow;
	}
	
	public Date getLastUpDatetime() {
		return lastUpDatetime;
	}
	public void setLastUpDatetime(Date lastUpDatetime) {
		this.lastUpDatetime = lastUpDatetime;
	}
	
	public Date getAccountPeriodStartDate() {
		return accountPeriodStartDate;
	}
	public void setAccountPeriodStartDate(Date accountPeriodStartDate) {
		this.accountPeriodStartDate = accountPeriodStartDate;
	}
	
	public Date getAccountPeriodEndDate() {
		return accountPeriodEndDate;
	}
	public void setAccountPeriodEndDate(Date accountPeriodEndDate) {
		this.accountPeriodEndDate = accountPeriodEndDate;
	}
	
	public int getMaxRoamFlow() {
		return maxRoamFlow;
	}
	public void setMaxRoamFlow(int maxRoamFlow) {
		this.maxRoamFlow = maxRoamFlow;
	}
	
	public int getUsedRoamFlow() {
		return usedRoamFlow;
	}
	public void setUsedRoamFlow(int usedRoamFlow) {
		this.usedRoamFlow = usedRoamFlow;
	}
	
	public int getResidueRoamFlow() {
		return residueRoamFlow;
	}
	public void setResidueRoamFlow(int residueRoamFlow) {
		this.residueRoamFlow = residueRoamFlow;
	}
	
	public int getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(int operatorCode) {
		this.operatorCode = operatorCode;
	}
	@Override
	public String toString() {
		return "FlowMonth [id=" + id + ", imsi=" + imsi + ", iccid=" + iccid
				+ ", number=" + number + ", date=" + date + ", maxFlow="
				+ maxFlow + ", usedFlow=" + usedFlow + ", residueFlow="
				+ residueFlow + ", lastUpDatetime=" + lastUpDatetime
				+ ", accountPeriodStartDate=" + accountPeriodStartDate
				+ ", accountPeriodEndDate=" + accountPeriodEndDate
				+ ", maxRoamFlow=" + maxRoamFlow + ", usedRoamFlow="
				+ usedRoamFlow + ", residueRoamFlow=" + residueRoamFlow
				+ ", operatorCode=" + operatorCode + "]";
	}

	
	
}
