package com.team.test;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import com.hqrh.rw.common.model.GroupCacheSim;
import com.schooner.MemCached.MemcachedItem;
import com.team.dao.SimCardDao;
import com.team.model.FlowMonth;
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
	private static final Cache simGroupCache = CacheFactory.getCache(MConstant.MEM_SIM_GROUP);
	private static final Cache simFlowCache = CacheFactory.getCache(MConstant.MEM_SIM_FlOW);
	private static final Cache simCache = CacheFactory.getCache(MConstant.MEM_SIM);
	private static final Cache publicCache = CacheFactory.getCache(MConstant.MEM_PUBLIC);


	@Autowired
	private SimPoolService service;
	@Autowired
	private CountryService countryService;
	@Autowired
	private SimCardService simCardService;

	@Autowired
	private SimCardDao simCardDao;

	@Test
	public void query() {
		Map<String, Object> map = new HashMap<>();
		map.put("number", "262014546463587");
		List<Map<String, Object>> list = simCardDao.getSimCardListMap(map);
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
	public void testMemcached() {
		SimCard simCard = new SimCard();
		simCard.setImsi(1111111L);
		simCard.setPackageId(100);
		Boolean ok = simCache.set(MConstant.CACHE_SIM_KEY_PREF + simCard.getImsi(),
				CommonUtil.convertBean(simCard, com.hqrh.rw.common.model.SimCard.class),new Date(System.currentTimeMillis()+1000*60*60*24*24));
		System.out.println(ok.toString());

	}

	@Test
	public void testTmp() {
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

		MemcachedItem item = (MemcachedItem) simCache.gets("null_46001_29_10_4");
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
	public void testTX() {
		SimPool simPool = new SimPool();
		simPool.setName("wzh");
		service.saveSimPool(simPool);
	}

	@Test
	public void testDate() {
		//System.out.println(countryService.getRoamcountryDate(new Date(), 156, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(countryService.getRoamcountryDate(new Date(), 840, "yyyy-MM-dd HH:mm:ss"));
	}

	@Test
	public void testSimGroup() {
		String imsi = "460040458106201";
		MemcachedItem m = simCache.gets("11_46004_0_31_1");
		if(m!=null){
			List<GroupCacheSim> list = (List<GroupCacheSim>) m.getValue();
			for (GroupCacheSim groupCacheSim : list) {
				//if((Long.valueOf(groupCacheSim.getImsi()).toString().equals(imsi))){
					System.out.println(groupCacheSim);
				//}
			}
		}

		Object object = simGroupCache.get("SIM_"+imsi);

		System.out.println(CommonUtil.convertBean(object, com.hqrh.rw.common.model.SimCard.class));
		System.out.println(CommonUtil.convertBean(object, SimCard.class));
	}

	@Test
	public void releaseCard() {
		simCardService.updateGroupSim2Cache(simCardDao.getByImsi(4600112343434L), 0);

	}

	@Test
	public void testGetSimcard() {
		Object object = simGroupCache.get("SIM_460042341604162");

		System.out.println(CommonUtil.convertBean(object, com.hqrh.rw.common.model.SimCard.class));
		System.out.println(CommonUtil.convertBean(object, SimCard.class));
	}

	@Test
	public void testGetSimpackage() {
		Object object = simCache.get(MConstant.CACHE_PACKAGE_KEY_PREF + "24");
		SimPackage simPackage = CommonUtil.convertBean(object, SimPackage.class);
		com.hqrh.rw.common.model.SimPackage simPackage2 = CommonUtil.convertBean(object, com.hqrh.rw.common.model.SimPackage.class);
		System.out.println(simPackage);
		System.out.println(simPackage2);

	}

	@Test
	public void testFlushAllCache(){
		simCache.clear();
	}

	@Test
	public void testGetPool(){
		Object object = simCache.get(MConstant.CACHE_SUM_PREF+"400");

	}

	@Test
	public void testFlowMonth() {
		String imsi = "460016767614238";
		Object object = simCache.get(MConstant.CACHE_FLOW_KEY_PREF+imsi);

		System.out.println(object);

	}

	@Test
	public void testGetNowFlowMonth() {
		String imsi = "460016767614238";
		SimCard simCard = simCardDao.getByImsi(Long.valueOf(imsi));
		FlowMonth flowMonth = simCardService.getNowFlowMonth(simCard);

		System.out.println(flowMonth);

	}

	@Test
	public void testGetPackage(){
		Integer packageId=20;
		Object object = simCache.get(MConstant.CACHE_PACKAGE_KEY_PREF+packageId);
		System.out.println(object);
	}

	@Test
	public void testGetOperator(){
		Object object = publicCache.get(MConstant.CACHE_OPERATOR_KEY_PREE+46000);
		System.out.println(object);
	}

	public static void main(String[] args) throws Exception{
		StringBuffer sb = new StringBuffer();
		HttpURLConnection conn = null;
		URL url = new URL("http://localhost:10086/kachiManagerment/q?imsi=1");
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		try{
			conn.connect();
			InputStream in = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			String temp = "";
			while((temp = br.readLine()) != null){
				sb.append(temp);
			}
			br.close();
			isr.close();
			in.close();
			conn.disconnect();
			System.out.println(sb.toString());
		}catch (Exception e){
			System.out.println("网络连接失败！");
		}

	}

}


