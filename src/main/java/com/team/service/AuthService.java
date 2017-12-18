package com.team.service;

import java.util.List;


import com.team.model.TbAuthMenu;

public interface AuthService {
	
	public List<TbAuthMenu> queryMenuByUser();
	
}
