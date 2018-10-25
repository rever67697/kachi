package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.SelectCardDao;
import com.team.model.SelectCard;
import com.team.service.SelectCardService;
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

    @Override
    public ResultList list(Date startDate, Date endDate, Integer tsid,int page, int rows) {
        PageHelper.startPage(page,rows);
        Map<String,Object> map = new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("tsid",tsid);
        List<SelectCard> list = selectCardDao.list(map);
        PageInfo<SelectCard> pageInfo = new PageInfo<>(list);
        return new ResultList(pageInfo.getTotal(),list);
    }
}
