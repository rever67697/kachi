package com.team.test;


import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.schooner.MemCached.MemcachedItem;
import com.team.model.SimCard;
import com.team.model.SimPool;
import com.team.service.CountryService;
import com.team.service.SimPoolService;
import com.team.util.Cache;
import com.team.util.CacheFactory;
import com.team.util.MConstant;

/**
 * 创建日期：2017-12-16下午10:44:00
 * author:wuzhiheng
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {
	
	//组缓存
	private static final Cache simGroupCache =CacheFactory.getCache(MConstant.MEM_SIM_GROUP);
	
	@Autowired
	private SimPoolService service;
	@Autowired
	private CountryService countryService;
	
	@Test
	public void testMemcached(){
		String[] a = new String[]{"1","2","3"};
		
		Boolean ok = simGroupCache.set("wzh", a);
		System.out.println(ok.toString());
		a = (String[])simGroupCache.gets("wzh").getValue();
		
		System.out.println(Arrays.toString(a));
	}
	
	@Test
	public void testTX(){
		SimPool simPool = new SimPool();
		simPool.setName("wzh");
		service.saveSimPool(simPool);
	}
	
	@Test
	public void testDate(){
		//System.out.println(countryService.getRoamcountryDate(new Date(), 156, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(countryService.getRoamcountryDate(new Date(), 840, "yyyy-MM-dd HH:mm:ss"));
	}
}
