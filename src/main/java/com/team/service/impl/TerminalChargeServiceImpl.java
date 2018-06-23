package com.team.service.impl;

import com.team.dao.FlowBalanceDao;
import com.team.dao.TerminalChargeRecordDao;
import com.team.model.FlowBalance;
import com.team.model.TerminalChargeRecord;
import com.team.service.TerminalChargeService;
import com.team.vo.ReturnMsg;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        if(origin!=null){
            flowBlanceDao.update(flowBalance);
        }else {
            flowBlanceDao.save(flowBalance);
        }
        //2.保存充值记录

        if(origin == null){
            origin = new FlowBalance();
        }
        if(!origin.getAllowFlow().equals(record.getAllowFlow())){
            record.setChargeFlow(record.getAllowFlow()-origin.getAllowFlow());
        }
        if(record.getChargeFlow()!=null || record.getChargeDate()!=null){
            terminalChargeRecordDao.save(record);
        }

        return super.successTip();
    }


}
