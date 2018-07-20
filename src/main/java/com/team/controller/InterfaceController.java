package com.team.controller;

import com.team.dao.auth.OperationLogDao;
import com.team.model.TerminalChargeRecord;
import com.team.model.auth.OperationLog;
import com.team.service.InterfaceService;
import com.team.service.impl.BaseService;
import com.team.util.*;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    private List<String> INTEFACE_NAME = Arrays.asList("qtb","qti","tCharge","qd","qtbd");

    @RequestMapping("/interface")
    @ResponseBody
    public ReturnMsg bussiness(String name,
                               @RequestParam(name = "time",defaultValue = "0") long time ,
                               TerminalChargeRecord terminalChargeRecord,
                               HttpServletRequest request){

        ReturnMsg checkResult = validate(name, time, terminalChargeRecord, request);
        if(checkResult.getCode().equals(IConstant.CODE_ERROR)){
            return checkResult;
        }

        ReturnMsg returnMsg = null;
        //查询终端流量信息
        if("qtb".equals(name)){
            returnMsg = interfaceService.qtb(terminalChargeRecord.getTsid());
        }else if("qti".equals(name)){//查询终端流水和充值记录
            returnMsg = interfaceService.qti(terminalChargeRecord.getTsid(),toInt(request,"page"),toInt(request,"rows"));
        }else if("tCharge".equals(name)){//终端充值

            if(terminalChargeRecord.getChargeFlow()==null && terminalChargeRecord.getChargeDate()==null){
                return errorTip("参数有误");
            }

            returnMsg = interfaceService.tCharge(terminalChargeRecord);
        }else if("qd".equals(name)){//查询所有部门
            returnMsg = interfaceService.qd();
        }else if("qtbd".equals(name)){//通过部门编号查询所有的终端
            returnMsg = interfaceService.qtbd(toInt(request,"departmentId"),
                                              toInt(request,"tsid"),
                                              toInt(request,"page"),
                                              toInt(request,"rows"));
        }

        //保存日志
        saveLog(request);

        return returnMsg;
    }


    public void saveLog(HttpServletRequest request){
        final OperationLog operationLog = new OperationLog(null, "接口管理", request.getParameter("name"),
                CommonUtil.getParamDesc(request),null, IPUtils.getIpAddr(request),CommonUtil.browserInfo(request));
        LogManager.me().executeLog(new TimerTask() {
            @Override
            public void run() {
                operationLogDao.saveLog(operationLog);
            }
        });
    }

    public ReturnMsg validate(String name,long time,TerminalChargeRecord record,HttpServletRequest request){


        if(!INTEFACE_NAME.contains(name)){
            return errorTip("请求的接口不存在");
        }

        //请求一分钟内有效
        if(time<(System.currentTimeMillis()-1*60*1000)){
            return errorTip("请求超时");
        }

        if(Arrays.asList("qtb","qti","tCharge").contains(name) && record.getTsid()==null){
            return errorTip("参数有误");
        }
        //qtbd
        if("qtbd".equals(name)){
            if(toInt(request,"departmentId")==null){
                return errorTip("参数有误");
            }
        }

        if("qtbd".equals(name) || "qti".equals(name)){
            Integer page = toInt(request,"page");
            Integer rows = toInt(request,"rows");
            if((page==null && rows!=null) || (page!=null && rows==null)){
                return errorTip("参数有误");
            }else if (page!=null && rows!=null && (page<1 || rows<1)){
                return errorTip("参数有误");
            }
        }


        Enumeration params = request.getParameterNames();
        List<String> list = new ArrayList<>();

        String verify_source = "";

        while (params.hasMoreElements()){
            String paramName = (String) params.nextElement();
            if(!"checkCode".equals(paramName)){
                list.add(paramName+"="+request.getParameter(paramName));
            }
        }

        Collections.sort(list);

        for (String s : list) {
            verify_source+=s+"&";
        }

        verify_source = verify_source.substring(0,verify_source.length()-1);

        String verify_check_code = MD5Utils.encrypt(verify_source);

        if(!verify_check_code.equals(request.getParameter("checkCode"))){
            return errorTip("校验失败-checkCode");
        }


        return successTip();

    }

    public Integer toInt(HttpServletRequest request,String name){
        String str = request.getParameter(name);
        try {
            return new Integer(str);
        }catch (Exception e){
            System.out.println(str);
        }
        return  null;
    }

    public static void main(String[] args){
        long time = System.currentTimeMillis();
        System.out.println(time);
//        System.out.println("name=qtb&time="+time+"&tsid=10160266");
//        System.out.println("name=qti&page=&rows=&time="+time+"&tsid=10160266");
//        System.out.println(MD5Utils.encrypt("name=qti&page=1&rows=5&time="+time+"&tsid=10160266"));
//        System.out.println(MD5Utils.encrypt("chargeDate=2&chargeFlow=2&name=tCharge&time="+time+"&tsid=10160266"));
//        System.out.println(MD5Utils.encrypt("name=qd&time="+time));
        System.out.println(MD5Utils.encrypt("departmentId=0&name=qtbd&page=1&rows=20&time="+time+"&tsid=29627286"));

    }



}
