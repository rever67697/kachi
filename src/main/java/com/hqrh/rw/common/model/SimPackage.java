package com.hqrh.rw.common.model;

import java.io.Serializable;


public class SimPackage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1838211821434658145L;
	private int id;
	private String name;
	private int operatorCode;
	private  int maxFlow;
	private int maxRoamFlow;
	private int status;
	private String mccs;
	private int level;
	
	private int departmentId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(int operatorCode) {
		this.operatorCode = operatorCode;
	}
	
	public int getMaxFlow() {
		return maxFlow;
	}
	public void setMaxFlow(int maxFlow) {
		this.maxFlow = maxFlow;
	}
	
	public int getMaxRoamFlow() {
		return maxRoamFlow;
	}
	public void setMaxRoamFlow(int maxRoamFlow) {
		this.maxRoamFlow = maxRoamFlow;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	public String getMccs() {
		return mccs;
	}

	public void setMccs(String mccs) {
		this.mccs = mccs;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "SimPackage [id=" + id + ", name=" + name + ", operatorCode="
				+ operatorCode + ", maxFlow=" + maxFlow + ", maxRoamFlow="
				+ maxRoamFlow + ", status=" + status + ", mccs=" + mccs
				+ ", level=" + level + ", departmentId=" + departmentId + "]";
	}


	




}
