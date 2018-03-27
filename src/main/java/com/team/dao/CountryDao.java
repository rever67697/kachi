package com.team.dao;

import com.team.model.Country;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 创建日期：2018-2-12下午4:11:59
 * author:wuzhiheng
 */
public interface CountryDao {

	List<Country> list(@Param("nameCn") String nameCn);

	List<Country> getSelected(String[] list);

	Country getCountry(Integer CountryCode);
	
}
