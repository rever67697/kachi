package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.FlowBalanceDao;
import com.team.dao.TerminalChargeRecordDao;
import com.team.model.FlowBalance;
import com.team.model.TerminalChargeRecord;
import com.team.service.TerminalChargeService;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午4:55 2018/3/26
 */
@Service
@Transactional
public class TerminalChargeServiceImpl extends BaseService implements TerminalChargeService {

    @Autowired
    private TerminalChargeRecordDao terminalChargeRecordDao;
    @Autowired
    private FlowBalanceDao flowBlanceDao;

    @Override
    public ReturnMsg charge(TerminalChargeRecord record) {

        //获取源流量和日期参数
        FlowBalance origin = flowBlanceDao.findByTsid(record.getTsid());

        //1.保存FlowBlance
        FlowBalance flowBalance = new FlowBalance();
        BeanUtils.copyProperties(record, flowBalance);

        //解决在手机端手机不到时间的问题

        if(record.getChargeDate() != null && record.getValidityDate() == null){
            Date validityDate = origin.getValidityDate()==null?new Date():origin.getValidityDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(validityDate);
            calendar.add(Calendar.DATE,record.getChargeDate());
            flowBalance.setValidityDate(calendar.getTime());
        }

        if(origin!=null){
            flowBlanceDao.update(flowBalance);
        }else {
            flowBlanceDao.save(flowBalance);
        }
        //2.保存充值记录

        if(origin == null){
            origin = new FlowBalance();
        }

        //如果原本的剩余流量小于0，当0处理
        if(origin.getAllowFlow()<=0){
            origin.setAllowFlow(0L);
        }

        if(!origin.getAllowFlow().equals(record.getAllowFlow())){
            record.setChargeFlow(record.getAllowFlow()-origin.getAllowFlow());
        }
        if(record.getChargeFlow()!=null || record.getChargeDate()!=null){
            terminalChargeRecordDao.save(record);
        }

        return super.successTip();
    }

    @Override
    public ResultList list(Integer tsid, Date startDate, Date endDate, int page, int rows) {
        PageHelper.startPage(page, rows);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tsid",tsid);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        List<TerminalChargeRecord> list = terminalChargeRecordDao.list(map);

        PageInfo<TerminalChargeRecord> pageInfo = new PageInfo<TerminalChargeRecord>(list);
        return new ResultList(pageInfo.getTotal(), list);
    }

    @Override
    public ReturnMsg count(Integer tsid, Date startDate, Date endDate) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tsid",tsid);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        return successTip(terminalChargeRecordDao.count(map));
    }
    public static void main(String[] args) throws Exception{
        Date n = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(n);
        calendar.add(Calendar.DATE,365);
        n = calendar.getTime();

        System.out.println((new SimpleDateFormat("yyyy-MM-dd").format(n)));
    }


}
