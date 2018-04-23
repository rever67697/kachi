package com.hqrh.rw.common.model;

import java.io.Serializable;


public class OperatorPrefer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3857558033084074591L;
	private int id;
	private int operatorCode;
	private String roamMcc;
	private String pPlmn;
	private String fPlmn;
	
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
	
	
	@Override
	public String toString() {
		return "OperatorPreferService [id=" + id + ", operatorCode=" + operatorCode
				+ ", roamMcc=" + roamMcc + ", pPlmn=" + pPlmn + ", fPlmn="
				+ fPlmn + "]";
	}
	


}
