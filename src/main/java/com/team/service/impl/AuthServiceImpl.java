package com.team.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.dao.AuthDao;
import com.team.model.TbAuthMenu;
import com.team.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private AuthDao authDao;
	
	public List<TbAuthMenu> queryMenuByUser(){
		List<TbAuthMenu> menu = new ArrayList<TbAuthMenu>();
		List<TbAuthMenu> list = authDao.queryMenuByRoleId();
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
	
	
}
