package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqrh.rw.common.model.GroupCacheSim;
import com.hqrh.rw.common.model.GroupKey;
import com.schooner.MemCached.MemcachedItem;
import com.team.dao.*;
import com.team.dto.SimCardDTO;
import com.team.model.*;
import com.team.service.*;
import com.team.util.*;
import com.team.vo.OutlineInfo;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 创建日期：2017-12-19上午10:04:29 author:wuzhiheng
 */
@SuppressWarnings("unchecked")
@Transactional
@Service
public class SimCardServiceImpl extends BaseService implements SimCardService {

	private Logger logger = Logger.getLogger(SimCardServiceImpl.class);
	// 组缓存
	private static final Cache simGroupCache = CacheFactory.getCache(MConstant.MEM_SIM_GROUP);
	// 卡缓存
	private static final Cache simCache = CacheFactory .getCache(MConstant.MEM_SIM);
	// 卡最后月流量缓存
	private static final Cache simFlowCache = CacheFactory .getCache(MConstant.MEM_SIM_FlOW);

	private static final Cache publicCache = CacheFactory .getCache(MConstant.MEM_PUBLIC);

	private ExportExcelUtil<SimCard> exportExcelUtil = new ExportExcelUtil();

	@Autowired
	private SimCardDao simCardDao;
	@Autowired
	private OperatorService operatorService;
	@Autowired
	private SimGroupService simGroupService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private GroupKeyService groupKeyService;
	@Autowired
	private FlowMonthDao flowMonthDao;
	@Autowired
	private FlowDayDao flowDayDao;
	@Autowired
	private SimPackageDao simPackageDao;
	@Autowired
	private TerminalSimDao terminalSimDao;

	/**
	 * 根据卡池id找出其上面的卡
	 */
	@Override
	public ReturnMsg getSimCardByPool(Integer cpId) {
		Map<String,Object> map = new HashMap<>();
		map.put("cpId",cpId);
		map.put("dId",CommonUtil.getDId());
		List<SimCard> list = simCardDao.getSimCardByPool(map);
		ReturnMsg returnMsg = super.successTip();
		returnMsg.setData(new ResultList(list != null ? list.size() : 0, list));
		return returnMsg;
	}

	/**
	 * 状态为作废的卡可以删除
	 * @param ids
	 * @return
	 */
	@Override
	public ReturnMsg deleteSimCard(String ids) {
		String[] arr = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String string : arr) {
			list.add(Integer.valueOf(string));
		}
		//获取卡列表
		List<SimCard> simCardList = simCardDao.getByIds(list);

		//删除卡
		simCardDao.deleteSimCard(list);

		//删除卡组缓存
		if(CommonUtil.listNotBlank(simCardList)){
			for (SimCard simCard : simCardList) {

				String groupKey = getGroupKeyByImsi(simCard.getImsi());

				removeSimBySimGroupCache(groupKey,simCard.getImsi());
				//删除卡缓存
				simCache.remove(MConstant.CACHE_SIM_KEY_PREF + simCard.getImsi());
			}
		}

