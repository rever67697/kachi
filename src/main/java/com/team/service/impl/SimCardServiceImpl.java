package com.team.service.impl;

import java.util.*;

import com.hqrh.rw.common.model.GroupCacheSim;
import com.team.dao.FlowDayDao;
import com.team.dao.FlowMonthDao;
import com.team.dao.SimPackageDao;
import com.team.model.*;
import com.team.service.CountryService;
import com.team.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.schooner.MemCached.MemcachedItem;
import com.team.dao.SimCardDao;
import com.team.vo.OutlineInfo;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import com.team.service.OperatorService;
import com.team.service.SimCardService;
import com.team.service.SimGroupService;

import org.apache.log4j.Logger;

/**
 * 创建日期：2017-12-19上午10:04:29 author:wuzhiheng
 */
@SuppressWarnings("unchecked")
@Transactional
@Service
public class SimCardServiceImpl extends BaseService implements SimCardService {

	private Logger logger = Logger.getLogger(SimCardServiceImpl.class);
	// 组缓存
	//private static final Cache simGroupCache = CacheFactory.getCache(MConstant.MEM_SIM_GROUP);
	// 卡缓存
	private static final Cache simCache = CacheFactory .getCache(MConstant.MEM_SIM);
	// 卡最后月流量缓存
	private static final Cache simFlowCache = CacheFactory .getCache(MConstant.MEM_SIM_FlOW);

	@Autowired
	private SimCardDao simCardDao;
	@Autowired
	private OperatorService operatorService;
	@Autowired
	private SimGroupService simGroupService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private FlowMonthDao flowMonthDao;
	@Autowired
	private FlowDayDao flowDayDao;
	@Autowired
	private SimPackageDao simPackageDao;

	@Override
	/**
	 * 根据卡池id找出其上面的卡
	 */
	public ReturnMsg getSimCardByPool(Integer cpId) {
		List<SimCard> list = simCardDao.getSimCardByPool(cpId);
		ReturnMsg returnMsg = super.successTip();
		returnMsg.setData(new ResultList(list != null ? list.size() : 0, list));
		return returnMsg;
	}

