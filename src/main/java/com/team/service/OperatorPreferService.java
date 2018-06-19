package com.team.service;

import com.team.model.OperatorPrefer;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午2:24 2018/4/23
 */
public interface OperatorPreferService {

    ResultList list(Integer countryCode,Integer operatorCode,int page,int rows);

    ReturnMsg saveOrUpdate(OperatorPrefer operatorPrefer);

    ReturnMsg delete(String ids);

}