		return super.successTip();
	}

	/**
	 * 页面查询列表
	 */
	@Override
	public ResultList getSimCardList(SimCardDTO simCard,Integer dateType,Integer outDate,Date startDate,Date endDate,Integer forAppoint, Integer cpStatus,int page,int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("departmentId", simCard.getDepartmentId());
		map.put("dId", CommonUtil.getDId());
		map.put("cpId", simCard.getCpId());
		map.put("status", simCard.getStatus());
		map.put("imsi", simCard.getImsi());
		map.put("countryCode",simCard.getCountryCode());
		map.put("operatorCode",simCard.getOperatorCode());
		map.put("cStatus",simCard.getcStatus());
		map.put("dateType",dateType);
		map.put("outDate",outDate);
		map.put("startDate",startDate);
		map.put("endDate",endDate);
		map.put("packageId",simCard.getPackageId());
		map.put("iccid",simCard.getIccid());
		map.put("forAppoint",forAppoint);
		map.put("cpStatus",cpStatus);
		List<SimCard> list = simCardDao.getSimCardList(map);

		PageInfo<SimCard> pageInfo = new PageInfo<SimCard>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	/**
	 * 页面导出excel
	 */
	@Override
	public File getCsv(SimCardDTO simCard,Integer dateType,Integer outDate, Date startDate, Date endDate,Integer cpStatus) throws  Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("departmentId", simCard.getDepartmentId());
		map.put("dId", CommonUtil.getDId());
		map.put("cpId", simCard.getCpId());
		map.put("status", simCard.getStatus());
		map.put("imsi", simCard.getImsi());
		map.put("countryCode",simCard.getCountryCode());
		map.put("operatorCode",simCard.getOperatorCode());
		map.put("cStatus",simCard.getcStatus());
		map.put("dateType",dateType);
		map.put("outDate",outDate);
		map.put("startDate",startDate);
		map.put("endDate",endDate);
		map.put("packageId",simCard.getPackageId());
		map.put("iccid",simCard.getIccid());
		map.put("cpStatus",cpStatus);


		Field[] fields = SimCard.class.getDeclaredFields();
//		List<Map<String,Object>> list = simCardDao.getSimCardListMap(map);
		List<SimCard> list = simCardDao.getSimCardList(map);
		File parentFile = new File("csv");
		if(!parentFile.exists()){
			parentFile.mkdir();
		}
		File file = new File(parentFile,System.currentTimeMillis()+".xls");

		OutputStream out = new FileOutputStream(file);

		String[] headers = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			headers[i] = fields[i].getName();
		}
		exportExcelUtil.exportExcel("卡列表",headers,list,out,"yyyy-MM-dd HH:mm:ss");

