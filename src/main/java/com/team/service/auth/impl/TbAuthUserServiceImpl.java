package com.team.service.auth.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.auth.TbAuthUserDao;
import com.team.model.auth.TbAuthUser;
import com.team.service.auth.TbAuthUserService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;

/**
 * 创建日期：2018-1-18下午10:19:11
 * author:wuzhiheng
 */
@Service
@Transactional
public class TbAuthUserServiceImpl implements TbAuthUserService{

	@Autowired
	private TbAuthUserDao tbAuthUserDao;
	
	@Override
	/**
	 * 通过登录名查找用户信息
	 */
	public TbAuthUser getUserByLoginName(String loginName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", loginName);
		map.put("status", 0);
		List<TbAuthUser> list = tbAuthUserDao.getUserList(map);
		return CommonUtil.listNotBlank(list)?list.get(0):null;
	}
	
	@Override
	/**
	 * 根据条件查找用户列表
	 */
	public ResultList getUserList(Integer status, String name,String loginName,
			Integer departmentId,int page,int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("name", name);
		map.put("loginName", loginName);
		map.put("departmentId", CommonUtil.changeDepartmentId(departmentId));
		map.put("status", status);
		List<TbAuthUser> list = tbAuthUserDao.getUserList(map);
		PageInfo<TbAuthUser> pageInfo = new PageInfo<TbAuthUser>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

}
