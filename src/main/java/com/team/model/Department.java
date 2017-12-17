package com.team.model;

import java.io.Serializable;

/**
 * 组织机构信息	m_department
 * 创建日期：2017-12-15下午6:00:27
 * author:wuzhiheng
 */
public class Department implements Serializable{

	private Integer id;//主键
	
	private String name;//部门名称（分组，相当于一个独立公司，终端和卡都是相互隔离的）
	
	private String abbr;//简称
	
	private Integer status;//状态，0：正常；1：删除
	
	private Integer parentId;//'上级部门Id
	
	private String note;//备注

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

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}
