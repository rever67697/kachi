package com.team.service;

import java.util.List;

import com.team.model.auth.TbAuthMenu;
import com.team.vo.ReturnMsg;



public interface AuthService {
	
	public List<TbAuthMenu> getMenuByUser();
	
	public ReturnMsg getFunByUser(Integer id);
	
}
