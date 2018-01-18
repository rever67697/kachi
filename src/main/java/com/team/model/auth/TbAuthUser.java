package com.team.model.auth;

import java.io.Serializable;
import java.util.List;

/**
 * 创建日期：2018-1-1上午12:08:43
 * author:wuzhiheng
 */
public class TbAuthUser implements Serializable{
	
	private Integer id;

	private String name;
	
	private String passWord;
	
	private String phoneNumber;
	
	private String sex; 
	
	private Integer departmentId;
	
	private String departmentName;
	
	private List<TbAuthRole> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
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

}
