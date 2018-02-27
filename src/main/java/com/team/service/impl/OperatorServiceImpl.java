package com.team.service.impl;

import java.util.Date;
import java.util.List;

import com.team.util.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.dao.OperatorDao;
import com.team.model.Operator;
import com.team.service.OperatorService;
import com.team.util.Cache;
import com.team.util.CacheFactory;
import com.team.util.MConstant;

/**
 * 创建日期：2018-2-9下午3:56:27
 * author:wuzhiheng
 */
@Service
public class OperatorServiceImpl implements OperatorService {
	
	private Logger logger = Logger.getLogger(OperatorServiceImpl.class);

	private static final Cache publicCache =CacheFactory.getCache(MConstant.MEM_PUBLIC);
	
	@Autowired
	private OperatorDao operatorDao;
	
	
	@Override
	public Operator getOperator(Integer operatorCode) {
		Operator operator = null;
		Object object = publicCache.get(MConstant.CACHE_OPERATOR_KEY_PREE + operatorCode);
		if(object != null)
			return CommonUtil.convertBean(object, Operator.class);
		
		List<Operator> operatorList = operatorDao.getOperatorByCode(operatorCode);
		if (operatorList.size() != 0)
			operator = operatorList.get(0);
		
		logger.debug("select Operator by sql,operatorCode:"+ operatorCode + "/" + operator);
		
		if(operator != null) {
			publicCache.set(MConstant.CACHE_OPERATOR_KEY_PREE +  operatorCode,
					CommonUtil.convertBean(operator, com.hqrh.rw.common.model.Operator.class),
					new Date(1000*60*60*24));
		}
		
		return operator;
	}

}
