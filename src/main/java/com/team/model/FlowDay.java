package com.team.model;

import java.io.Serializable;
import java.util.Date;

/**卡的日流量	m_flow_day
 * 创建日期：2017-12-15下午3:09:47
 * author:wuzhiheng
 */
public class FlowDay implements Serializable{

	private Integer id;//主键
	
	private Long imsi;//
	
	private Date date;//消费日期
	
	private Integer flow;//本国流量，单位
	
	private Integer roamFlow;//漫游流量（国内是省内流量）
	
	private Date lastUpdateTime;//最后更新时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getImsi() {
		return imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getFlow() {
		return flow;
	}

	public void setFlow(Integer flow) {
		this.flow = flow;
	}

	public Integer getRoamFlow() {
		return roamFlow;
	}

	public void setRoamFlow(Integer roamFlow) {
		this.roamFlow = roamFlow;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	
}
