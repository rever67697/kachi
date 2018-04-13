package com.hqrh.rw.common.model;

import java.io.Serializable;
import java.util.Date;


public class SimCard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7657756623557722785L;
	
	
	//STATUS	int	11	0：正常；1:停用; 2：指定;3:待激活；4：作废

	public static int STATUS_OK = 0;
	public static int STATUS_STOP = 1;
	public static int STATUS_READY = 2;
	public static int STATUS_WAIT = 3;
	public static int STATUS_CANCEL = 4;
	
	
	//CPSTATUS	int	11	卡池状态，0：正常，1:待激活,2:拔出，3:超时, 4卡信息错误；6：初始化卡信息
	public static int CPSTATUS_OK = 0;
	public static int CPSTATUS_WAIT = 1;
	public static int CPSTATUS_PULLOUT = 2;
	public static int CPSTATUS_TIMEOUT = 3;
	public static int CPSTATUS_FAULT = 4; //卡信息错误
	public static int CPSTATUS_INIT = 6; //初始化卡信息

	private int id;
	private long imsi;
	private String number;
	private String iccid;
	private String pin;
	private int operatorCode;
	private String mcNumber;
	private int packageId;
	private int offPeriod; //账期日
	private int suStained;  //账期持续时间：表示1个月；6：表示半年
	private String simMeProtocol; //类型:2G:sim;3G:usim
	private int status; //0：正常；2：指定、3：停机、4：作废
	private int cpStatus; //卡池状态:0:正常；1：拔出
	private int cpId; 
	private int cpChannelId;
	private int countryCode;
	private int provinceCode = 0;
	private Date  expiryDate; //有效期截止时间
	private Date insertDate;  //插入时间	
	private int usedVpn;   //是否支持VPN	
	private String vpnIP; 
	private String vpnName;
	private String vpnPass;
	private int softType; //0:实卡；1：软卡
	private String ki;
	private String opc;
	private String op;
	private String APN;
	private Date openDate;
	private Integer departmentId;  //代理商
	private String groupPref;  //分组
	private String note;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public long getImsi() {
		return imsi;
	}
	public void setImsi(long imsi) {
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
	
	public int getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(int operatorCode) {
		this.operatorCode = operatorCode;
	}
	
	public String getMcNumber() {
		return mcNumber;
	}
	public void setMcNumber(String mcNumber) {
		this.mcNumber = mcNumber;
	}
	
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	
	public int getOffPeriod() {
		return offPeriod;
	}
	public void setOffPeriod(int offPeriod) {
		this.offPeriod = offPeriod;
	}
	
	public int getSuStained() {
		return suStained;
	}
	public void setSuStained(int suStained) {
		this.suStained = suStained;
	}
	
	public String getSimMeProtocol() {
		return simMeProtocol;
	}
	public void setSimMeProtocol(String simMeProtocol) {
		this.simMeProtocol = simMeProtocol;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getCpStatus() {
		return cpStatus;
	}
	public void setCpStatus(int cpStatus) {
		this.cpStatus = cpStatus;
	}
	
	public int getCpId() {
		return cpId;
	}
	public void setCpId(int cpId) {
		this.cpId = cpId;
	}
	
	public int getCpChannelId() {
		return cpChannelId;
	}
	public void setCpChannelId(int cpChannelId) {
		this.cpChannelId = cpChannelId;
	}
	
	public int getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
	
	public int getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(int provinceCode) {
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
	
	public int getUsedVpn() {
		return usedVpn;
	}
	public void setUsedVpn(int usedVpn) {
		this.usedVpn = usedVpn;
	}
	
	public String getVpnIP() {
		return vpnIP;
	}
	public void setVpnIP(String vpnIP) {
		this.vpnIP = vpnIP;
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
	
	public int getSoftType() {
		return softType;
	}
	public void setSoftType(int softType) {
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
	
	public String getAPN() {
		return APN;
	}
	public void setAPN(String aPN) {
		APN = aPN;
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
				+ ", groupPref=" + groupPref + ", note=" + note + "]";
	} 
	

}
