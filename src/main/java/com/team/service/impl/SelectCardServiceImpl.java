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
    public ResultList listUnnormal(Date startDate, Date endDate, Integer tsid,Integer departmentId,int page, int rows) {
        PageHelper.startPage(page,rows);
        Map<String,Object> map = new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("tsid",tsid);
        map.put("departmentId", departmentId);
        map.put("dId", CommonUtil.getDId());
        List<SelectCard> list = selectCardDao.listUnnormal(map);
        PageInfo<SelectCard> pageInfo = new PageInfo<>(list);
        return new ResultList(pageInfo.getTotal(),list);
    }

    /**
     * 选卡日志
     * @return
     */
    @Override
    public ResultList listSelectCardLog(Date startDate, Date endDate, Integer tsid, Long imsi,Integer departmentId,int page, int rows) {
        PageHelper.startPage(page,rows);
        Map<String,Object> map = new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("tsid",tsid);
        map.put("imsi",imsi);
        map.put("departmentId", departmentId);
        map.put("dId", CommonUtil.getDId());
        List<SelectCard> list = selectCardDao.listSelectCardLog(map);

        if(CommonUtil.listNotBlank(list)){
            for (SelectCard selectCard : list) {
                //增加逻辑，不方便在sql里面写，先找出比当前数据大的最小选卡时间，这个时间如果比这个数据查出的鉴权记录还小，就把下面日期置空
                if(selectCard.getRefSelectDate() != null && selectCard.getAuthTime() != null && selectCard.getRefSelectDate().before(selectCard.getAuthTime())){
                    selectCard.setAuthTime(null);
                    selectCard.setFirstTime(null);
                }
            }
        }

        PageInfo<SelectCard> pageInfo = new PageInfo<>(list);
        return new ResultList(pageInfo.getTotal(),list);
    }
}
