package com.team.service;

import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

import java.util.Date;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 11:06 PM 2018/10/25
 */
public interface ProblemCardService {

    ResultList list(Date startDate, Date endDate, Integer tsid, Long imsi,Integer status,Integer departmentId,Integer dId, int page, int rows);

    ReturnMsg delete(Long imsi);

    ReturnMsg getAlarmList(Integer dId);
}
