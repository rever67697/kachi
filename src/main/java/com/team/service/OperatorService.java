package com.team.service;

import com.team.model.Operator;
import com.team.vo.ResultList;

/**
 * 创建日期：2018-2-9下午3:54:59
 * author:wuzhiheng
 */
public interface OperatorService {

	ResultList list(Integer countryCode,int page,int rows);

	ResultList list(String operatorList);

	Operator getOperator(Integer operatorCode);
	
}
