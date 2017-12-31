package com.team.service;

import java.util.List;

import com.team.model.auth.TbAuthMenu;



public interface AuthService {
	
	public List<TbAuthMenu> queryMenuByUser();
	
}
