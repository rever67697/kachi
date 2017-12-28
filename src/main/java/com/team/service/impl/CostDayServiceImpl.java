package com.team.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.CostDayDao;
import com.team.model.CostDay;
import com.team.service.CostDayService;
import com.team.vo.ResultList;

/**
 * 终端的日消费的相关操作	m_cost_day
 * 创建日期：2017-12-22下午3:15:30
 * author:wuzhiheng
 */
@Transactional
@Service
public class CostDayServiceImpl implements CostDayService{
	
	@Autowired
	private CostDayDao costDayDao;

	@Override
	/**
	 * 通过终端tsid查找它的相关消费记录
	 *@param tsid
	 *@return
	 *return
	 */
	public ResultList getCostDayByTsid(Integer tsid,int page,int rows) {
		PageHelper.startPage(page, rows);
		List<CostDay> list = costDayDao.getCostDayByTsid(tsid);
		PageInfo<CostDay> pageInfo = new PageInfo<CostDay>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

}
