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
import java.net.URLEncoder;
import java.util.*;

/**
 * @Author : wuzhiheng
 * @Description :   对外开放的接口
 * @Date Created in 下午5:50 2018/6/21
 */
@Controller
public class InterfaceController extends BaseService {

    @Autowired
    private InterfaceService interfaceService;

    @Autowired
    private OperationLogDao operationLogDao;

    private List<String> INTEFACE_NAME = Arrays.asList(
            "qtb", "qti", "tCharge", "qd", "qtbd", "qte",
            "aliQuery", "aliCharge", "aliStatusQuery",
            "tOffline", "tChangeCard", "tPassword", "tCheck", "tTerminal"
    );

    @RequestMapping("/interface")
    @ResponseBody
    public ReturnMsg bussiness(String name,
                               @RequestParam(name = "time", defaultValue = "0") long time,
                               @RequestParam(name = "clearFlow", defaultValue = "false") boolean clearFlow,//是否清空流量
                               @RequestParam(name = "clearDate", defaultValue = "false") boolean clearDate,//是否清空日期
                               @RequestParam(name = "noLimit", defaultValue = "false") boolean noLimit,    //是否不限量
                               TerminalChargeRecord terminalChargeRecord,
                               HttpServletRequest request) throws Exception {

        ReturnMsg checkResult = validate(name, time, terminalChargeRecord, request);
        if (checkResult.getCode().equals(IConstant.CODE_ERROR)) {
            return checkResult;
        }

        ReturnMsg returnMsg = null;
        //查询终端流量信息
        if ("qtb".equals(name)) {
            returnMsg = interfaceService.qtb(terminalChargeRecord.getTsid());

        } else if ("qti".equals(name)) {//查询终端流水和充值记录
            returnMsg = interfaceService.qti(terminalChargeRecord.getTsid(), toInt(request, "page"), toInt(request, "rows"));

        } else if ("tCharge".equals(name)) {//终端充值

            if (terminalChargeRecord.getChargeFlow() == null && terminalChargeRecord.getChargeDate() == null
                    && !clearFlow && !clearDate && !noLimit) {
                return errorTip("参数有误");
            }
            returnMsg = interfaceService.tCharge(terminalChargeRecord, clearFlow, clearDate, noLimit);

        } else if ("qd".equals(name)) {//查询所有部门
            returnMsg = interfaceService.qd();

        } else if ("qtbd".equals(name)) {//通过部门编号查询所有的终端
            returnMsg = interfaceService.qtbd(toInt(request, "departmentId"),
                    toInt(request, "tsid"),
                    toInt(request, "page"),
                    toInt(request, "rows"));
        } else if ("qte".equals(name)) {//查询终端是否存在
            returnMsg = interfaceService.qte(terminalChargeRecord.getTsid(), toInt(request, "departmentId"));

        } else if ("aliCharge".equals(name)) {//调用阿里的充值接口
            returnMsg = interfaceService.aliCharge(toLong(request, "offerId"),
                    toLong(request, "phoneNumber"),
                    request.getParameter("outOrderId"));

        } else if ("aliQuery".equals(name)) {//调用阿里的查询接口
            returnMsg = interfaceService.aliQuery(toLong(request, "offerId"),
                    request.getParameter("channelType"),
                    request.getParameter("province"),
                    request.getParameter("vendor"));

        } else if ("aliStatusQuery".equals(name)) {
            returnMsg = interfaceService.aliStatusQuery(request.getParameter("outOrderId"));

        } else if ("tOffline".equals(name)) {//终端下线
            returnMsg = interfaceService.tOffline(terminalChargeRecord.getTsid());

        } else if ("tChangeCard".equals(name)) {//终端换卡
            returnMsg = interfaceService.tChangeCard(terminalChargeRecord.getTsid());

        } else if ("tPassword".equals(name)) {//终端更新密码
            returnMsg = interfaceService.tPassword(terminalChargeRecord.getTsid(), request.getParameter("wifiPassword"), request.getParameter("ssid"));

        } else if ("tCheck".equals(name)) {//检查终端是否在线
            returnMsg = interfaceService.tCheck(terminalChargeRecord.getTsid());

        } else if ("tTerminal".equals(name)) {//检查终端具体信息
            returnMsg = interfaceService.tTerminal(terminalChargeRecord.getTsid());

        }

        //保存日志
        if (!"1".equals(request.getParameter("s"))) {
            saveLog(request);
        }

        return returnMsg;
    }


    public void saveLog(HttpServletRequest request) {
        final OperationLog operationLog = new OperationLog("接口调用", "接口管理", request.getParameter("name"),
                CommonUtil.getParamDesc(request), null, IPUtils.getIpAddr(request), CommonUtil.browserInfo(request));
        LogManager.me().executeLog(new TimerTask() {
            @Override
            public void run() {
                operationLogDao.saveLog(operationLog);
            }
        });
    }

