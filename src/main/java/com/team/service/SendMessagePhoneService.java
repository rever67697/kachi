package com.team.service;

import com.team.model.SendMesagePhone;
import com.team.vo.ReturnMsg;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 12:00 2019-03-08
 */
public interface SendMessagePhoneService {

    ReturnMsg get();

    ReturnMsg save(SendMesagePhone sendMesagePhone);

}
