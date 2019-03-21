package com.team.dao;

import com.team.model.SendMesagePhone;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 11:59 2019-03-08
 */
public interface SendMesagePhoneDao {

    SendMesagePhone getByUser(Integer departmentId);

    int save(SendMesagePhone sendMesagePhone);

    int update(SendMesagePhone sendMesagePhone);
}
