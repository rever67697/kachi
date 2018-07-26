package com.team.service;

import com.team.model.TerminalChargeRecord;
import com.team.vo.ReturnMsg;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午5:22 2018/6/21
 */
public interface InterfaceService {

    /**查询终端剩余流量等信息**/
    ReturnMsg qtb(Integer tsid);

    /**查询终端流水和终端充值记录**/
    ReturnMsg qti(Integer tsid,Integer page,Integer rows);

    /**终端充值**/
    ReturnMsg tCharge(TerminalChargeRecord terminalChargeRecord);

    /**查询所有部门**/
    ReturnMsg qd();

    /**查询部门下的终端**/
    ReturnMsg qtbd(Integer departmentId,Integer tsid,Integer page,Integer rows);

    /**查询终端是否存在**/
    ReturnMsg qte(Integer tsid,Integer departmentId);

}
