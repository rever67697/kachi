package com.team.model;

import java.io.Serializable;

/**卡套餐信息	 m_simpackage
 * 创建日期：2017-12-15下午3:32:25
 * author:wuzhiheng
 */
public class SimPackage implements Serializable{
	
	private Integer id;//主键
	
	private String name;//套餐名称
	
	private Integer operatorCode;//运营商编号
	
	private Integer maxFlow;//本国流量
	
	private Integer maxRoamFlow;//漫游流量；0表示不限流量，如果在国内卡，表示省内流量
	
	private Integer status;//0：正常；1:删除
	
	private Integer level;//优先级
	
	private String mccs;//套餐支持的漫游国家，用,分隔，为空表示任何国家都可以使用

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

	public Integer getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(Integer operatorCode) {
		this.operatorCode = operatorCode;
	}

	public Integer getMaxFlow() {
		return maxFlow;
	}

	public void setMaxFlow(Integer maxFlow) {
		this.maxFlow = maxFlow;
	}

	public Integer getMaxRoamFlow() {
		return maxRoamFlow;
	}

	public void setMaxRoamFlow(Integer maxRoamFlow) {
		this.maxRoamFlow = maxRoamFlow;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getMccs() {
		return mccs;
	}

	public void setMccs(String mccs) {
		this.mccs = mccs;
	}
	
	
	
}
