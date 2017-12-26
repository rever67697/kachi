package com.team.model;

import java.io.Serializable;
import java.util.Date;

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
	
	private Integer sustained;//账期持续时间：表示1个月；6：表示半年
	
	private String simmeProtocol;//议类型:2g:sim;3g:usim
	
	private Integer status;//0：正常；1：拨出；3：停机
	
	private Integer cpStatus;//卡池状态:0:正常；1：拔出
	
	private Integer cpId;//卡池id
	
	private Integer cpChannelId;//卡池通道编号
	
	private Integer countryCode;//国家编码
	
	private Integer provinceCode;//省编码
	
	private Date expiryDate;//有效期截卡时间
	
	private Date insertDate;//插入时间
	
	private Integer usedVpn;//是否支持vpn
	
	private String vpnIp;//IP
	
	private String vpnName;
	
	private String vpnPass;
	
	private Integer softType;//是否是软卡；0：实卡；1：软卡
	
	private String ki;
	
	private String opc;
	
	private String op;
	
	private String apn;
	
	private Date openDate;//开卡日期
	
	private Integer departmentId;//部门id
	
	private String groupPref;//分组
	
	private String note;//备注

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
		return operatorCode;
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
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getOffPeriod() {
		return offPeriod;
	}

	public void setOffPeriod(Integer offPeriod) {
		this.offPeriod = offPeriod;
	}

	public Integer getSustained() {
		return sustained;
	}

	public void setSustained(Integer sustained) {
		this.sustained = sustained;
	}

	public String getSimmeProtocol() {
		return simmeProtocol;
	}

	public void setSimmeProtocol(String simmeProtocol) {
		this.simmeProtocol = simmeProtocol;
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
		return provinceCode;
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

	public String getVpnIp() {
		return vpnIp;
	}

	public void setVpnIp(String vpnIp) {
		this.vpnIp = vpnIp;
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

	public String getApn() {
		return apn;
	}

	public void setApn(String apn) {
		this.apn = apn;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Integer getDepartmentId() {
		return departmentId;
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

	@Override
	public String toString() {
		return "SimCard [id=" + id + ", imsi=" + imsi + ", number=" + number
				+ ", iccid=" + iccid + ", pin=" + pin + ", operatorCode="
				+ operatorCode + ", mcNumber=" + mcNumber + ", packageId="
				+ packageId + ", offPeriod=" + offPeriod + ", sustained="
				+ sustained + ", simmeProtocol=" + simmeProtocol + ", status="
				+ status + ", cpStatus=" + cpStatus + ", cPid=" + cpId
				+ ", cpChannelId=" + cpChannelId + ", countryCode="
				+ countryCode + ", provinceCode=" + provinceCode
				+ ", expiryDate=" + expiryDate + ", insertDate=" + insertDate
				+ ", usedVpn=" + usedVpn + ", vpnIp=" + vpnIp + ", vpnName="
				+ vpnName + ", vpnPass=" + vpnPass + ", softType=" + softType
				+ ", ki=" + ki + ", opc=" + opc + ", op=" + op + ", apn=" + apn
				+ ", openDate=" + openDate + ", departmentId=" + departmentId
				+ ", groupPref=" + groupPref + ", note=" + note + "]";
	}
	
	
	
}
