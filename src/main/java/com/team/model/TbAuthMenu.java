package com.team.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TbAuthMenu {
	private int id;
	private int parentId;
	private String name;
	private String url;
	private int orderNum;
	private String iconCls;
	private Map<String, Object> attributes = new HashMap<String, Object>();
	private List<TbAuthMenu> children = new ArrayList<TbAuthMenu>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getname() {
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
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
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
	
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public TbAuthMenu() {
	}
	public TbAuthMenu(int id, int parentId, String name, String url, int orderNum,String iconCls,
			Map<String, Object> attributes, List<TbAuthMenu> children) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.url = url;
		this.orderNum = orderNum;
		this.attributes = attributes;
		this.children = children;
		this.iconCls = iconCls;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", parentId=" + parentId + ", name=" + name
				+ ", url=" + url + ", orderNum=" + orderNum + ", iconCls="
				+ iconCls + ", attributes=" + attributes + ", children="
				+ children + "]";
	}
	
	
	
	
}
