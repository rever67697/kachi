package com.team.model.auth;

import java.io.Serializable;

/**
 * 创建日期：2018-1-1上午12:08:43
 * author:wuzhiheng
 */
public class TbAuthUser implements Serializable{

	private String userName;
	
	private String loginName;
	
	private String passWord;
	
	private Integer departmentId;

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
	
}
