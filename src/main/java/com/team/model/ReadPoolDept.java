package com.team.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 预分配卡池
 * 创建日期：2017-12-26下午9:03:48
 * author:wuzhiheng
 */
public class ReadPoolDept implements Serializable{

	private Integer id;
	
	private Integer spid;//卡池编号
	
	private Integer departmentId;
	
	private Date operatorTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSpid() {
		return spid;
	}

	public void setSpid(Integer spid) {
		this.spid = spid;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	
	
}
