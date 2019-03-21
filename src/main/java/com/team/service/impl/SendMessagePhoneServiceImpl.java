package com.team.service.impl;

import com.team.dao.SendMesagePhoneDao;
import com.team.model.SendMesagePhone;
import com.team.service.SendMessagePhoneService;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : wuzhiheng
 * @Description : 取卡异常短信发送业务
 * @Date Created in 12:02 2019-03-08
 */
@Service
public class SendMessagePhoneServiceImpl extends BaseService implements SendMessagePhoneService {

    @Autowired
    private SendMesagePhoneDao sendMesagePhoneDao;

    @Override
    public ReturnMsg get() {
        Integer departmentId = getDepartmentId();
        return successTip(sendMesagePhoneDao.getByUser(departmentId));
    }

    @Override
    public ReturnMsg save(SendMesagePhone sendMesagePhone) {

        sendMesagePhone.setDepartmentId(getDepartmentId());

        if(sendMesagePhone.getId() == null)
            sendMesagePhoneDao.save(sendMesagePhone);
        else
            sendMesagePhoneDao.update(sendMesagePhone);

        return successTip();
    }
}
