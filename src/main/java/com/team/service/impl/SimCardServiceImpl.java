package com.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.SimCardDao;
import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.vo.OutlineInfo;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import com.team.model.SimCard;
import com.team.service.SimCardService;

/**
 * 创建日期：2017-12-19上午10:04:29
 * author:wuzhiheng
 */
@Transactional
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
	 * 状态为作废的卡可以删除
	 */
	public ReturnMsg deleteSimCard(String ids) {
		int count = simCardDao.deleteSimCard(ids.split(","));
		return count>0?IConstant.MSG_OPERATE_SUCCESS:IConstant.MSG_OPERATE_ERROR;
	}

	@Override
	/**
	 * 根据条件寻找出sim卡列表
	 */
	public ResultList getSimCard(String departmentId, String cpId,
			String number, String status, int page, int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("departmentId", CommonUtil.putInteger(departmentId));
		map.put("cpId", CommonUtil.putInteger(cpId));
		map.put("status", CommonUtil.putInteger(status));
		map.put("number", number);
		List<SimCard> list = simCardDao.getSimCard(map);
		PageInfo<SimCard> pageInfo = new PageInfo<SimCard>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	@Override
	/**
	 * 查询流量卡总览信息
	 */
	public ReturnMsg getOutlineInfo(String departmentId) {
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		List<OutlineInfo> list = simCardDao.getOutlineInfo(CommonUtil.putInteger(departmentId));
		OutlineInfo info = null;
		if(list!=null&&list.size()>0){
			info = list.get(0);
			returnMsg.setData(info);
		}
		return returnMsg;
	}

}
