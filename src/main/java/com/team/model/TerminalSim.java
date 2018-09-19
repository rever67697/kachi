package com.team.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 在线终端使用的卡信息		m_terminal_sim
 * 创建日期：2017-12-15下午4:40:34
 * author:wuzhiheng
 */
public class TerminalSim implements Serializable{
	
	private Integer id;//主键
	
	private Long imsi;
	
	private Integer tsid;//终端的编号
	
	private String mcc;
	
	private String groupKey;//卡组key

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date beatTime;//最后心跳时间

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date date;//选卡时间
	
	private Integer reserve;//是否是在归属地；0：归属地；1：漫游
	
	private Long eCardImsi;//副卡IMSI
	
	private Integer eCardDuration;//副卡时延
	
	private String runVersion;//当前版本
	
	private Integer vCountryCode;//国家编码
	
	private String countryName;//国家名称
	
	private String departmentName;//部门名称

	private String operatorName;//运营商名称

	private String sversion;//终端版本
	
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

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

	public Integer getTsid() {
		return tsid;
	}

	public void setTsid(Integer tsid) {
		this.tsid = tsid;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getGroupKey() {
		return groupKey;
	}

	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}

	public Date getBeatTime() {
		return beatTime;
	}

	public void setBeatTime(Date beatTime) {
		this.beatTime = beatTime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getReserve() {
		return reserve;
	}

	public void setReserve(Integer reserve) {
		this.reserve = reserve;
	}

	public Long geteCardImsi() {
		return eCardImsi;
	}

	public void seteCardImsi(Long eCardImsi) {
		this.eCardImsi = eCardImsi;
	}

	public Integer geteCardDuration() {
		return eCardDuration;
	}

	public void seteCardDuration(Integer eCardDuration) {
		this.eCardDuration = eCardDuration;
	}

	public String getRunVersion() {
		return runVersion;
	}

	public void setRunVersion(String runVersion) {
		this.runVersion = runVersion;
	}

	public Integer getvCountryCode() {
		return vCountryCode;
	}

	public void setvCountryCode(Integer vCountryCode) {
		this.vCountryCode = vCountryCode;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getSversion() {
		return sversion;
	}

	public void setSversion(String sversion) {
		this.sversion = sversion;
	}
}
