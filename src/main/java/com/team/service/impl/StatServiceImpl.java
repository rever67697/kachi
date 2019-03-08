package com.team.service.impl;

import com.team.dao.StatDao;
import com.team.model.StatTerminal;
import com.team.service.StatService;
import com.team.util.CommonUtil;
import com.team.vo.stat.StatBean;
import com.team.vo.stat.TerminalCost;
import com.team.vo.stat.TerminalCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 上午9:25 2018/8/16
 */
@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatDao statDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");


    @Override
    public Map<String, Object> terminalCount() {

        List<TerminalCount> terminalCountList = statDao.queryTerminalCount(CommonUtil.getDId());

        List<Map<String, Object>> series = new ArrayList<>();

        List<String> xAxis = new ArrayList<>();

        List<Integer> data1 = new ArrayList<>();

        List<Integer> data2 = new ArrayList<>();

        for (TerminalCount terminalCount : terminalCountList) {
            xAxis.add(terminalCount.getStatDate().substring(5));
            data1.add(terminalCount.getActiveNum());
            data2.add(terminalCount.getTotalNum());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("name", "当天激活量");
        map.put("type", "line");
        map.put("data", data1);
        series.add(map);
        map = new HashMap<>();
        map.put("name", "总激活量");
        map.put("type", "line");
        map.put("data", data2);
        series.add(map);

        Map<String, Object> ret = new HashMap<>();
        ret.put("series", series);
        ret.put("xAxis", xAxis);
        ret.put("legend", new String[]{"当天激活量", "总激活量"});

        StatBean statBean = new StatBean();
        statBean.setdTCount(data1.size() > 0 ? (data1.get(data1.size() - 1)) : 0);
        statBean.settTCount(data2.size() > 0 ? (data2.get(data2.size() - 1)) : 0);

        ret.put("statBean", statBean);

        return ret;
    }

    @Override
    public Map<String, Object> terminalCost() {

        List<TerminalCost> terminalCostList = statDao.queryTerminalCost(CommonUtil.getDId());

        List<String> xAxis = new ArrayList<>();
        List<Double> series = new ArrayList<>();

        for (TerminalCost terminalCost : terminalCostList) {
            xAxis.add(terminalCost.getTsid().toString());
            series.add(terminalCost.getFlow());
        }

        Map<String, Object> ret = new HashMap<>();
        ret.put("xAxis", xAxis);
        ret.put("series", series);

        return ret;
    }

    @Override
    public StatBean fixInformation(StatBean sb) {

        StatBean statBean = statDao.stat(CommonUtil.getDId());
        statBean.setdTCount(sb.getdTCount());
        statBean.settTCount(sb.gettTCount());
        statBean.fix();

        return statBean;
    }

    @Override
    public Map<String, Object> statTerminal(Date startDate, Date endDate){
        Map<String,Object> map = new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("dId",CommonUtil.getDId());


        List<String> xAxis = new ArrayList<>();
        List<Integer> series = new ArrayList<>();

        List<StatTerminal> list = statDao.queryOnlineCount(map);

        if(CommonUtil.listNotBlank(list)){
            for (StatTerminal statTerminal : list) {
                xAxis.add(sdf.format(statTerminal.getStatDate()));
                series.add(statTerminal.getStatCount());
            }
        }

        Map<String, Object> ret = new HashMap<>();
        ret.put("xAxis", xAxis);
        ret.put("series", series);

        return ret;

    }

}
