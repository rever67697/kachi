package com.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.TerminalDao;
import com.team.model.ResultList;
import com.team.model.Terminal;
import com.team.service.TerminalService;
import com.team.util.StringUtil;

/**
 * 终端的相关操作	m_terminal
 * 创建日期：2017-12-21下午4:53:20
 * author:wuzhiheng
 */
@Service
public class TerminalServiceImpl implements TerminalService{

	@Autowired
	private TerminalDao terminalDao;
	
	@Override
	/**
	 * 通过代理商找出终端列表
	 *@param 
	 *@return
	 *return
	 */
	public ResultList getTerminalByDeparment(String departmentId, String tsid,
			String status,String activated, int page, int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("departmentId", StringUtil.putInteger(departmentId));
		map.put("tsid", StringUtil.putInteger(tsid));
		map.put("status", StringUtil.putInteger(status));
		map.put("activated", StringUtil.putInteger(activated));
		List<Terminal> list = terminalDao.getTerminalByDeparment(map);
		PageInfo<Terminal> pageInfo = new PageInfo<>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

}
