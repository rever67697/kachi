package com.team.service;

import com.team.model.TerminalChargeRecord;
import com.team.vo.ReturnMsg;

/**
 * @Author : wuzhiheng
 * @Description : 终端充值记录
 * @Date Created in 下午4:51 2018/3/26
 */
public interface TerminalChargeService {

    /**
     * 充值
     * @param record
     * @return
     */
    ReturnMsg charge(TerminalChargeRecord record);

}
