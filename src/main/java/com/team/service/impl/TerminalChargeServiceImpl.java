package com.team.service.impl;

import com.team.dao.FLowBalanceDao;
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
    private FLowBalanceDao fLowBlanceDao;

    @Override
    public ReturnMsg charge(TerminalChargeRecord record) {

        //1.保存FlowBlance
        FlowBalance flowBalance = new FlowBalance();
        BeanUtils.copyProperties(record, flowBalance);

        if(fLowBlanceDao.count(record.getTsid())>0){
            fLowBlanceDao.update(flowBalance);
        }else {
            fLowBlanceDao.save(flowBalance);
        }
        //2.保存充值记录
        terminalChargeRecordDao.save(record);

        return super.successTip();
    }


}
