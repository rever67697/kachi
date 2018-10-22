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
    ReturnMsg tCharge(TerminalChargeRecord terminalChargeRecord,boolean clearFlow,boolean clearDate,boolean noLimit);

    /**查询所有部门**/
    ReturnMsg qd();

    /**查询部门下的终端**/
    ReturnMsg qtbd(Integer departmentId,Integer tsid,Integer page,Integer rows);

    /**查询终端是否存在**/
    ReturnMsg qte(Integer tsid,Integer departmentId);

    /**阿里云充值**/
    ReturnMsg aliCharge(Long offerId, Long phoneNumber,String outOrderId);

    /**阿里云查询流量套餐**/
    ReturnMsg aliQuery(Long offerId,String channelType,String province,String vendor);

    /**阿里云订单状态查询**/
    ReturnMsg aliStatusQuery(String outOrderId);

    /**订阅阿里消息**/
    void aliMessage() throws Exception;

    /**终端下线**/
    ReturnMsg tOffline(Integer tsid);

    /**终端换卡**/
    ReturnMsg tChangeCard(Integer tsid);

    /**终端更新密码**/
    ReturnMsg tPassword(Integer tsid,String wifiPassword);

    /**检查终端是否在线**/
    ReturnMsg tCheck(Integer tsid);
}
