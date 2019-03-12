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

	@Override
	public ResultList list(Integer tsid, Long imsi,Integer departmentId,int page,int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dId",CommonUtil.getDId());
		paramMap.put("imsi", imsi);
		paramMap.put("tsid", tsid);
		paramMap.put("departmentId", departmentId);
		List<ReadyTerminalSim> list = readyTerminalSimDao.list(paramMap);
		PageInfo<ReadyTerminalSim> pageInfo = new PageInfo<ReadyTerminalSim>(
				list);
		return new ResultList(pageInfo.getTotal(), list);
	}

    @Override
    public ReturnMsg delete(ReadyTerminalSim readyTerminalSim) {

		SimCard simCard = simCardDao.getByImsi(readyTerminalSim.getImsi());

		//1.删除当前数据
		readyTerminalSimDao.delete(readyTerminalSim);
		//2.根据imsi更新卡的状态为指定前的状态
		//判断旧卡当前的状态是不是就是指定状态，如果不是，代表这张卡在指定期间修改过状态，那么这张卡就不做状态的修改了
		if(simCard != null && simCard.getStatus() != null && simCard.getStatus() == 2){
			simCardDao.resetStatus(readyTerminalSim);
			simCard.setStatus(readyTerminalSim.getLastStatus());
		}
		//3.释放卡
		//simCardService.updateGroupSim2Cache(simCard,0);

		//4.更新卡缓存
		simCardService.updateSimCardFromCache(simCard,false);

        return successTip();
    }


	@Override
	public ReturnMsg save(Integer tsid,Integer type,String args,Integer userId,String remark) {

		//判断tsid是否已经有指定卡了，一个终端tsid只能指定一张卡
		if(readyTerminalSimDao.selectByTsid(tsid) > 0){
			return errorTip("该终端已经有指定卡，不能重复指定");
		}

		List<ReadyTerminalSim> list = new ArrayList<ReadyTerminalSim>();
		List<Integer> ids = new ArrayList<Integer>();
		for (String string : args.split(";")) {
			String[] params = string.split(",");
			ReadyTerminalSim readyTerminalSim = new ReadyTerminalSim(tsid, CommonUtil.putLong(params[2]),CommonUtil.putInteger(params[1]), type, userId,remark);
			list.add(readyTerminalSim);
			ids.add(CommonUtil.putInteger(params[0]));
		}
		if(list.size()>0){
			//插入数据
			readyTerminalSimDao.insert(list);
			//更新卡表卡状态为指定
			simCardDao.updateCardStatus(ids);
		}

		//更新卡缓存
		for (ReadyTerminalSim readyTerminalSim : list) {
			SimCard simCard = simCardDao.getByImsi(readyTerminalSim.getImsi());

			simCardService.updateSimCardFromCache(simCard,false);
		}

		return successTip();
	}

	@Override
	public ReturnMsg update(ReadyTerminalSim readyTerminalSim) {
		if (readyTerminalSim.getId() != null) {
			//1.获取原数据
			ReadyTerminalSim old = readyTerminalSimDao.getBydId(readyTerminalSim.getId());

			if(old !=null && !old.getImsi().equals(readyTerminalSim.getImsi())){
				if(old.getLastStatus() != null && old.getLastStatus()==2){
					old.setLastStatus(0);
				}
				//获取旧卡，如果imsi发生改变，需要把先更新之前卡的状态
				SimCard simCard = simCardDao.getByImsi(old.getImsi());

				//判断旧卡当前的状态是不是就是指定状态，如果不是，代表这张卡在指定期间修改过状态，那么这张卡就不做状态的修改了
				if(simCard.getStatus() != null && simCard.getStatus() ==2){
					simCardDao.resetStatus(old);
					//更新旧卡缓存
					simCard.setStatus(old.getLastStatus());
					simCardService.updateSimCardFromCache(simCard,false);
				}


				//2.把修改后的imsi的状态改为指定
				Map<String,Object> map = new HashMap<>();
				map.put("imsi",readyTerminalSim.getImsi());
				map.put("status",2);
				simCardDao.updateStatusByImsi(map);


				//释放卡
				//simCardService.updateGroupSim2Cache(simCard,0);

				//更新新卡缓存
				simCard = simCardDao.getByImsi(readyTerminalSim.getImsi());
				simCardService.updateSimCardFromCache(simCard,false);
			}
			readyTerminalSimDao.update(readyTerminalSim);
		}
		return successTip();
	}

}
