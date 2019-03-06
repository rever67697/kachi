package com.team.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team.dao.FlowDayDao;
import com.team.model.FlowDay;
import com.team.model.FlowMonth;
import com.team.model.SimCard;
import com.team.service.SimCardService;
import com.team.util.Cache;
import com.team.util.CacheFactory;
import com.team.util.MConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.FlowMonthDao;
import com.team.dao.SimCardDao;
import com.team.dao.SimPackageDao;
import com.team.model.SimPackage;
import com.team.service.SimPackageService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * 卡套餐的相关操作	m_simpackage
 * 创建日期：2017-12-23下午6:07:26
 * author:wuzhiheng
 */
@Transactional
@Service
public class SimPackageServiceImpl extends BaseService implements SimPackageService{
	
	@Autowired
	private SimPackageDao simPackageDao;
	@Autowired
	private SimCardDao simCardDao;
	@Autowired
	private FlowMonthDao flowMonthDao;
	@Autowired
	private FlowDayDao flowDayDao;
	@Autowired
	private SimCardService simCardService;

	//卡缓存
	private static final Cache simCache = CacheFactory.getCache(MConstant.MEM_SIM);
	// 卡最后月流量缓存
	private static final Cache simFlowCache = CacheFactory .getCache(MConstant.MEM_SIM_FlOW);

	@Override
	/**
	 * 查找卡套餐信息
	 */
	public ResultList list(Integer dId,Integer departmentId,Integer status, String name, int page, int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("status", status);
		map.put("departmentId", departmentId);
		map.put("dId", CommonUtil.changeDepartmentId(dId));
		List<SimPackage> list = simPackageDao.list(map);
		PageInfo<SimPackage> pageInfo = new PageInfo<>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	@Override
	/**
	 * 单条删除卡套餐
	 */
	public ReturnMsg delete(Integer id) {
		ReturnMsg returnMsg;
		if(!CommonUtil.StringIsNull(simCardDao.getPackageExist(id))){
			returnMsg = super.errorTip();
			returnMsg.setMsg("删除失败，有SIM卡正在使用该套餐！");
		}else{
			int count = simPackageDao.delete(id);
			if(count>0){
				//删除缓存
				simCache.remove(MConstant.CACHE_PACKAGE_KEY_PREF + id);
			}
			returnMsg = super.successTip();
		}
		return returnMsg;
	}

	@Override
	public ReturnMsg save(SimPackage simPackage) {
		int count = 0;
		if(simPackage.getId()!=null){
			SimPackage origin = simPackageDao.getOne(simPackage.getId());

			if(simPackage.getStatus().equals(1) && origin.getStatus().equals(0) && !CommonUtil.StringIsNull(simCardDao.getPackageExist(simPackage.getId()))){
				ReturnMsg returnMsg = super.errorTip();
				returnMsg.setMsg("不能修改套餐状态，有SIM卡正在使用该套餐！");
				return returnMsg;
			}

			count = simPackageDao.update(simPackage);
			if(!simPackage.getMaxFlow().equals(origin.getMaxFlow()) || !simPackage.getMaxRoamFlow().equals(origin.getMaxRoamFlow())) {
				//需要更新流量大小
//				flowMonthDao.updateMonthFlowByPackage(simPackage);

				//需要更新使用了这张卡的月流量缓存，重新计算月流量
				List<SimCard> simCardList = simCardDao.getByPackage(simPackage.getId());

				if(CommonUtil.listNotBlank(simCardList)){
					for (SimCard simCard : simCardList) {
						FlowMonth flowMonth = simCardService.getNowFlowMonth(simCard);
						if(flowMonth!=null){

							Map<String,Object> map = new HashMap<>();
							map.put("imsi",simCard.getImsi());
							map.put("startDate",flowMonth.getAccountPeriodStartDate());
							map.put("endDate",flowMonth.getAccountPeriodEndDate());
							FlowDay flowDay = flowDayDao.getUsedFlow(map);

							//4.重新计算本月的其他流量参数
							flowMonth.setMaxFlow(simPackage.getMaxFlow());
							flowMonth.setMaxRoamFlow(simPackage.getMaxRoamFlow());
							flowMonth.setUsedFlow(flowDay.getFlow());
							flowMonth.setUsedRoamFlow(flowDay.getRoamFlow());
							flowMonth.setResidueFlow(flowMonth.getMaxFlow()-flowMonth.getUsedFlow());
							flowMonth.setResidueRoamFlow(flowMonth.getMaxRoamFlow()-flowMonth.getUsedRoamFlow());
							flowMonth.setLastUpDatetime(new Date());

							flowMonthDao.updateMonthFlowBySimCard(flowMonth);

							//这里需要注意一下，所有有关获取或者设置缓存的，要确保两边的一致
							simFlowCache.set(MConstant.CACHE_FLOW_KEY_PREF + simCard.getImsi(),
									CommonUtil.convertBean(flowMonth, com.hqrh.rw.common.model.FlowMonth.class));
						}
					}
				}

			}
		}else{
			count = simPackageDao.insert(simPackage);
		}
		if(count>0){
			//更新缓存
			simCache.set(MConstant.CACHE_PACKAGE_KEY_PREF + simPackage.getId(),
					CommonUtil.convertBean(simPackage, com.hqrh.rw.common.model.SimPackage.class));

			return super.successTip();
		}
		return super.errorTip();
	}

}
