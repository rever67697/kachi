package com.team.dao;

import com.team.model.TerminalChargeRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午4:50 2018/3/26
 */
public interface TerminalChargeRecordDao {

    List<TerminalChargeRecord> list(Map<String,Object> map);

    Map<String,Object> count(Map<String,Object> map);

    int save(TerminalChargeRecord terminalChargeRecord);

}
