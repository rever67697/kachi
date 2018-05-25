package com.team.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**卡信息	m_simcard
 * 创建日期：2017-12-15下午2:52:39
 * author:wuzhiheng
 */
public class SimCard implements Serializable{

	private Integer id;//主键
	
	private Long imsi;
	
	private String number;//卡号
	
	private String iccid;
	
	private String pin;//PIN码
	
	private Integer operatorCode;//运营商编码
	
	private String mcNumber;//短信中心号码
	
	private Integer packageId;//流量套餐ID
	
	private Integer offPeriod;//账期日
	
	private Integer suStained;//账期持续时间：表示1个月；6：表示半年
	
	private String simMeProtocol;//协议类型:2g:sim;3g:usim
	
	private Integer status;//0：正常；1：拨出；3：停机
	
	private Integer cpStatus;//卡池状态，0：正常，1:待激活,2:拔出，8:超时
	
	private Integer cpId;//卡池id
	
	private Integer cpChannelId;//卡池通道编号
	
	private Integer countryCode;//国家编码
	
	private Integer provinceCode;//省编码

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date expiryDate;//有效期截卡时间

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date insertDate;//插入时间
	
	private Integer usedVpn;//是否支持vpn
	
	private String vpnIP;//IP
	
	private String vpnName;
	
	private String vpnPass;
	
	private Integer softType;//是否是软卡；0：实卡；1：软卡
	
	private String ki;
	
	private String opc;
	
	private String op;

	@JsonProperty("APN")
	private String APN;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date openDate;//开卡日期
	
	private Integer departmentId;//部门id
	
	private String groupPref;//分组
	
	private String note;//备注
	
	private String packageName;//卡套餐名称
	
	private String simPoolName;//卡池名称
	
	private String departmentName;//代理商名称
	
	private String operatorName;//运营商名称

	private String countryName;//国家

	private String provinceName;//省

	private String tempImei; //临时IMEI

	private Long residueFlow;//本国流量剩余额度

	private Long residueRoamFlow;//漫游流量剩余额度


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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Integer getOperatorCode() {
		return operatorCode!=null?operatorCode:0;
	}

	public void setOperatorCode(Integer operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getMcNumber() {
		return mcNumber;
	}

	public void setMcNumber(String mcNumber) {
		this.mcNumber = mcNumber;
	}

	public Integer getPackageId() {
		return packageId!=null?packageId:0;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getOffPeriod() {
		return offPeriod!=null?offPeriod:0;
	}

	public void setOffPeriod(Integer offPeriod) {
		this.offPeriod = offPeriod;
	}

	public Integer getSuStained() {
		return suStained!=null?suStained:0;
	}

	public void setSuStained(Integer suStained) {
		this.suStained = suStained;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCpStatus() {
		return cpStatus;
	}

	public void setCpStatus(Integer cpStatus) {
		this.cpStatus = cpStatus;
	}

	public Integer getCpId() {
		return cpId;
	}

	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}

	public Integer getCpChannelId() {
		return cpChannelId;
	}

	public void setCpChannelId(Integer cpChannelId) {
		this.cpChannelId = cpChannelId;
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getProvinceCode() {
		return provinceCode!=null?provinceCode:0;
	}

	public void setProvinceCode(Integer provinceCode) {
		this.provinceCode = provinceCode;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Integer getUsedVpn() {
		return usedVpn;
	}

	public void setUsedVpn(Integer usedVpn) {
		this.usedVpn = usedVpn;
	}

	public String getVpnName() {
		return vpnName;
	}

	public void setVpnName(String vpnName) {
		this.vpnName = vpnName;
	}

	public String getVpnPass() {
		return vpnPass;
	}

	public void setVpnPass(String vpnPass) {
		this.vpnPass = vpnPass;
	}

	public Integer getSoftType() {
		return softType;
	}

	public void setSoftType(Integer softType) {
		this.softType = softType;
	}

	public String getKi() {
		return ki;
	}

	public void setKi(String ki) {
		this.ki = ki;
	}

	public String getOpc() {
		return opc;
	}

	public void setOpc(String opc) {
		this.opc = opc;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getSimMeProtocol() {
		return simMeProtocol;
	}

	public void setSimMeProtocol(String simMeProtocol) {
		this.simMeProtocol = simMeProtocol;
	}

	public String getVpnIP() {
		return vpnIP;
	}

	public void setVpnIP(String vpnIP) {
		this.vpnIP = vpnIP;
	}

	public String getAPN() {
		return APN;
	}

	public void setAPN(String APN) {
		this.APN = APN;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Integer getDepartmentId() {
		return departmentId!=null?departmentId:0;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getGroupPref() {
		return groupPref;
	}

	public void setGroupPref(String groupPref) {
		this.groupPref = groupPref;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getSimPoolName() {
		return simPoolName;
	}

	public void setSimPoolName(String simPoolName) {
		this.simPoolName = simPoolName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getTempImei() {
		return tempImei;
	}

	public void setTempImei(String tempImei) {
		this.tempImei = tempImei;
	}

	public Long getResidueFlow() {
		return residueFlow;
	}

	public void setResidueFlow(Long residueFlow) {
		this.residueFlow = residueFlow;
	}

	public Long getResidueRoamFlow() {
		return residueRoamFlow;
	}

	public void setResidueRoamFlow(Long residueRoamFlow) {
		this.residueRoamFlow = residueRoamFlow;
	}

	@Override
	public String toString() {
		return "SimCard [id=" + id + ", imsi=" + imsi + ", number=" + number
				+ ", iccid=" + iccid + ", pin=" + pin + ", operatorCode="
				+ operatorCode + ", mcNumber=" + mcNumber + ", packageId="
				+ packageId + ", offPeriod=" + offPeriod + ", suStained="
				+ suStained + ", simMeProtocol=" + simMeProtocol + ", status="
				+ status + ", cpStatus=" + cpStatus + ", cpId=" + cpId
				+ ", cpChannelId=" + cpChannelId + ", countryCode="
				+ countryCode + ", provinceCode=" + provinceCode
				+ ", expiryDate=" + expiryDate + ", insertDate=" + insertDate
				+ ", usedVpn=" + usedVpn + ", vpnIP=" + vpnIP + ", vpnName="
				+ vpnName + ", vpnPass=" + vpnPass + ", softType=" + softType
				+ ", ki=" + ki + ", opc=" + opc + ", op=" + op + ", APN=" + APN
				+ ", openDate=" + openDate + ", departmentId=" + departmentId
				+ ", groupPref=" + groupPref + ", note=" + note+ ", tempImei=" + tempImei + "]";
	}
}
