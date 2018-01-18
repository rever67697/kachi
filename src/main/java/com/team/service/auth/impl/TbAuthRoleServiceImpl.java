package com.team.service.auth.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.dao.auth.TbAuthRoleDao;
import com.team.model.auth.TbAuthRole;
import com.team.model.auth.TbAuthUser;
import com.team.service.auth.TbAuthRoleService;

@Service
@Transactional
public class TbAuthRoleServiceImpl implements TbAuthRoleService{
	
	@Autowired
	private TbAuthRoleDao tbAuthRoleDao;
	
	@Override
	public List<TbAuthRole> getRolesByUser(TbAuthUser user) {
		return tbAuthRoleDao.getRolesByUser(user.getId());
	}

}
