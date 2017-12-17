package com.team.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.team.dao.TestDao;
import com.team.model.SimCard;
import com.team.util.IConstant;

/**
 * 创建日期：2017-12-15下午12:33:38
 * author:wuzhiheng
 */
@Service
public class TestService {
	
	@Autowired
	private TestDao dao;
	
	public List<SimCard> getAll(){
		return dao.getAllSimCard();
	}
	
	@Cacheable(value = "userCache", key = IConstant.USER_KEY)
	public List<SimCard> getCustomerById(int id){
		System.out.println("service===="+id);
		return dao.getSimCardById(id);
	}
	
}
