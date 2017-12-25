package com.team.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.dao.SimCardDao;
import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import com.team.model.SimCard;
import com.team.service.SimCardService;

/**
 * 创建日期：2017-12-19上午10:04:29
 * author:wuzhiheng
 */
@Service
public class SimCardServiceImpl implements SimCardService{

	@Autowired
	private SimCardDao simCardDao;
	
	@Override
	/**
	 * 根据卡池id找出其上面的卡
	 */
	public ResultList getSimCardByPool(Integer cpId) {
		List<SimCard> list =simCardDao.getSimCardByPool(cpId);
		return new ResultList(list!=null?list.size():0, list);
	}

	@Override
	/**
	 * 根据卡套餐id寻找是否有SIM卡在使用这个套餐
	 */
	public String getPackageExist(Integer packageId) {
		System.out.println("result"+simCardDao.getPackageExist(packageId));
		return simCardDao.getPackageExist(packageId);
	}

}
