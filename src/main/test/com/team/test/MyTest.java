package com.team.test;


import java.lang.reflect.Field;
import java.util.*;

import com.hqrh.rw.common.model.GroupCacheSim;
import com.schooner.MemCached.MemcachedItem;
import com.team.dao.SimCardDao;
import com.team.model.SimCard;
import com.team.model.SimPackage;
import com.team.service.SimCardService;
import com.team.util.CommonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
	private static final Cache simFlowCache = CacheFactory .getCache(MConstant.MEM_SIM_FlOW);
	private static final Cache simCache = CacheFactory .getCache(MConstant.MEM_SIM);


	@Autowired
	private SimPoolService service;
	@Autowired
	private CountryService countryService;
	@Autowired
	private SimCardService simCardService;

	@Autowired
	private SimCardDao simCardDao;

	@Test
	public void query(){
		Map<String,Object> map = new HashMap<>();
		map.put("number","262014546463587");
		List<Map<String,Object>> list = simCardDao.getSimCardListMap(map);
		Field[] fields = SimCard.class.getDeclaredFields();

		for (Map<String, Object> stringObjectMap : list) {
			for (Field field : fields) {
				System.out.println(field.getName() + " = " + stringObjectMap.get(field.getName().toUpperCase()));
			}
//			for (Map.Entry<String, Object> stringObjectEntry : stringObjectMap.entrySet()) {
//				System.out.println(stringObjectEntry.getKey() + " = " + stringObjectEntry.getValue());
//			}
			break;
		}
	}
	
	@Test
	public void testMemcached(){
		String[] a = new String[]{"1","2","3"};
		Boolean ok = simGroupCache.set("wzh", a);
		System.out.println(ok.toString());
		a = (String[])simGroupCache.gets("wzh").getValue();
		
		System.out.println(Arrays.toString(a));
	}

	@Test
	public void testTmp(){
//		FlowMonth flowMonth1 = new FlowMonth();
//		flowMonth1.setId(10);
//		Boolean ok = simFlowCache.set("FLOW_4600112343434",flowMonth1);
//		System.out.println(ok);
//		Object obj = simFlowCache.get("FLOW_4600112343434");
//
//		FlowMonth2 flowMonth = new FlowMonth2();
////		BeanUtils.copyProperties(flowMonth,obj);
//		flowMonth = CommonUtil.convertBean(obj,FlowMonth2.class);
//		System.out.println(flowMonth);
		List<GroupCacheSim> groupCacheSims = null;

		MemcachedItem item = (MemcachedItem)simCache.gets("null_46001_29_10_4");
		if (item != null) {
			groupCacheSims = (List<GroupCacheSim>) item.getValue();
			if (groupCacheSims != null) {
				for (GroupCacheSim sg : groupCacheSims) {
					System.out.println(sg);
				}
			}
		}

	}
	
	//@Test
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

	@Test
	public void testSimGroup(){
		MemcachedItem m  = simCache.gets("0_46004_0_30_9");
		List<GroupCacheSim> list = (List<GroupCacheSim>) m.getValue();
		for (GroupCacheSim groupCacheSim : list) {
			System.out.println(groupCacheSim);
		}
	}

	@Test
	public void releaseCard(){
		simCardService.updateGroupSim2Cache(simCardDao.getByImsi(460040458106549L),0);

	}

	@Test
	public void testGetSimcard(){
//		Object object = simGroupCache.get("SIM_460011498632926");
		Object object = simCache.get("FLOW_DAY_460111103004282");

		System.out.println(object);

		com.hqrh.rw.common.model.SimCard simCard = new com.hqrh.rw.common.model.SimCard();

//		BeanUtils.copyProperties(object,simCard);
		System.out.println(CommonUtil.convertBean(object, com.hqrh.rw.common.model.SimCard.class));
		System.out.println(CommonUtil.convertBean(object, SimCard.class));
	}

	@Test
	public void testGetSimpackage(){
		Object object = simCache.get(MConstant.CACHE_PACKAGE_KEY_PREF+"30");
		SimPackage simPackage = CommonUtil.convertBean(object, SimPackage.class);
		System.out.println(1);

	}
}
