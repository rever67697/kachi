package com.team.service.auth.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.dao.auth.TbAuthPermissionDao;
import com.team.model.auth.TbAuthPermission;
import com.team.model.auth.TbAuthUser;
import com.team.service.auth.TbAuthPermissionService;
import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.vo.ReturnMsg;

/**
 * 创建日期：2018-1-18下午10:19:11
 * author:wuzhiheng
 */
@Service
@Transactional
public class TbAuthPermissionServiceImpl implements TbAuthPermissionService{

	@Autowired
	private TbAuthPermissionDao tbAuthPermissionDao;
	
	public List<TbAuthPermission> getMenuByUser(TbAuthUser user){
		List<TbAuthPermission> list = null;
		if(CommonUtil.listNotBlank(user.getRoles())){
			list = tbAuthPermissionDao.getMenuByRole(user.getRoles());
		}
		
		return  CommonUtil.bulidTree(list,false);
	}

	@Override
	public ReturnMsg getFunByUser(TbAuthUser user,Integer id) {
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", id);
		map.put("list", user.getRoles());
		returnMsg.setData(tbAuthPermissionDao.getFunByRole(map));
		return returnMsg;
	}

	@Override
	public List<TbAuthPermission> getPermissionTree() {
		List<TbAuthPermission> list = tbAuthPermissionDao.getAllPermission();
		return CommonUtil.bulidTree(list,true);
	}

	@Override
	public List<Integer> getPermissionByUser(Integer id) {
		return tbAuthPermissionDao.getPermissionByUser(id);
	}


	
}
