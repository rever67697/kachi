package com.team.model;

import java.io.Serializable;
import java.util.Date;

/**SIM卡分组数据，用于选卡 	m_sim_group
 * 创建日期：2017-12-15下午3:25:36
 * author:wuzhiheng
 */
public class SimGroup implements Serializable{
	
	private Integer id;//主键
	
	private String groupKey;//卡组key
	
	private Long imsi;
	
	private Integer status;//状态
	
	private Date changeDate;//加载时间
	
	private Integer departmentId;//代理商Id
	
	private Integer operatorCode;//运营商编号
	
	private Integer provinceCode;//省编码
	
	private Integer packageId;//套餐ID
	
	private Integer groupId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupKey() {
		return groupKey;
	}

	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}

	public Long getImsi() {
		return imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(Integer operatorCode) {
		this.operatorCode = operatorCode;
	}

	public Integer getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(Integer provinceCode) {
		this.provinceCode = provinceCode;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	
}