    public ReturnMsg validate(String name, long time, TerminalChargeRecord record, HttpServletRequest request) throws Exception {

        String flag = request.getParameter("s");

        if (!INTEFACE_NAME.contains(name)) {
            return errorTip("请求的接口不存在");
        }

        //请求一分钟内有效
        if (!"1".equals(flag)) {
            if (time < (System.currentTimeMillis() - 1 * 60 * 1000)) {
                return errorTip("请求超时");
            }
        }

        if (Arrays.asList("qtb", "qti", "tCharge", "qte", "tOffline", "tChangeCard", "tPassword", "tCheck", "tTerminal").contains(name) && record.getTsid() == null) {
            return errorTip("参数有误");
        }
        //qtbd
        if ("qtbd".equals(name)) {
            if (toInt(request, "departmentId") == null) {
                return errorTip("参数有误");
            }
        }

        //aliCharge
        if ("aliCharge".equals(name)) {
            String outOrderId = request.getParameter("outOrderId");
            if (toLong(request, "offerId") == null || toLong(request, "phoneNumber") == null || outOrderId == null || "".equals(outOrderId)) {
                return errorTip("参数有误");
            }
        }

        //qtbd
        if ("qtbd".equals(name) || "qti".equals(name)) {
            Integer page = toInt(request, "page");
            Integer rows = toInt(request, "rows");
            if ((page == null && rows != null) || (page != null && rows == null)) {
                return errorTip("参数有误");
            } else if (page != null && rows != null && (page < 1 || rows < 1)) {
                return errorTip("参数有误");
            }
        }

        //aliQuery
        if ("aliQuery".equals(name)) {
            String outOrderId = request.getParameter("outOrderId");
            String channelType = request.getParameter("channelType");
            if ((outOrderId != null && !"".equals(outOrderId)) && toLong(request, "outOrderId") == null) {
                return errorTip("参数有误");
            }
            if (channelType == null || "".equals(channelType)) {
                return errorTip("参数有误");
            }
        }

        if (!"1".equals(flag)) {
            Enumeration paramNames = request.getParameterNames();

            TreeMap<String, String> treeMap = new TreeMap<>();
            StringBuilder params = new StringBuilder();

            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                if (!"checkCode".equals(paramName)) {

                    //注意，因为针对一些敏感字符或者汉子，接口要求是先把参数编码化后再生成checkCode，然后在后台这里参数已经被解码过后了，所以这里要重新编码
                    treeMap.put(paramName, URLEncoder.encode(request.getParameter(paramName), "utf-8"));
                }
            }

            for (Map.Entry<String, String> entry : treeMap.entrySet()) {
                params.append(entry).append("&");
            }

            params.setLength(params.length() - 1);

            String verify_check_code = MD5Utils.encrypt(params.toString());

            if (!verify_check_code.equals(request.getParameter("checkCode"))) {
                return errorTip("校验失败-checkCode");
            }
        }

        return successTip();

    }

    public Integer toInt(HttpServletRequest request, String name) {
        String str = request.getParameter(name);
        try {
            return new Integer(str);
        } catch (Exception e) {
            System.out.println(str);
        }
        return null;
    }

    private Long toLong(HttpServletRequest request, String name) {
        String str = request.getParameter(name);
        try {
            return new Long(str);
        } catch (Exception e) {
            System.out.println(str);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        long time = System.currentTimeMillis();
        System.out.println(time);
//        System.out.println("name=qtb&time="+time+"&tsid=10160266");
//        System.out.println("name=qti&page=&rows=&time="+time+"&tsid=10160266");
//        System.out.println(MD5Utils.encrypt("name=qti&page=1&rows=5&time="+time+"&tsid=10160266"));
//        System.out.println(MD5Utils.encrypt("chargeDate=1&chargeFlow=2&clearDate=false&clearFlow=false&name=tCharge&noLimit=true&time="+time+"&tsid=10160266"));
//        System.out.println(MD5Utils.encrypt("name=qd&time="+time));
//        System.out.println(MD5Utils.encrypt("departmentId=0&name=qtbd&page=1&rows=20&time="+time+"&tsid=29627286"));
//        System.out.println(MD5Utils.encrypt("departmentId=1&name=qte&time="+time+"&tsid=29627286"));
//        System.out.println(MD5Utils.encrypt("name=aliCharge&offerId=22020000115116&phoneNumber=13570364320&time="+time));
//        System.out.println(MD5Utils.encrypt("name=aliQuery&time="+time));
        String str = "name=tPassword" + "&time=" + time + "&tsid=3496894" + "&wifiPassword=" + URLEncoder.encode("123456789+", "utf-8");
        System.out.println(str + "&checkCode=" + MD5Utils.encrypt(str));

    }

}