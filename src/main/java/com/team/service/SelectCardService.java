package com.team.service;

import com.team.vo.ResultList;

import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 4:41 PM 2018/10/25
 */
public interface SelectCardService {

    ResultList list(Date startDate, Date endDate, Integer tsid,int page, int rows);
}
