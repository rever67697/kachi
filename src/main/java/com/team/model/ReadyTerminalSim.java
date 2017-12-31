package com.team.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 给终端人工指定卡信息，一般用于测试	m_ready_terminal_sim
 * 创建日期：2017-12-15下午4:48:51
 * author:wuzhiheng
 */
public class ReadyTerminalSim implements Serializable{

	private Integer id;//主键
	
	private Integer tsid;//终端id
	
	private Long imsi;
	
	private Integer lastStatus;//SIM卡前状态
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date insertDate;//添加时间
	
	private Integer operator;//操作人
	
	private Integer type;//预指定类型

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

	public Long getImsi() {
		return imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public Integer getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(Integer lastStatus) {
		this.lastStatus = lastStatus;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
