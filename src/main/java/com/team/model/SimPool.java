package com.team.model;

import java.io.Serializable;

/**
 * 卡池基本信息，由卡池接入系统时自动上报	m_simpool
 * 创建日期：2017-12-15下午2:45:30
 * author:wuzhiheng
 */
public class SimPool implements Serializable{
	
	private Integer id;//主键
	
	private Integer spid;//卡池编号
	
	private String ip;//卡池IP
	
	private Integer type;//卡池类型
	
	private Integer isActive;//是否是活动状态
	
	private Integer departmentId;//部门Id
	
	private String mac;
	
	private String version;//版本
	
	private String name;//名字
	
	private String inIp;//SUM Controller内网IP
	
	private String outIp;//SUM Controller外网IP
	
	private Integer port;//端口
	
	private String sumIp;//卡池内网IP
	
	private Integer webPort;//卡池显示端口
	
	private String lionVersion;//Controller软件版本
	
	private String departmentName;//代理商名称，bean额外加的，为了方便在页面上显示

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInIp() {
		return inIp;
	}

	public void setInIp(String inIp) {
		this.inIp = inIp;
	}

	public String getOutIp() {
		return outIp;
	}

	public void setOutIp(String outIp) {
		this.outIp = outIp;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getSumIp() {
		return sumIp;
	}

	public void setSumIp(String sumIp) {
		this.sumIp = sumIp;
	}

	public Integer getWebPort() {
		return webPort;
	}

	public void setWebPort(Integer webPort) {
		this.webPort = webPort;
	}

	public String getLionVersion() {
		return lionVersion;
	}

	public void setLionVersion(String lionVersion) {
		this.lionVersion = lionVersion;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "SimPool{" +
				"id=" + id +
				", spid=" + spid +
				", ip='" + ip + '\'' +
				", type=" + type +
				", isActive=" + isActive +
				", departmentId=" + departmentId +
				", mac='" + mac + '\'' +
				", version='" + version + '\'' +
				", name='" + name + '\'' +
				", inIp='" + inIp + '\'' +
				", outIp='" + outIp + '\'' +
				", port=" + port +
				", sumIp='" + sumIp + '\'' +
				", webPort=" + webPort +
				", lionVersion='" + lionVersion + '\'' +
				", departmentName='" + departmentName + '\'' +
				'}';
	}
}
