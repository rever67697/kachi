package com.hqrh.rw.common.model;

import java.io.Serializable;


public class GroupKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7879375242447886988L;
	private int id;
	private String groupKey;
	private int departmentId;
	private int operatorCode;
	private int provinceCode;
	private int packageId;
	private int groupId;
	
	public GroupKey(){
		
	};
	
	public GroupKey(String groupKey, int departmentId, int operatorCode, int provinceCode, int packageId, int groupId) {
		this.groupKey = groupKey;
		this.departmentId = departmentId;
		this.operatorCode = operatorCode;
		this.provinceCode = provinceCode;
		this.packageId = packageId;
		this.groupId = groupId;
	}
	
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
	public int getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(int operatorCode) {
		this.operatorCode = operatorCode;
	}
	
	public int getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(int provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}
		
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	
	@Override
	public String toString() {
		return "SimGroup [id=" + id + ", groupKey=" + groupKey 
				+ ", departmentId=" + departmentId + ", operatorCode="
				+ operatorCode + ", provinceCode=" + provinceCode
				+ ", packageId=" + packageId + ", groupId=" + groupId + "]";
	}
	



}
