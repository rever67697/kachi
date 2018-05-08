package com.team.service.impl;

import java.util.*;

import com.team.model.SimCard;
import com.team.service.SimCardService;
import com.team.util.Cache;
import com.team.util.CacheFactory;
import com.team.util.MConstant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.ReadyTerminalSimDao;
import com.team.dao.SimCardDao;
import com.team.model.ReadyTerminalSim;
import com.team.service.ReadyTerminalSimService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

@Transactional
@Service
public class ReadyTerminalSimServiceImpl extends BaseService implements ReadyTerminalSimService {

	private Logger logger = Logger.getLogger(ReadyTerminalSimServiceImpl.class);

	@Autowired
	private ReadyTerminalSimDao readyTerminalSimDao;
	@Autowired
	private SimCardDao simCardDao;
	@Autowired
	private SimCardService simCardService;

	// 卡缓存
	private static final Cache simCache = CacheFactory.getCache(MConstant.MEM_SIM);

	@Override
	public ResultList list(Integer tsid, Long imsi, Integer dId,int page,
			int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dId",CommonUtil.changeDepartmentId(dId));
		paramMap.put("imsi", imsi);
		paramMap.put("tsid", tsid);
		List<ReadyTerminalSim> list = readyTerminalSimDao.list(paramMap);
		PageInfo<ReadyTerminalSim> pageInfo = new PageInfo<ReadyTerminalSim>(
				list);
		return new ResultList(pageInfo.getTotal(), list);
	}

    @Override
    public ReturnMsg delete(ReadyTerminalSim readyTerminalSim) {
		//1。删除当前数据
		readyTerminalSimDao.delete(readyTerminalSim);
		//2。根据imsi更新卡的状态为指定前的状态
		simCardDao.resetStatus(readyTerminalSim);
		//3.释放卡
		SimCard simCard = simCardDao.getByImsi(readyTerminalSim.getImsi());
		//simCardService.updateGroupSim2Cache(simCard,0);

		//4.更新卡缓存
		boolean bCached = simCache.set(MConstant.CACHE_SIM_KEY_PREF + simCard.getImsi(),
				CommonUtil.convertBean(simCard, com.hqrh.rw.common.model.SimCard.class));
		if(!bCached) {
			logger.error("save SimCard to Cache is error! simCard: " + simCard);
		}

        return super.successTip();
    }


	@Override
	public ReturnMsg save(Integer tsid,Integer type,String args,Integer userId) {
		int count = 0;
		List<ReadyTerminalSim> list = new ArrayList<ReadyTerminalSim>();
		List<Integer> ids = new ArrayList<Integer>();
		for (String string : args.split(";")) {
			String[] params = string.split(",");
			ReadyTerminalSim readyTerminalSim = new ReadyTerminalSim(tsid, CommonUtil.putLong(params[2]),CommonUtil.putInteger(params[1]), type, userId);
			list.add(readyTerminalSim);
			ids.add(CommonUtil.putInteger(params[0]));
		}
		if(list.size()>0){
			//插入数据
			count = readyTerminalSimDao.insert(list);
			//更新卡表卡状态为指定
			simCardDao.updateCardStatus(ids);
		}

		//更新卡缓存
		for (ReadyTerminalSim readyTerminalSim : list) {
			SimCard simCard = simCardDao.getByImsi(readyTerminalSim.getImsi());

			//4.更新卡缓存
			boolean bCached = simCache.set(MConstant.CACHE_SIM_KEY_PREF + simCard.getImsi(),
					CommonUtil.convertBean(simCard, com.hqrh.rw.common.model.SimCard.class));
			if(!bCached) {
				logger.error("save SimCard to Cache is error! simCard: " + simCard);
			}
		}

		return count>0?super.successTip():super.errorTip();
	}

	@Override
	public ReturnMsg update(ReadyTerminalSim readyTerminalSim) {
		int count = 0;
		if (readyTerminalSim.getId() != null) {
			//1.获取原数据
			ReadyTerminalSim old = readyTerminalSimDao.getBydId(readyTerminalSim.getId());

			if(old !=null && !old.getImsi().equals(readyTerminalSim.getImsi())){
				if(old.getLastStatus()==2){
					old.setLastStatus(0);
				}
				//1.如果imsi发生改变，需要把先更新之前卡的状态
				simCardDao.resetStatus(old);

				//2.把修改后的imsi的状态改为指定
				simCardDao.updateByImsi(readyTerminalSim.getImsi());

				SimCard simCard = simCardDao.getByImsi(old.getImsi());
				//3.释放卡
				//simCardService.updateGroupSim2Cache(simCard,0);

				//4.更新旧卡缓存
				boolean bCached = simCache.set(MConstant.CACHE_SIM_KEY_PREF + simCard.getImsi(),
						CommonUtil.convertBean(simCard, com.hqrh.rw.common.model.SimCard.class));
				if(!bCached) {
					logger.error("save SimCard to Cache is error! simCard: " + simCard);
				}
				//5.更新新卡缓存
				simCard = simCardDao.getByImsi(readyTerminalSim.getImsi());
				bCached = simCache.set(MConstant.CACHE_SIM_KEY_PREF + simCard.getImsi(),
						CommonUtil.convertBean(simCard, com.hqrh.rw.common.model.SimCard.class));
				if(!bCached) {
					logger.error("save SimCard to Cache is error! simCard: " + simCard);
				}
			}
			count = readyTerminalSimDao.update(readyTerminalSim);
		}
		return count>0?super.successTip():super.errorTip();
	}

}
