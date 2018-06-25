package com.team.controller;

import com.team.dao.auth.OperationLogDao;
import com.team.model.TerminalChargeRecord;
import com.team.model.auth.OperationLog;
import com.team.service.InterfaceService;
import com.team.service.impl.BaseService;
import com.team.util.CommonUtil;
import com.team.util.IPUtils;
import com.team.util.LogManager;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

/**
 * @Author : wuzhiheng
 * @Description :   对外开放的接口
 * @Date Created in 下午5:50 2018/6/21
 */
@Controller
public class InterfaceController  extends BaseService{

    @Autowired
    private InterfaceService interfaceService;
    @Autowired
    private OperationLogDao operationLogDao;

    private List<String> INTEFACE_NAME = Arrays.asList("qtb","qti","tCharge");

    @RequestMapping("/interface")
    @ResponseBody
    public ReturnMsg bussiness(String name, TerminalChargeRecord terminalChargeRecord,HttpServletRequest request){

        if(!checkName(name)){
            return errorTip("接口名有误");
        }

        if(terminalChargeRecord.getTsid()==null){
            return errorTip("参数有误（终端编号）");
        }

        ReturnMsg returnMsg = null;
        //查询终端流量信息
        if("qtb".equals(name)){
            returnMsg = interfaceService.qtb(terminalChargeRecord.getTsid());
        }else if("qti".equals(name)){//查询终端流水和充值记录
            returnMsg = interfaceService.qti(terminalChargeRecord.getTsid());
        }else if("tCharge".equals(name)){//终端充值

            if(terminalChargeRecord.getChargeFlow()==null && terminalChargeRecord.getChargeDate()==null){
                return errorTip("参数有误（充值流量或充值天数）");
            }

            returnMsg = interfaceService.tCharge(terminalChargeRecord);
        }
        saveLog(request);

        return returnMsg;
    }

    public boolean checkName(String name){
        return INTEFACE_NAME.contains(name);
    }

    public void saveLog(HttpServletRequest request){
        final OperationLog operationLog = new OperationLog(null, "接口", request.getParameter("name"),
                CommonUtil.getParamDesc(request),null, IPUtils.getIpAddr(request),CommonUtil.browserInfo(request));
        LogManager.me().executeLog(new TimerTask() {
            @Override
            public void run() {
                operationLogDao.saveLog(operationLog);
            }
        });
    }

}
