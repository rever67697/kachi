package com.team.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.dao.AuthDao;
import com.team.model.auth.TbAuthMenu;
import com.team.service.AuthService;
import com.team.util.IConstant;
import com.team.vo.ReturnMsg;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private AuthDao authDao;
	
	public List<TbAuthMenu> getMenuByUser(){
		List<TbAuthMenu> menu = new ArrayList<TbAuthMenu>();
		List<TbAuthMenu> list = authDao.getMenuByRoleId();
		for (TbAuthMenu m : list) {
			if(m.getParentId() == 0){
				menu.add(m);
			}
		}
		for (TbAuthMenu m : list) {
			int parentId = m.getParentId();
			for (TbAuthMenu mm : menu) {
				if(parentId == mm.getId()){
					mm.getChildren().add(m);
				}
			}
		}
		for (TbAuthMenu m : list) {
			int parentId = m.getParentId();
			for (TbAuthMenu mm : menu) {
				for (TbAuthMenu mmm : mm.getChildren()) {
					if(parentId == mmm.getId()){
						
						mmm.getChildren().add(m);
					}
				}
			}
		}
		return menu;
	}

	@Override
	public ReturnMsg getFunByUser(Integer id) {
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		returnMsg.setData(authDao.getFunByRoleId(id));
		return returnMsg;
	}

	
}