//		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"GBK"));
////		FileWriter writer = new FileWriter(file);
//		for (Field field : fields) {
//			writer.write("\""+field.getName()+"\",");
//		}
//		writer.write("\r\n");
//		if(CommonUtil.listNotBlank(list)){
//			for (Map<String, Object> stringObjectMap : list) {
//				for (Field field : fields) {
//					if (stringObjectMap.get(field.getName().toUpperCase()) != null){
//
//						String value = stringObjectMap.get(field.getName().toUpperCase()).toString();
//						boolean quoteFlag = false;//标记是否添加过双引号
//
//						if(value.contains("\"")){ //若发现有双引号  需要将字符串中的一个双引号替换为两个 并且需前后加双引号
//							value = value.replaceAll("\"","\"\"");
//							value = "\"" + value + "\"";
//							quoteFlag = true;
//						}
//						if(value.contains(",") && !quoteFlag){ //若发现有逗号  需前后加引号
//							value = "\"" + value + "\"";
//						}
//						writer.write(value+",");
//					}else{
//						writer.write("\" \",");
//					}
//				}
//				writer.write("\r\n");
//			}
//		}
//		writer.flush();
//		writer.close();

		out.close();
		return  file;
	}

	/**
	 * 获取某个部门的总体卡信息概览
	 */
	@Override
	public ReturnMsg getOutlineInfo() {
		ReturnMsg returnMsg = super.successTip();
		List<OutlineInfo> list = simCardDao.getOutlineInfo(CommonUtil.getDId());
		OutlineInfo info = null;
		if (list != null && list.size() > 0) {
			info = list.get(0);
			returnMsg.setData(info);
		}
		return returnMsg;
	}

	/**
	 * 页面更新单个卡信息
	 */
	@Override
	public ReturnMsg update(SimCard simCard) {

		SimCard oldSimCard = simCardDao.getById(simCard.getId());

		//1.更新
		simCardDao.update(simCard);

		//2.如果卡的帐期或者持续时间发生变化，需要重新计算卡的月流量
		reCalculateFlowMonth(oldSimCard,simCard.getOffPeriod(),simCard.getSuStained(),simCard.getPackageId());

		//3.需要更新缓存里面的卡组信息
		//4.更新卡缓存
		updateSimCardFromCache(simCard,false);

		//如果修改了卡状态，改为了停用或者作废，需要把卡组状态设置为1
		if((simCard.getStatus() == 1 || simCard.getStatus() ==4 ) && !simCard.getStatus().equals(oldSimCard.getStatus()) ){
			refreshCacheStatus(simCard);
		}

		return super.successTip();
	}


	/**
	 * 初始化一张卡到卡组,
	 */
	private SimGroup initGroupSim2Cache(SimCard simCard) {
		if (simCard == null)
			return null;

		Long imsi = simCard.getImsi();
		int departmentId = simCard.getDepartmentId(); // 为0表示自有的
		int operatorCode = simCard.getOperatorCode();
		int provinceId = simCard.getProvinceCode(); // 为0表示全国卡
		int packageId = simCard.getPackageId();

		if (packageId <= 0) // 没有配置套餐，不初始化进缓存
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
			simGroup = addSim2SimGroupCache(newKey, simCard,groupId);
			if (simGroup == null) {
				simGroup = addSim2SimGroupCache(newKey, simCard,groupId);
				if (simGroup == null) {
					logger.error("addSim2SimGroupCache is fail! newKey: "
							+ newKey + "/ imsi: " + imsi);
					return null;
				}
			}
		} else {
			simGroup = addSim2SimGroupCache(newKey, simCard,groupId);
			if (simGroup == null) {
				simGroup = addSim2SimGroupCache(newKey, simCard,groupId);
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
	 * 页面批量更新卡，只更新某几个字段
	 */
    @Override
    public ReturnMsg batchUpdate(SimCardDTO simCardDTO, String ids) {
		if(checkParams(simCardDTO)){
			//1.更新数据
			Map<String,Object> map = new HashMap<>();
			map.put("status",simCardDTO.getStatus());
			map.put("packageId",simCardDTO.getPackageId());
			map.put("offPeriod",simCardDTO.getOffPeriod());
			map.put("suStained",simCardDTO.getSuStained());
			map.put("provinceCode",simCardDTO.getProvinceCode());
			map.put("expiryDate",simCardDTO.getExpiryDate());
			map.put("usedVpn",simCardDTO.getUsedVpn());
			map.put("softType",simCardDTO.getSoftType());
			map.put("openDate",simCardDTO.getOpenDate());
			map.put("departmentId",simCardDTO.getDepartmentId());
			map.put("note",simCardDTO.getNote());

			List<Integer> list = new ArrayList<>();
			for (String s : ids.split(",")) {
				list.add(Integer.valueOf(s));
			}
			map.put("list",list);

			//获取更新前的simcard列表
			List<SimCard> oldSimCardList = simCardDao.getByIds(list);

			//批量更新siamcard
			simCardDao.batchUpdate(map);

			//如果卡的账期参数发生变化，需要重新计算本月流量
			for (SimCard simCard : oldSimCardList) {
				reCalculateFlowMonth(simCard,
						simCardDTO.getOffPeriod()!=null?simCardDTO.getOffPeriod():0,
						simCardDTO.getSuStained()!=null?simCardDTO.getSuStained():0,
						simCardDTO.getPackageId()!=null?simCardDTO.getPackageId():0);

			}

			//刷新缓存
			//获取更新后的simcard列表
			List<SimCard> simCardList = simCardDao.getByIds(list);

			for (SimCard simCard : simCardList) {

				//更新卡缓存和卡组缓存
				updateSimCardFromCache(simCard,false);
			}
		}

        return successTip();
    }

	/**
	 * 如果卡的账期参数或者套餐发生变化，需要重新计算本月流量
	 * @param simCard 更新前的simcard
	 */
	private void reCalculateFlowMonth(SimCard simCard, int offPeriod, int sustained, int packageId) {

		//计算账期是否改变，套餐是否改变
		boolean isChangePeriod = offPeriod!=simCard.getOffPeriod() && offPeriod!=0;

		boolean isChangeSustained = sustained!=simCard.getSuStained() && sustained!=0;

		boolean isChangePackage = packageId!=simCard.getPackageId() && packageId!=0;

		if(isChangePeriod || isChangeSustained || isChangePackage){
			//获取当前卡对应的月流量信息
			simCard.setPackageId(packageId);
			FlowMonth flowMonth = getNowFlowMonth(simCard);
			//如果为空则不处理
			if (flowMonth != null){
				Date nowDate = new Date();
				flowMonth.setLastUpDatetime(new Date());

				//1。如果帐期发生了变化，需要重新计算帐期相关参数
				if(isChangePeriod || isChangeSustained){

					int offperiod =isChangePeriod?offPeriod:simCard.getOffPeriod();
					if(offPeriod==0)
						offPeriod=1;
					String strOffperiod = "" + offperiod;
					if(offperiod < 10)
						strOffperiod = "0" + offperiod;
					sustained = isChangeSustained?sustained:simCard.getSuStained();

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
					SimPackage simPackage = simPackageDao.getOne(packageId);
					flowMonth.setMaxFlow(simPackage.getMaxFlow());
					flowMonth.setMaxRoamFlow(simPackage.getMaxRoamFlow());
				}

				//3.获取当月已使用的流量
				Map<String,Object> map = new HashMap<>();
				map.put("imsi",simCard.getImsi());
				map.put("startDate",flowMonth.getAccountPeriodStartDate());
				map.put("endDate",flowMonth.getAccountPeriodEndDate());
				FlowDay flowDay = flowDayDao.getUsedFlow(map);

				//4.重新计算本月的其他流量参数
				flowMonth.setUsedFlow(flowDay.getFlow());
				flowMonth.setUsedRoamFlow(flowDay.getRoamFlow());
				flowMonth.setResidueFlow(flowMonth.getMaxFlow()-flowMonth.getUsedFlow());
				flowMonth.setResidueRoamFlow(flowMonth.getMaxRoamFlow()-flowMonth.getUsedRoamFlow());

				flowMonthDao.updateMonthFlowBySimCard(flowMonth);
				//这里需要注意一下，所有有关获取或者设置缓存的，要确保两边的一致
				simFlowCache.set(MConstant.CACHE_FLOW_KEY_PREF + simCard.getImsi(),
						CommonUtil.convertBean(flowMonth, com.hqrh.rw.common.model.FlowMonth.class));
			}
		}
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
//						groupCacheSims.remove(gcs);
						groupCacheSims.remove(i-1);//TODO  这里不能直接删除，因为不是同一个对象了，要根据索引下标删除
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
	 * 如果分组的Key还没有使用，添加groupKey到数据库
	 * @param key
	 * @param simCard
	 * @param groupId
	 */
	public void addGroupKey(String key, SimCard simCard, int groupId) {
		if(key == null || simCard == null) {
			logger.error("addGroupKey is Error, groupKey: "+ key + "/simCard: " + simCard );
			return;
		}

		GroupKey groupKey =  groupKeyService.getGroupKey(key);
		if(groupKey == null) {
//			(String groupKey,int departmentId,int operatorCode,int provinceCode,int packageId,int groupId
			groupKey = new GroupKey(key,simCard.getDepartmentId(),simCard.getOperatorCode()
					,simCard.getProvinceCode(),simCard.getPackageId(),groupId);
			groupKeyService.addGroupKey(groupKey);
			logger.info("addGroupKey 2 db: " + groupKey);
		}
	}

	/**
	 * 添加SIM卡到组缓存
	 * 
	 * @param groupKey
	 * @return
	 */
	public SimGroup addSim2SimGroupCache(String groupKey, SimCard simCard,int groupId) {
		if (groupKey == null) {
			return null;
		}

		//如果分组的Key还没有使用，添加到组Key表
		addGroupKey(groupKey,simCard,groupId);

		long imsi = simCard.getImsi();

		SimGroup simGroup = null;

		List<GroupCacheSim> groupCacheSims = null;

		MemcachedItem item = simCache.gets(groupKey);
		if (item != null) {
			groupCacheSims = (List<GroupCacheSim>) item.getValue();
			if (groupCacheSims != null) {
				//TODO 修改了接口
				for (int i = 0; i < groupCacheSims.size(); i++) {
					//好鬼烦啊
					GroupCacheSim sg = CommonUtil.convertBean(groupCacheSims.get(i),GroupCacheSim.class);
					System.out.println("addSim2SimGroupCache-->"+sg);
					if (sg.getImsi() == imsi) { // 缓存有这张卡
						simGroup = getSimGroupByDB(imsi);
						if (simGroup == null) {
							simGroup = addSimGroup2DB(groupKey, simCard,groupId);
						}

						int usedSim = 0;
						TerminalSim  terminalSim = getTerminalSimBySim(imsi);
						if(terminalSim != null) {
							usedSim = 1;
						}
						if(usedSim != sg.getStatus()) {
							sg.setStatus(usedSim);
							long casUnique = item.casUnique;
							logger.debug("update groupCacheSim 1:  " + sg);

							//TODO  修改了接口
							groupCacheSims.remove(i);
							groupCacheSims.add(sg);

							boolean update = simGroupCache.cas(groupKey, groupCacheSims, casUnique);
							if(!update) {
								logger.error("update groupCacheSim fail! " + sg);
							}
						}else {
							logger.debug("update groupCacheSim 1-1:  " + sg);
						}

						return simGroup;
					}
				}

				GroupCacheSim groupCacheSim = createGroupCacheSim(imsi);
				groupCacheSims.add(groupCacheSim);

				long casUnique = item.casUnique;
				logger.debug("update groupCacheSim 2:  " + groupCacheSim);
				boolean update = simCache.cas(groupKey, groupCacheSims,casUnique);
				if (!update) { // 更新缓存失败，再来一次
					logger.error("update groupCacheSim fail! " + groupCacheSim);
					return null;
				} else {
					simGroup = addSimGroup2DB(groupKey, simCard,groupId);
					return simGroup;
				}
			} else {
				groupCacheSims = new ArrayList<GroupCacheSim>();

				GroupCacheSim groupCacheSim = createGroupCacheSim(imsi);
				groupCacheSims.add(groupCacheSim);

				long casUnique = item.casUnique;
				boolean update = simCache.cas(groupKey, groupCacheSims, casUnique);
				if (!update) { // 更新缓存失败，再来一次
					logger.error("update groupCacheSim fail! " + groupCacheSim);
					return null;
				} else {
					simGroup = addSimGroup2DB(groupKey, simCard,groupId);
					return simGroup;
				}
			}
		} else {
			groupCacheSims = new ArrayList<GroupCacheSim>();

			GroupCacheSim groupCacheSim = createGroupCacheSim(imsi);
			groupCacheSims.add(groupCacheSim);

			boolean update = simCache.add(groupKey, groupCacheSims);
			if (!update) { // 更新缓存失败，再来一次
				logger.debug("update groupCacheSim 3:  " + groupCacheSim);
				return null;
			} else {
				simGroup = addSimGroup2DB(groupKey, simCard,groupId);
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
	public SimGroup addSimGroup2DB(String groupKey,SimCard simCard,int groupId) {
		SimGroup nSimGroup = new SimGroup();
		nSimGroup.setImsi(simCard.getImsi());
		nSimGroup.setGroupKey(groupKey);
		nSimGroup.setStatus(0);
		nSimGroup.setChargeDate(new Date());
		
		nSimGroup.setDepartmentId(simCard.getDepartmentId());
		nSimGroup.setPackageId(simCard.getPackageId());
		nSimGroup.setOperatorCode(simCard.getOperatorCode());
		nSimGroup.setProvinceCode(simCard.getProvinceCode());
		nSimGroup.setGroupId(groupId);

		simGroupService.insert(nSimGroup);
		return nSimGroup;
	}

	/**
	 * 创建一个组缓存对象
	 * @param imsi
	 * @return
	 */
	public GroupCacheSim createGroupCacheSim(long imsi) {
		GroupCacheSim groupCacheSim = new GroupCacheSim();
		groupCacheSim.setImsi(imsi);

		TerminalSim  terminalSim = getTerminalSimBySim(imsi);
		if(terminalSim == null) {
			groupCacheSim.setStatus(0);
		}else {
			groupCacheSim.setStatus(1);
		}
		return groupCacheSim;
	}

	/**
	 * 获取当前卡的月流量
	 * @param simCard
	 * @return
	 */
	@Override
	public FlowMonth getNowFlowMonth(SimCard simCard) {
		if(simCard == null)
			return null;
		long imsi = simCard.getImsi();

		//心跳时间对应的当地时间
		String timeStr =  countryService.getRoamcountryDate(new Date(),simCard.getCountryCode(),"yyyy-MM-dd HH:mm:ss");
		Date nowDate = DateUtil.string2Date(timeStr, "yyyy-MM-dd HH:mm:ss");

		//这里需要注意一下，所有有关获取或者设置缓存的，要确保两边的一致
//		Object object =  simFlowCache.get(MConstant.CACHE_FLOW_KEY_PREF + imsi);
		FlowMonth simFlowMonth = null;
//		if(object != null) {
//			//TODO 修改了接口
//			simFlowMonth = CommonUtil.convertBean(object,FlowMonth.class);
//			if(simFlowMonth.getAccountPeriodStartDate().before(nowDate)
//					&& simFlowMonth.getAccountPeriodEndDate().after(nowDate)) {
//				return simFlowMonth;
//			}
//		}


		simFlowMonth = getNowFlowMonthByDB(imsi,nowDate);
		if(simFlowMonth != null) {
			//simFlowCache.set(MConstant.CACHE_FLOW_KEY_PREF + imsi, simFlowMonth);
			return simFlowMonth;
		}

		//如果在当前账期找不到月流量，从套餐创建一个月流量记录。
		if(simCard != null) {
			SimPackage simPackage = simPackageDao.getOne(simCard.getPackageId());
			if(simPackage != null) {

				simFlowMonth = createSimFlowMonth(simCard, simPackage);
			}
		}

		return simFlowMonth;
	}

	/**
	 * 页面上传excel中读取卡列表
	 * @param file
	 * @return
	 */
	@Override
	public ReturnMsg getSimcardList(MultipartFile file) {
		ReturnMsg returnMsg = super.successTip();
		Workbook book = null;
		Sheet sheet = null;
		Row row = null;
		List<SimCard> list = new ArrayList<SimCard>();
		try {
			book = WorkbookFactory.create(file.getInputStream());
			sheet = book.getSheetAt(0);
			int total = sheet.getLastRowNum()+1;
			SimCard simCard = null;

			for (int i = 1; i < total; i++) {
				row = sheet.getRow(i);
				if(row == null || row.getCell(0) == null){
					continue;
				}
				try {
					Long imsi = CommonUtil.getCellLongVal(row.getCell(1));
					String tempImei = CommonUtil.getCellStringVal(row.getCell(38));

					//处理长整数读取成科学计算法格式问题
					if(tempImei.indexOf(".")>0 && tempImei.indexOf("E")>0){
						tempImei = CommonUtil.getCellLongVal(row.getCell(38)).toString();
					}

					simCard  = new SimCard(imsi,tempImei);

					list.add(simCard);
				} catch (Exception e) {
					returnMsg = super.errorTip();
					returnMsg.setMsg("第"+(row.getRowNum()+1)+"行数据格式错误，请检查！");
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			returnMsg = super.errorTip();
			returnMsg.setMsg("无效的文件");
			e.printStackTrace();
		}finally{
			returnMsg.setData(list);
			return returnMsg;
		}
	}

	/**
	 * 页面批量更新tempImei
	 * @param list
	 */
	@Override
	public void batchUpdateTempImei(List<SimCard> list) {
		for (SimCard simCard : list) {
			//查库找出数据库里面的这张卡
			SimCard nowSimCard = simCardDao.getByImsi(simCard.getImsi());
			if(nowSimCard!=null){
				nowSimCard.setTempImei(simCard.getTempImei());
				//更新临时IMEI
				simCardDao.updateTempImei(nowSimCard);
				//刷新缓存
				updateSimCardFromCache(nowSimCard,false);
			}
		}
	}

    private FlowMonth getNowFlowMonthByDB(long imsi, Date nowDate) {
		Map<String,Object> map = new HashMap<>();
		map.put("imsi",imsi);
		map.put("nowDate",nowDate);
		return flowMonthDao.get(map);
	}


	/**
	 * 更新SIM卡状态的占用状态,0:空闲；1：占用
	 * @param simCard
	 * @return
	 */
	@Override
	public boolean updateGroupSim2Cache(SimCard simCard,int status) {

		if(simCard == null)
			return false;

		String groupKey = getGroupKeyByImsi(simCard.getImsi());
		if(groupKey != null) {
			Object obj =simGroupCache.gets(groupKey);
			if(obj!=null){
				logger.debug("get the simGroup: "+ groupKey + "/ " + obj);
				MemcachedItem item =(MemcachedItem) obj;
				if(item==null)
					logger.warn(groupKey + "'s MemcachedItem is null");
				long casUnique = item.casUnique;
				List<GroupCacheSim>  groupSims =(List<GroupCacheSim>) item.getValue();
				if(groupSims==null)
					logger.debug("------------------->SimpleSimInfoList is null");
				GroupCacheSim dtoValue = getCacheImsiInGroup(groupSims,simCard.getImsi());
				if(dtoValue != null) {
					logger.info("-------------> before " + dtoValue.getImsi() +"'s status: " + dtoValue.getStatus() +  "/asUnique: " + item.casUnique);
					dtoValue.setStatus(status);
					groupSims.add(dtoValue);
					boolean cas = simGroupCache.cas(groupKey,groupSims,casUnique);
					if(cas){
						logger.debug("------------------------>update cache success,groupKey:" + groupKey + "/ groupSims: " + groupSims);
						return  true;
					}
					else{
						logger.warn("updateGroupSim2Cache fail! " + groupKey + " cas conflict: " + item.casUnique +", the simCard : " +simCard);
					}
				}else {
					logger.warn("get GroupCacheSim is null, groupKey: " + groupKey + "/imsi: " + simCard.getImsi() + "/groupSims: " +  groupSims);
				}
			}else {
				logger.warn("get SimGroup by Cache is null, groupKey: " + groupKey);
			}
		}else {
			logger.warn("get groupKey by DB is null,  simCard: " + simCard);
		}
		initGroupSim2Cache(simCard);
		return false;
	}

	/**
	 * 从缓存组找到SIM卡
	 * @param simGroup
	 * @param imsi
	 * @return
	 */
	public GroupCacheSim getCacheImsiInGroup(List<GroupCacheSim> simGroup,long imsi){

		if(simGroup ==null || imsi <=0){
			logger.error("simGroup or imsi is null" + ",imsi:" + imsi);
		}

		for (int i = 0; i < simGroup.size(); i++) {
			//好鬼烦啊
			GroupCacheSim gcs = CommonUtil.convertBean(simGroup.get(i),GroupCacheSim.class);
			System.out.println(gcs);
			if (gcs!=null && gcs.getImsi() == imsi) { // 缓存有这张卡
				simGroup.remove(i);
				return gcs;
			}
		}

//		for(GroupCacheSim ssi : simGroup) {
//			if(ssi != null && imsi == ssi.getImsi()) {
//				return ssi;
//			}
//		}
		return null;
	}

	public TerminalSim getTerminalSimBySim(long imsi) {
		TerminalSim tSim = null;

		List<TerminalSim> tSimList = terminalSimDao.getTerminalSimByImsi(imsi);
		if (tSimList.size() != 0) {

			if(tSimList.size() > 1) {
				logger.error("get TerminalSim >1 ,imsi: " + imsi);
			}
			tSim = tSimList.get(0);

		}
		logger.debug("select TerminalSim by sql,imsi:"+ imsi + "/" + tSim);

		return tSim;

	}

	/**
	 * 用于在simcard批量更新功能上的判断，判断是否有相关字段的变化
	 * @param simCard
	 * @return
	 */
	private boolean checkParams(SimCardDTO simCard){
		if(simCard.getStatus()!=null || simCard.getPackageId()!=null || simCard.getSuStained()!=null
				|| simCard.getExpiryDate()!=null || simCard.getOpenDate()!=null || simCard.getProvinceCode()!=null
				|| simCard.getOffPeriod()!=null || simCard.getUsedVpn()!=null || simCard.getSoftType()!=null
				|| simCard.getDepartmentId()!=null || !CommonUtil.StringIsNull(simCard.getNote())){
			return true;
		}
		return false;
	}


	private FlowMonth createSimFlowMonth(SimCard simCard,SimPackage simPackage) {

		Date nowDate = new Date();

		FlowMonth simFlowMonth = new FlowMonth();
		simFlowMonth.setImsi(simCard.getImsi());
		simFlowMonth.setIccid(simCard.getIccid());
		simFlowMonth.setNumber(simCard.getNumber());
		simFlowMonth.setOperatorCode(simCard.getOperatorCode());
		simFlowMonth.setMaxFlow(simPackage.getMaxFlow());
		simFlowMonth.setUsedFlow(0);
		simFlowMonth.setResidueFlow(simPackage.getMaxFlow());
		simFlowMonth.setMaxRoamFlow(simPackage.getMaxRoamFlow());
		simFlowMonth.setUsedRoamFlow(0);
		simFlowMonth.setResidueRoamFlow(simPackage.getMaxRoamFlow());
		simFlowMonth.setLastUpDatetime(nowDate);

		//账期
		Integer integerOffperiod =simCard.getOffPeriod();
		int offperiod=1;
		if(integerOffperiod!=null){
			offperiod =integerOffperiod.intValue();
			if(offperiod==0){
				offperiod=1;
			}
		}
		String strOffperiod = "" + offperiod;
		if(offperiod < 10)
			strOffperiod = "0" + offperiod;

		//默认账期持续一个月
		Integer sustained = simCard.getSuStained();
		if(sustained == null)
			sustained = 1;


		//账期日
		String nowMonth = countryService.getRoamcountryDate(nowDate,simCard.getCountryCode(),DateUtil.RB_DATE_Y_M);

		Date offperiodDate = DateUtil.string2Date(nowMonth + "-" + strOffperiod, DateUtil.RB_DATE_Y_M_D);
		String strNowRoamDate = countryService.getRoamcountryDate(nowDate,simCard.getCountryCode(),DateUtil.RB_DATE_FORMATER);
		Date nowRoamDate =  DateUtil.string2Date(strNowRoamDate, DateUtil.RB_DATE_FORMATER);
		if(offperiodDate.before(nowRoamDate)) {//账期日小于当前时间，则以账期日为起始时间
			simFlowMonth.setAccountPeriodStartDate(offperiodDate);
			Date endDate = DateUtil.calculate(offperiodDate,GregorianCalendar.MONTH, sustained);
			simFlowMonth.setAccountPeriodEndDate(endDate);
		} else {
			simFlowMonth.setAccountPeriodEndDate(offperiodDate);
			Date startDate = DateUtil.calculate(offperiodDate,GregorianCalendar.MONTH, 0-sustained);
			simFlowMonth.setAccountPeriodStartDate(startDate);
		}

		String month = DateUtil.date2String(DateUtil.RB_DATE_Y_M, simFlowMonth.getAccountPeriodStartDate());
		simFlowMonth.setDate(month);

		logger.debug("new simFlowMonth: " + simFlowMonth);
		//保存数据
		flowMonthDao.save(simFlowMonth);

		return simFlowMonth;
	}

	/**
	 * 更新卡组缓存里面卡的使用状态，0-空闲，1-占用
	 * @param simCard
	 */
	private void refreshCacheStatus(SimCard simCard){
		if(simCard.getStatus() == 0){
			TerminalSim terminalSim = getTerminalSimBySim(simCard.getImsi());
			if(terminalSim == null){
				updateGroupSim2Cache(simCard,0);
			}else {
				updateGroupSim2Cache(simCard,1);
			}
		}else if(simCard.getStatus() == 1 || simCard.getStatus() == 4){
			updateGroupSim2Cache(simCard,1);
		}
	}

	/**
	 * 更新缓存里面的卡信息
	 * @param simCard
	 * @return
	 */
	@Override
	public boolean updateSimCardFromCache(SimCard simCard,boolean onlySimCardCache){

		//对于他们提出的经常看不到缓存的信息，我也不知道，之前设置缓存是没有设置第三个日期参数的，我现在改成设置1天过期看看

		if(simCard == null)
			return false;

		boolean bCached = simCache.set(MConstant.CACHE_SIM_KEY_PREF + simCard.getImsi(),
				CommonUtil.convertBean(simCard, com.hqrh.rw.common.model.SimCard.class),
					new Date(System.currentTimeMillis()+1000*60*60*24));

		if(!bCached) {
			logger.error("save SimCard to Cache is error! simCard: " + simCard);
		}

		//???
		if (simCard.getPackageId() != 0) {
			publicCache.remove(MConstant.CACHE_OPERATOR_GROUP_KEY_PREE + simCard.getOperatorCode());
		}

		//onlySimCardCache - false 同时需要更新卡组缓存	true - 只更新卡缓存,不更新卡组缓存，也许卡组缓存已经更新
		if(!onlySimCardCache)
			initGroupSim2Cache(simCard);

		return bCached;
	}

}

