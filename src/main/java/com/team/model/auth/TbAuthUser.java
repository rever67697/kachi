package com.team.model.auth;

import java.io.Serializable;
import java.util.List;

/**
 * 创建日期：2018-1-1上午12:08:43
 * author:wuzhiheng
 */
public class TbAuthUser implements Serializable{
	
	private Integer id;

	private String userName;
	
	private String loginName;
	
	private String passWord;
	
	private Integer departmentId;
	
	private String departmentName;
	
	private List<TbAuthRole> roles;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<TbAuthRole> getRoles() {
		return roles;
	}

	public void setRoles(List<TbAuthRole> roles) {
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
