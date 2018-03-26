package com.team.dao;

import com.team.model.TerminalChargeRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午4:50 2018/3/26
 */
public interface TerminalChargeRecordDao {

    List<TerminalChargeRecord> list(@Param("tsid") Integer tsid);

    int save(TerminalChargeRecord terminalChargeRecord);

}
