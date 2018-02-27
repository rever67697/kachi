package com.hqrh.rw.common.model;

import java.io.Serializable;


public class Operator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4608652855579201296L;
	private int id;
	private int operatorCode;
	private String operatorNameCn;
	private String operatorNameEn;
	private int defaultNet;
	private String nets;
	private int countryCode;
	private int status;
	private int level;
	private String apn;
	private int hasCard;
	private String mcc;
	private String mnc;
	private int groupSize;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(int operatorCode) {
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
	
	public int getDefaultNet() {
		return defaultNet;
	}
	public void setDefaultNet(int defaultNet) {
		this.defaultNet = defaultNet;
	}
	
	public String getNets() {
		return nets;
	}
	public void setNets(String nets) {
		this.nets = nets;
	}
	
	public int getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getApn() {
		return apn;
	}
	public void setApn(String apn) {
		this.apn = apn;
	}
	
	public int getHasCard() {
		return hasCard;
	}
	public void setHasCard(int hasCard) {
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
	@Override
	public String toString() {
		return "Operator [id=" + id + ", operatorCode=" + operatorCode
				+ ", operatorNameCn=" + operatorNameCn + ", operatorNameEn="
				+ operatorNameEn + ", defaultNet=" + defaultNet + ", nets="
				+ nets + ", countryCode=" + countryCode + ", status=" + status
				+ ", level=" + level + ", apn=" + apn + ", hasCard=" + hasCard
				+ ", mcc=" + mcc + ", mnc=" + mnc + ", groupSize=" + groupSize
				+ "]";
	}
	


}
