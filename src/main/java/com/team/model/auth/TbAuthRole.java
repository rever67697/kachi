package com.team.model.auth;

import java.io.Serializable;

/**
 * 创建日期：2018-1-18下午4:39:35
 * author:wuzhiheng
 */
public class TbAuthRole implements Serializable{

	private Integer id;
	
	private String name;
	
	private String code;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
