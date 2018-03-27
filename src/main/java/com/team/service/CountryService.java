package com.team.service;

import java.util.Date;
import java.util.List;

import com.team.model.Country;
import com.team.vo.ResultList;

/**
 * 创建日期：2018-2-12下午4:13:14
 * author:wuzhiheng
 */
public interface CountryService {

	ResultList list(String nameCn,int page,int rows);

	ResultList getList(String mccs);

	Country getCountry(Integer CountryCode);
	
	String getRoamcountryDate(Date date, Integer countryCode, String format);
}
