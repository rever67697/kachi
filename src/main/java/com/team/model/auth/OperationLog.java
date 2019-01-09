package com.team.model.auth;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 创建日期：2018-1-26下午10:24:04
 * author:wuzhiheng
 */
public class OperationLog implements Serializable{
	
	private Integer id;
	
	private String username;
	
	private Integer departmentid;
	
	private String ip;

	private String browser;
	
	private String bussinesstype;
	
	private String operation;
	
	private String desc;
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createtime;

	/**
	 * 
	 */
	public OperationLog() {
		super();
	}

	/**
	 * @param username
	 * @param bussinesstype
	 * @param operation
	 * @param desc
	 */
	public OperationLog(final String username, final String bussinesstype,
			final String operation,final String desc,final Integer departmentid,final String ip,final String browser) {
		super();
		this.username = username;
		this.bussinesstype = bussinesstype;
		this.operation = operation;
		this.desc = desc;
		this.departmentid = departmentid;
		this.ip = ip;
		this.browser = browser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBussinesstype() {
		return bussinesstype;
	}

	public void setBussinesstype(String bussinesstype) {
		this.bussinesstype = bussinesstype;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
}
