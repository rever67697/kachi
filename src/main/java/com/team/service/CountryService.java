package com.team.service;

import java.util.Date;

import com.team.model.Country;

/**
 * 创建日期：2018-2-12下午4:13:14
 * author:wuzhiheng
 */
public interface CountryService {

	Country getCountry(Integer CountryCode);
	
	String getRoamcountryDate(Date date, Integer countryCode, String format);
}
