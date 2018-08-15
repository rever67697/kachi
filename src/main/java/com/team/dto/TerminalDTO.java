package com.team.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午5:13 2018/7/19
 */
public class TerminalDTO implements Serializable {

    private Integer id;//主键

    private Integer tsid;//编号

    private String mac;

    private String model;//终端型号

    private String batch;//生产批次

    private String sVersion;//软件版本

    private String key;//License

    private Integer status;//0:正常；1：未初始化；2：停用；3：注销

    private Integer upLog;//上传日志：0：不上传；1上传

    private String imei;

    private Integer activated;//是否已经激活：0：未激活；1：已激活

    private Integer homeLocation;//归属地，国家编码

    private String ssid;

    private String wifiPassword;//WiFi密码

    private String licFix;//LIC后缀

    private Integer usedVpn;//是否支持VPN

    private Integer usedSoft;//是否支持软卡

    private Integer departmentId;//部门ID

    private String sendWiFiPass;//发下给终端更新的WiFi密码

    private String meid;//WCDMA的MEID

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    private Integer dayspeedlimit;

    private Integer maxdaydata;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTsid() {
        return tsid;
    }

    public void setTsid(Integer tsid) {
        this.tsid = tsid;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getsVersion() {
        return sVersion;
    }

    public void setsVersion(String sVersion) {
        this.sVersion = sVersion;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUpLog() {
        return upLog;
    }

    public void setUpLog(Integer upLog) {
        this.upLog = upLog;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public Integer getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(Integer homeLocation) {
        this.homeLocation = homeLocation;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getWifiPassword() {
        return wifiPassword;
    }

    public void setWifiPassword(String wifiPassword) {
        this.wifiPassword = wifiPassword;
    }

    public String getLicFix() {
        return licFix;
    }

    public void setLicFix(String licFix) {
        this.licFix = licFix;
    }

    public Integer getUsedVpn() {
        return usedVpn;
    }

    public void setUsedVpn(Integer usedVpn) {
        this.usedVpn = usedVpn;
    }

    public Integer getUsedSoft() {
        return usedSoft;
    }

    public void setUsedSoft(Integer usedSoft) {
        this.usedSoft = usedSoft;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getSendWiFiPass() {
        return sendWiFiPass;
    }

    public void setSendWiFiPass(String sendWiFiPass) {
        this.sendWiFiPass = sendWiFiPass;
    }

    public String getMeid() {
        return meid;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getDayspeedlimit() {
        return dayspeedlimit;
    }

    public void setDayspeedlimit(Integer dayspeedlimit) {
        this.dayspeedlimit = dayspeedlimit;
    }

    public Integer getMaxdaydata() {
        return maxdaydata;
    }

    public void setMaxdaydata(Integer maxdaydata) {
        this.maxdaydata = maxdaydata;
    }
}
