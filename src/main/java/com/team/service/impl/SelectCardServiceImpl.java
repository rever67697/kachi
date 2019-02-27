package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.SelectCardDao;
import com.team.model.SelectCard;
import com.team.service.SelectCardService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 4:42 PM 2018/10/25
 */
@Service
public class SelectCardServiceImpl implements SelectCardService {

    @Autowired
    private SelectCardDao selectCardDao;

    /**
     * 取卡异常
     * @return
     */
    @Override
    public ResultList listUnnormal(Date startDate, Date endDate, Integer tsid,Integer departmentId,Integer dId,int page, int rows) {
        PageHelper.startPage(page,rows);
        Map<String,Object> map = new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("tsid",tsid);
        map.put("departmentId", departmentId);
        map.put("dId", CommonUtil.changeDepartmentId(dId));
        List<SelectCard> list = selectCardDao.listUnnormal(map);
        PageInfo<SelectCard> pageInfo = new PageInfo<>(list);
        return new ResultList(pageInfo.getTotal(),list);
    }

    /**
     * 选卡日志
     * @return
     */
    @Override
    public ResultList listSelectCardLog(Date startDate, Date endDate, Integer tsid, Long imsi,Integer departmentId,Integer dId, int page, int rows) {
        PageHelper.startPage(page,rows);
        Map<String,Object> map = new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("tsid",tsid);
        map.put("imsi",imsi);
        map.put("departmentId", departmentId);
        map.put("dId", CommonUtil.changeDepartmentId(dId));
        List<SelectCard> list = selectCardDao.listSelectCardLog(map);
        PageInfo<SelectCard> pageInfo = new PageInfo<>(list);
        return new ResultList(pageInfo.getTotal(),list);
    }
}
