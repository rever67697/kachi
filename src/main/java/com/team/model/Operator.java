package com.team.model;

import java.io.Serializable;

/**
 * 运营商表
 * 创建日期：2018-2-9下午3:12:40
 * author:wuzhiheng
 */
public class Operator implements Serializable{
	
	private static final long serialVersionUID = -4608652855579201296L;
	private Integer id;
	private Integer operatorCode;
	private String operatorNameCn;
	private String operatorNameEn;
	private Integer defaultNet;
	private String nets;
	private Integer countryCode;
	private Integer status;
	private Integer level;
	private String apn;
	private Integer hasCard;
	private String mcc;
	private String mnc;
	private int groupSize;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(Integer operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getOperatorNameCn() {
		return operatorNameCn;
	}
	public void setOperatorNameCn(String operatorNameCn) {
		this.operatorNameCn = operatorNameCn;
	}
	public String getOperatorNameEn() {
		return operatorNameEn;
	}
	public void setOperatorNameEn(String operatorNameEn) {
		this.operatorNameEn = operatorNameEn;
	}
	public Integer getDefaultNet() {
		return defaultNet;
	}
	public void setDefaultNet(Integer defaultNet) {
		this.defaultNet = defaultNet;
	}
	public String getNets() {
		return nets;
	}
	public void setNets(String nets) {
		this.nets = nets;
	}
	public Integer getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getApn() {
		return apn;
	}
	public void setApn(String apn) {
		this.apn = apn;
	}
	public Integer getHasCard() {
		return hasCard;
	}
	public void setHasCard(Integer hasCard) {
		this.hasCard = hasCard;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMnc() {
		return mnc;
	}
	public void setMnc(String mnc) {
		this.mnc = mnc;
	}
	public int getGroupSize() {
		return groupSize;
	}
	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}
	
	

}
