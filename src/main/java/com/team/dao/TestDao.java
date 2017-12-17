package com.team.dao;

import java.util.List;
import java.util.Map;

import com.team.model.SimCard;

/**
 * 创建日期：2017-12-11上午9:59:14
 * author:wuzhiheng
 */
public interface TestDao {

	List<SimCard> getAllSimCard();
	
	List<SimCard> getSimCardById(int id);
}
