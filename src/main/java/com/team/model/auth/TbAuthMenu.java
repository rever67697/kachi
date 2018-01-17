package com.team.model.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TbAuthMenu implements Serializable{
	private Integer id;
	private Integer parentId;
	private String name;
	private String url;
	private Integer orderNum;
	private String iconCls;
	private Integer isMenu;
	private String funDesc;
	
	private Map<String, Object> attributes = new HashMap<String, Object>();
	private List<TbAuthMenu> children = new ArrayList<TbAuthMenu>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Integer getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}

	public String getFunDesc() {
		return funDesc;
	}

	public void setFunDesc(String funDesc) {
		this.funDesc = funDesc;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<TbAuthMenu> getChildren() {
		return children;
	}

	public void setChildren(List<TbAuthMenu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", parentId=" + parentId + ", name=" + name
				+ ", url=" + url + ", orderNum=" + orderNum + ", iconCls="
				+ iconCls + ", attributes=" + attributes + ", children="
				+ children + "]";
	}
	
	
	
	
}
