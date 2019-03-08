package com.team.service;

import com.team.vo.ResultList;

import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 4:41 PM 2018/10/25
 */
public interface SelectCardService {

    ResultList listUnnormal(Date startDate, Date endDate, Integer tsid,Integer departmentId,int page, int rows);

    ResultList listSelectCardLog(Date startDate, Date endDate, Integer tsid,Long imsi,Integer departmentId,int page, int rows);
}
