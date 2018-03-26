package com.team.service.impl;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.vo.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.dao.CountryDao;
import com.team.model.Country;
import com.team.service.CountryService;
import com.team.util.DateUtil;
import com.team.util.MConstant;

/**
 * 创建日期：2018-2-12下午4:14:22
 * author:wuzhiheng
 */
@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countryDao;

    @Override
    public ResultList list(String nameCn, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<Country> list = countryDao.list(nameCn);
		PageInfo<Country> pageInfo = new PageInfo<Country>(list);
		return new ResultList(pageInfo.getTotal(), list);
    }

    @Override
	public Country getCountry(Integer CountryCode) {
		return countryDao.getCountry(CountryCode);
	}

	/**
	 * 获取所在地的当前时间
	 */
	@Override
	public String getRoamcountryDate(Date date, Integer countryCode,
			String format) {
		String roamTimezone = MConstant.CHINA_TIMEZONE;
		
		if(156 != countryCode) { //如果是在中国，不需要额外转换时间
			Country country = getCountry(countryCode);
			roamTimezone = country.getTimeZone();
		}
		Date roamerDate =DateUtil.dateChangeTimezone(date,MConstant.CHINA_TIMEZONE,roamTimezone);
		String roamerDateStr =DateUtil.date2String(format,roamerDate);
		return roamerDateStr;
	}

}
