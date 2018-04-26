package com.team.model;

import java.io.Serializable;



public class Country implements Serializable {

	private static final long serialVersionUID = -3446617493967510669L;
	private Integer id;
	private Integer countryCode;
	private String countryName; 
	private String areaCode; //区号
	private Integer continentCode;  //洲
	private String timeZone; //时区
	private Double longItude;
	private Double latItude;
	private String nameEn;
	private String nameCn;
	private Integer status;

	private Integer mcc;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Integer getContinentCode() {
		return continentCode;
	}
	public void setContinentCode(Integer continentCode) {
		this.continentCode = continentCode;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public Double getLongItude() {
		return longItude;
	}
	public void setLongItude(Double longItude) {
		this.longItude = longItude;
	}
	public Double getLatItude() {
		return latItude;
	}
	public void setLatItude(Double latItude) {
		this.latItude = latItude;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getNameCn() {
		return nameCn;
	}
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMcc() {
		return mcc;
	}

	public void setMcc(Integer mcc) {
		this.mcc = mcc;
	}
}