	@Override
	/**
	 * 状态为作废的卡可以删除
	 */
	public ReturnMsg deleteSimCard(String ids) {
		String[] arr = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String string : arr) {
			list.add(Integer.valueOf(string));
		}
		int count = simCardDao.deleteSimCard(list);
		return count > 0 ? super.successTip() : super.errorTip();
	}

	@Override
	/**
	 * 根据条件寻找出sim卡列表
	 */
	public ResultList getSimCardList(Integer departmentId, Integer dId,
			Integer cpId, String number, Integer status, int page, int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("departmentId", departmentId);
		map.put("dId", CommonUtil.changeDepartmentId(dId));
		map.put("cpId", cpId);
		map.put("status", status);
		map.put("number", number);
		List<SimCard> list = simCardDao.getSimCardList(map);
		PageInfo<SimCard> pageInfo = new PageInfo<SimCard>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	@Override
	/**
	 * 查询流量卡总览信息
	 */
	public ReturnMsg getOutlineInfo(Integer dId) {
		ReturnMsg returnMsg = super.successTip();
		List<OutlineInfo> list = simCardDao.getOutlineInfo(CommonUtil
				.changeDepartmentId(dId));
		OutlineInfo info = null;
		if (list != null && list.size() > 0) {
			info = list.get(0);
			returnMsg.setData(info);
		}
		return returnMsg;
	}

	@Override
	/**
	 * isChangePeriod : 帐期参数是否发生变化
	 * isChangePackage : 套餐参数是否发生变化
	 */
	public ReturnMsg update(SimCard simCard, boolean isChangePeriod, boolean isChangePackage) {
		//1.更新
		simCardDao.update(simCard);

		//2.如果卡的帐期或者持续时间发生变化，需要重新计算卡的月流量
		reCalculateFlowMonth(simCard,isChangePeriod,isChangePackage);

		//3.需要更新缓存里面的卡组信息
		initGroupSim2Cache(simCard);
		return super.successTip();
	}

	private void reCalculateFlowMonth(SimCard simCard,boolean isChangePeriod, boolean isChangePackage) {
		//两者只要有一个是true就操作
		if(isChangePeriod || isChangePackage){
			FlowMonth flowMonth = getNowFlowMonth(simCard);
			//如果不为空则作处理
			if(flowMonth != null){
				convertFlowmonth(simCard,flowMonth,isChangePeriod,isChangePackage);
				flowMonthDao.updateMonthFlowBySimCard(flowMonth);
				//这里需要注意一下，所有有关获取或者设置缓存的，要确保两边的一致
				simFlowCache.set(MConstant.CACHE_FLOW_KEY_PREF + simCard.getImsi(),
						CommonUtil.convertBean(flowMonth, com.hqrh.rw.common.model.FlowMonth.class));
			}
		}

	}

	private void convertFlowmonth(SimCard simCard, FlowMonth flowMonth, boolean isChangePeriod, boolean isChangePackage) {
		Date nowDate = new Date();
		flowMonth.setLastUpDatetime(new Date());

		//1。如果帐期发生了变化，需要重新计算帐期相关参数
		if(isChangePeriod){
			int offperiod =simCard.getOffPeriod().intValue();
			String strOffperiod = "" + offperiod;
			if(offperiod < 10)
				strOffperiod = "0" + offperiod;
			Integer sustained = simCard.getSustained();

			String nowMonth = countryService.getRoamcountryDate(nowDate,simCard.getCountryCode(),DateUtil.RB_DATE_Y_M);
			Date offperiodDate = DateUtil.string2Date(nowMonth + "-" + strOffperiod, DateUtil.RB_DATE_Y_M_D);

			String strNowRoamDate = countryService.getRoamcountryDate(nowDate,simCard.getCountryCode(),DateUtil.RB_DATE_FORMATER);
			Date nowRoamDate =  DateUtil.string2Date(strNowRoamDate, DateUtil.RB_DATE_FORMATER);

			if(offperiodDate.before(nowRoamDate)) {//账期日小于当前时间，则以账期日为起始时间
				flowMonth.setAccountPeriodStartDate(offperiodDate);
				Date endDate = DateUtil.calculate(offperiodDate,GregorianCalendar.MONTH, sustained);
				flowMonth.setAccountPeriodEndDate(endDate);
			} else {
				flowMonth.setAccountPeriodEndDate(offperiodDate);
				Date startDate = DateUtil.calculate(offperiodDate, GregorianCalendar.MONTH, 0-sustained);
				flowMonth.setAccountPeriodStartDate(startDate);
			}

			String month = DateUtil.date2String(DateUtil.RB_DATE_Y_M, flowMonth.getAccountPeriodStartDate());
			flowMonth.setDate(month);
		}

		//2.如果套餐发生了变化，需要重新计算套餐的最大使用参数
		if(isChangePackage){
			SimPackage simPackage = simPackageDao.getPackage(simCard.getPackageId());
			flowMonth.setMaxFlow(simPackage.getMaxFlow());
			flowMonth.setMaxRoamFlow(simPackage.getMaxRoamFlow());
		}

		//3.获取当月已使用的流量
		Map<String,Object> map = new HashMap<>();
		map.put("imsi",simCard.getImsi());
		map.put("date",flowMonth.getAccountPeriodStartDate());
		FlowDay flowDay = flowDayDao.getUsedFlow(map);

		//4.重新计算本月的其他流量参数
		flowMonth.setUsedFlow(flowDay.getFlow());
		flowMonth.setUsedRoamFlow(flowDay.getRoamFlow());
		flowMonth.setResidueFlow(flowMonth.getMaxFlow()-flowMonth.getUsedFlow());
		flowMonth.setResidueRoamFlow(flowMonth.getMaxRoamFlow()-flowMonth.getUsedRoamFlow());
	}

	/**
	 * 初始化一张卡到卡组,
	 * 
	 * @param simCard
	 * @return
	 */
	public SimGroup initGroupSim2Cache(SimCard simCard) {
		if (simCard == null)
			return null;

		Long imsi = simCard.getImsi();
		Integer departmentId = simCard.getDepartmentId(); // 为0表示自有的
		Integer operatorCode = simCard.getOperatorCode();
		Integer provinceId = simCard.getProvinceCode(); // 为0表示全国卡
		Integer packageId = simCard.getPackageId();

		if (packageId == null || packageId <= 0) // 没有配置套餐，不初始化进缓存
			return null;

		int groupId = getGroupHashCode(imsi, operatorCode);
		String newKey = departmentId + MConstant.CONNECTOR + operatorCode
				+ MConstant.CONNECTOR + provinceId + MConstant.CONNECTOR
				+ packageId + MConstant.CONNECTOR + groupId;

		logger.debug("newKey: " + newKey);

		String oldKey = getGroupKeyByImsi(imsi);

		logger.debug("oldKey: " + newKey);

		SimGroup simGroup = null;

		if (oldKey != null && !oldKey.equals(newKey)) {
			boolean removed = removeSimBySimGroupCache(oldKey, imsi); // 删除旧的分组里的缓存
			if (!removed) {
				removed = removeSimBySimGroupCache(oldKey, imsi); // 如果缓存失败，重新删除一次
				if (!removed) {
					logger.error("removeSimBySimGroupCache is fail! oldKey: "
							+ oldKey + "/ imsi: " + imsi);
					return null;
				}
			}
			simGroup = addSim2SimGroupCache(newKey, simCard);
			if (simGroup == null) {
				simGroup = addSim2SimGroupCache(newKey, simCard);
				if (simGroup == null) {
					logger.error("addSim2SimGroupCache is fail! newKey: "
							+ newKey + "/ imsi: " + imsi);
					return null;
				}
			}
		} else {
			simGroup = addSim2SimGroupCache(newKey, simCard);
			if (simGroup == null) {
				simGroup = addSim2SimGroupCache(newKey, simCard);
				if (simGroup == null) {
					logger.error("oldKey is null,addSim2SimGroupCache is fail! newKey: "
							+ newKey + "/ imsi: " + imsi);
					return null;
				}
			}
		}
		return simGroup;
	}

	/**
	 * 根据IMSI算出SIM卡的卡组
	 * 
	 * @param imsi
	 * @param operatorCode
	 * @return
	 */
	public int getGroupHashCode(long imsi, Integer operatorCode) {
		Operator operator = operatorService.getOperator(operatorCode);
		int groupSize = 50;
		if (operator != null) {
			groupSize = operator.getGroupSize();
		} else {
			logger.error("getGroupHashCode, operator is null,operatorCode: "
					+ operatorCode);
		}

		try {
			Long groupId = imsi % groupSize;
			return groupId.intValue();
		} catch (NumberFormatException exception) {
			logger.error("getGroupHashCode is error, return 0");
		}
		return -1;
	}

	public String getGroupKeyByImsi(Long imsi) {
		String groupKey = null;
		SimGroup simGroup = simGroupService.getSimGroup(imsi);
		if (simGroup != null) {
			groupKey = simGroup.getGroupKey();
			logger.debug("select SimGroup by sql,tsid:" + imsi + "/" + simGroup);
		}
		return groupKey;
	}

	/**
	 * 删除卡组缓存中的卡对象，同时删除m_sim_group对应记录
	 * 
	 * @param groupKey
	 * @param imsi
	 * @return
	 */
	public boolean removeSimBySimGroupCache(String groupKey, Long imsi) {
		if (groupKey == null) {
			return true;
		}

		List<GroupCacheSim> groupCacheSims = null;

		MemcachedItem item = (MemcachedItem) simCache.gets(groupKey);
		if (item != null) {
			groupCacheSims = (List<GroupCacheSim>) item.getValue();
			if (groupCacheSims != null) {
				int size = groupCacheSims.size();
				for (int i = size; i > 0; i--) {
					GroupCacheSim gcs = CommonUtil.convertBean(groupCacheSims.get(i - 1),GroupCacheSim.class);
					if (gcs.getImsi() == imsi) { // 找到了要被删除的卡，从列表里去掉
						logger.info("removeSimBySimGroupCache,groupKey: "
								+ groupKey + "/ value:" + gcs);
						groupCacheSims.remove(gcs);
						long casUnique = item.casUnique;
						boolean update = simCache.cas(groupKey, groupCacheSims,
								casUnique);
						if (!update) { // 更新缓存失败，再来一次
							return false;
						}
					}
				}
			} else {
				logger.info("removeSimBySimGroupCache,groupKey: " + groupKey
						+ "/ value is null!");
			}
		} else {
			logger.info("removeSimBySimGroupCache,groupKey: " + groupKey
					+ "/ value is null!");
		}
		// 如果更新成功，删除数据库对应记录
		try {
			removeSimByDB(groupKey, imsi);
		} catch (Exception e) {
			logger.error("removeSimByDB is Fail!");
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * 在删除缓存的同时，从数据库m_sim_group
	 * 
	 * @param groupKey
	 * @param imsi
	 * @return
	 */
	public int removeSimByDB(String groupKey, Long imsi) throws Exception {
		int resultcode = 0;
		try {
			simGroupService.delete(groupKey, imsi);
			resultcode++;
		} catch (Exception e) {
			logger.error("----> removeSimByDB failed e : groupKey: " + groupKey
					+ "/ imsi:" + imsi + "/ " + e.getMessage());
			throw e;
		}
		return resultcode;
	}

	/**
	 * 添加SIM卡到组缓存
	 * 
	 * @param groupKey
	 * @return
	 */
	public SimGroup addSim2SimGroupCache(String groupKey, SimCard simCard) {
		if (groupKey == null) {
			return null;
		}
		long imsi = simCard.getImsi();

		SimGroup simGroup = null;

		List<GroupCacheSim> groupCacheSims = null;

		MemcachedItem item = simCache.gets(groupKey);
		if (item != null) {
			groupCacheSims = (List<GroupCacheSim>) item.getValue();
			if (groupCacheSims != null) {
				for (int i = 0; i < groupCacheSims.size(); i++) {
					//好鬼烦啊
					GroupCacheSim gcs = CommonUtil.convertBean(groupCacheSims.get(i),GroupCacheSim.class);
					System.out.println(gcs);
					if (gcs.getImsi() == imsi) { // 缓存有这张卡
						simGroup = getSimGroupByDB(imsi);
						if (simGroup == null) {
							simGroup = addSimGroup2DB(groupKey, simCard);
						}
						return simGroup;
					}
				}

				GroupCacheSim groupCacheSim = createGroupCacheSim(imsi);
				groupCacheSims.add(groupCacheSim);

				long casUnique = item.casUnique;
				boolean update = simCache.cas(groupKey, groupCacheSims,
						casUnique);
				if (!update) { // 更新缓存失败，再来一次
					return null;
				} else {
					simGroup = addSimGroup2DB(groupKey, simCard);
					return simGroup;
				}
			} else {
				groupCacheSims = new ArrayList<GroupCacheSim>();

				GroupCacheSim groupCacheSim = createGroupCacheSim(imsi);
				groupCacheSims.add(groupCacheSim);

				long casUnique = item.casUnique;
				boolean update = simCache.cas(groupKey, groupCacheSims,
						casUnique);
				if (!update) { // 更新缓存失败，再来一次
					return null;
				} else {
					simGroup = addSimGroup2DB(groupKey, simCard);
					return simGroup;
				}
			}
		} else {
			groupCacheSims = new ArrayList<GroupCacheSim>();

			GroupCacheSim groupCacheSim = createGroupCacheSim(imsi);
			groupCacheSims.add(groupCacheSim);

			boolean update = simCache.add(groupKey, groupCacheSims);
			if (!update) { // 更新缓存失败，再来一次
				return null;
			} else {
				simGroup = addSimGroup2DB(groupKey, simCard);
				return simGroup;
			}
		}
	}

	/**
	 * 根据imsi寻找simgroup
	 *@param imsi
	 *@return
	 *return
	 */
	public SimGroup getSimGroupByDB(Long imsi) {
		return simGroupService.getSimGroup(imsi);
	}
	
	/**
	 * 添加simgroup
	 *@param groupKey
	 *@param simCard
	 *@return
	 *return
	 */
	public SimGroup addSimGroup2DB(String groupKey,SimCard simCard) {
		SimGroup nSimGroup = new SimGroup();
		nSimGroup.setImsi(simCard.getImsi());
		nSimGroup.setGroupKey(groupKey);
		nSimGroup.setStatus(0);
		nSimGroup.setChargeDate(new Date());
		
		nSimGroup.setDepartmentId(simCard.getDepartmentId());
		nSimGroup.setPackageId(simCard.getPackageId());
		nSimGroup.setOperatorCode(simCard.getOperatorCode());
		nSimGroup.setProvinceCode(simCard.getProvinceCode());
		
		simGroupService.insert(nSimGroup);
		return nSimGroup;
	}
	
	/**
	 * 创建一个组缓存对象
	 * @param imsi
	 * @return
	 */
	public GroupCacheSim createGroupCacheSim(Long imsi) {
		GroupCacheSim groupCacheSim = new GroupCacheSim();
		groupCacheSim.setImsi(imsi);
		groupCacheSim.setStatus(0);
		return groupCacheSim;
	}

	/**
	 * 获取当前卡的月流量
	 * @param simCard
	 * @return
	 */
	public FlowMonth getNowFlowMonth(SimCard simCard) {
		if(simCard == null)
			return null;
		long imsi = simCard.getImsi();

		//心跳时间对应的当地时间
		String timeStr =  countryService.getRoamcountryDate(new Date(),simCard.getCountryCode(),"yyyy-MM-dd HH:mm:ss");
		Date nowDate = DateUtil.string2Date(timeStr, "yyyy-MM-dd HH:mm:ss");

		//这里需要注意一下，所有有关获取或者设置缓存的，要确保两边的一致
		Object object =  simFlowCache.get(MConstant.CACHE_FLOW_KEY_PREF + imsi);
		FlowMonth simFlowMonth = null;
		if(object != null) {
			simFlowMonth = CommonUtil.convertBean(object,FlowMonth.class);
			if(simFlowMonth.getAccountPeriodStartDate().before(nowDate)
					&& simFlowMonth.getAccountPeriodEndDate().after(nowDate)) {
				return simFlowMonth;
			}
		}


		simFlowMonth = getNowFlowMonthByDB(imsi,nowDate);
		if(simFlowMonth != null) {
			//simFlowCache.set(MConstant.CACHE_FLOW_KEY_PREF + imsi, simFlowMonth);
			return simFlowMonth;
		}

		return simFlowMonth;
	}

	private FlowMonth getNowFlowMonthByDB(long imsi, Date nowDate) {
		Map<String,Object> map = new HashMap<>();
		map.put("imsi",imsi);
		map.put("nowDate",nowDate);
		return flowMonthDao.get(map);
	}
}
