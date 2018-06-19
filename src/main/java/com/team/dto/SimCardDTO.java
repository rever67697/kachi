package com.team.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description : 这个实体类主要使用作simcard批量更新时作参数的判断，用Integer不用int，可以判断前端有没有传参数
 * @Date Created in 上午9:19 2018/4/13
 */
public class SimCardDTO {


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

    private Integer cpStatus;//卡池状态:0:正常；1：拔出

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

    private String APN;

    private Integer cStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    public Integer getSuStained() {
        return suStained;
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

    public Integer getcStatus() {
        return cStatus;
    }

    public void setcStatus(Integer cStatus) {
        this.cStatus = cStatus;
    }
}
