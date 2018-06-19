package com.team.model;

import java.io.Serializable;


public class OperatorPrefer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3857558033084074591L;
	private Integer id;
	private Integer operatorCode;
	private String roamMcc;
	private String pPlmn;
	private String fPlmn;

	private String countryName;
	private String operatorName;

	private Integer operatorId;//运营商id
	private Integer level;//运营商优先级

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
	
	public String getRoamMcc() {
		return roamMcc;
	}
	public void setRoamMcc(String roamMcc) {
		this.roamMcc = roamMcc;
	}
	
	public String getpPlmn() {
		return pPlmn;
	}
	public void setpPlmn(String pPlmn) {
		this.pPlmn = pPlmn;
	}
	
	public String getfPlmn() {
		return fPlmn;
	}
	public void setfPlmn(String fPlmn) {
		this.fPlmn = fPlmn;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "OperatorPreferService [id=" + id + ", operatorCode=" + operatorCode
				+ ", roamMcc=" + roamMcc + ", pPlmn=" + pPlmn + ", fPlmn="
				+ fPlmn + "]";
	}
	


}
