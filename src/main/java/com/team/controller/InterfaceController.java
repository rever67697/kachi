package com.team.controller;

import com.team.dao.TerminalDao;
import com.team.dao.auth.OperationLogDao;
import com.team.model.Terminal;
import com.team.model.TerminalChargeRecord;
import com.team.model.auth.OperationLog;
import com.team.model.auth.TbAuthUser;
import com.team.service.InterfaceService;
import com.team.service.auth.TbAuthUserService;
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

    @Autowired
    private TerminalDao terminalDao;

    @Autowired
    private TbAuthUserService tbAuthUserService;

    @Autowired
    private HttpServletRequest request;

    private List<String> INTEFACE_NAME = Arrays.asList(
            "qtb", "qti", "tCharge", "qd", "qtbd", "qte",
            "aliQuery", "aliCharge", "aliStatusQuery",
            "tOffline", "tChangeCard", "tPassword", "tCheck", "tTerminal",
            "qtoi"
    );

    @RequestMapping("/interface")
    @ResponseBody
    public ReturnMsg bussiness(String name,
                               @RequestParam(name = "time", defaultValue = "0") long time,
                               @RequestParam(name = "clearFlow", defaultValue = "false") boolean clearFlow,//是否清空流量
                               @RequestParam(name = "clearDate", defaultValue = "false") boolean clearDate,//是否清空日期
                               @RequestParam(name = "noLimit", defaultValue = "false") boolean noLimit,    //是否不限量
                               TerminalChargeRecord terminalChargeRecord) throws Exception {
        //验证身份信息
        ReturnMsg checkResult = validate(name, time);

        //校验不通过直接返回
        if (checkResult.getCode().equals(IConstant.CODE_ERROR)) {
            return checkResult;
        }

        ReturnMsg returnMsg = null;
        //查询终端流量信息
        if ("qtb".equals(name)) {
            returnMsg = interfaceService.qtb(terminalChargeRecord.getTsid());

        } else if ("qti".equals(name)) {//查询终端流水和充值记录
            returnMsg = interfaceService.qti(terminalChargeRecord.getTsid(), toInt("page"), toInt("rows"));

        } else if ("tCharge".equals(name)) {//终端充值

            if (terminalChargeRecord.getChargeFlow() == null && terminalChargeRecord.getChargeDate() == null
                    && !clearFlow && !clearDate && !noLimit) {
                return errorTip("参数有误");
            }
            returnMsg = interfaceService.tCharge(terminalChargeRecord, clearFlow, clearDate, noLimit);

        } else if ("qd".equals(name)) {//查询所有部门
            returnMsg = interfaceService.qd();

        } else if ("qtbd".equals(name)) {//通过部门编号查询所有的终端
            returnMsg = interfaceService.qtbd(toInt("departmentId"),
                    toInt("tsid"),
                    toInt("page"),
                    toInt("rows"));
        } else if ("qte".equals(name)) {//查询终端是否存在
            returnMsg = interfaceService.qte(terminalChargeRecord.getTsid(), toInt("departmentId"));

        } else if ("aliCharge".equals(name)) {//调用阿里的充值接口
            returnMsg = interfaceService.aliCharge(toLong("offerId"),
                    toLong("phoneNumber"),
                    request.getParameter("outOrderId"));

        } else if ("aliQuery".equals(name)) {//调用阿里的查询接口
            returnMsg = interfaceService.aliQuery(toLong("offerId"),
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

        }else if ("qtoi".equals(name)) {//检查在线终端的一些连接信息，连接数，信号等
            returnMsg = interfaceService.qtoi(terminalChargeRecord.getTsid());

        }

        //保存日志
        saveLog();

        return returnMsg;
    }


    public void saveLog() {
        TbAuthUser user = (TbAuthUser) request.getAttribute("user");
        if(user == null){
            user = new TbAuthUser();
        }
        final OperationLog operationLog = new OperationLog(user.getName(), "接口管理", request.getParameter("name"),
                CommonUtil.getParamDesc(request), user.getDepartmentId(), IPUtils.getIpAddr(request), CommonUtil.browserInfo(request));
        LogManager.me().executeLog(new TimerTask() {
            @Override
            public void run() {
                operationLogDao.saveLog(operationLog);
            }
        });
    }

    public ReturnMsg validate(String name, long time) throws Exception {

        if (!INTEFACE_NAME.contains(name)) {
            return errorTip("请求的接口不存在");
        }

        //请求一分钟内有效
        if (time < (System.currentTimeMillis() - 1 * 60 * 1000)) {
            return errorTip("请求超时");
        }

        //校验参数
        if(!validParams(name)){
            return errorTip("参数错误");
        }

        //判断checkCode
        if (!validCheckCode(request))
            return errorTip("校验失败-checkCode");

        //校验用户身份
        ReturnMsg ret = validUser(name);

        return ret;

    }

    private ReturnMsg validUser(String name){
        //阿里的接口不需要用户身份校验
        if(Arrays.asList("aliQuery", "aliCharge", "aliStatusQuery").contains(name)){
            return successTip();
        }

        //添加用户校验
        String userName = request.getParameter("n");
        String userPwd = request.getParameter("p");

        if(userName == null || "".equals(userName) || userPwd == null || "".equals(userPwd)){
            return errorTip("参数有误");
        }

        TbAuthUser user = tbAuthUserService.getUserByName(userName);
        if (user == null || !userPwd.equals(user.getPassWord())){
            return errorTip("用户名或密码错误");
        }

        //需要校验用户传过来的终端是否在其用户下
        if (Arrays.asList("qtb", "qti", "tCharge", "qte", "tOffline", "tChangeCard", "tPassword", "tCheck", "tTerminal","qtoi").contains(name)){
            Map<String,Object> map = new HashMap<>();
            map.put("tsid",toInt("tsid"));
            map.put("dId",user.getDepartmentId() != null && user.getDepartmentId() == 0 ? null : user.getDepartmentId());

            Terminal terminal = terminalDao.getByTsid(map);
            if(terminal == null)
                return errorTip(String.format("操作失败，终端号-%s不存在",request.getParameter("tsid")));
        }
        else if("qtbd".equals(name) && (user.getDepartmentId() != 0 && user.getDepartmentId() != toInt("departmentId"))){
            return errorTip(String.format("操作失败，departmentId-%s不存在",toInt("departmentId").toString()));
        }

        //在当前request暂存user
        request.setAttribute("user",user);

        return successTip();
    }

    private boolean validParams(String name){
        //下面这些接口必须要穿tsid
        if (Arrays.asList("qtb", "qti", "tCharge", "qte", "tOffline", "tChangeCard", "tPassword", "tCheck", "tTerminal","qtoi").contains(name) && toInt("tsid") == null) {
            return false;
        }
        //qtbd
        if ("qtbd".equals(name)) {
            if (toInt("departmentId") == null) {
                return false;
            }
        }

        //aliCharge
        if ("aliCharge".equals(name)) {
            String outOrderId = request.getParameter("outOrderId");
            if (toLong("offerId") == null || toLong("phoneNumber") == null || outOrderId == null || "".equals(outOrderId)) {
                return false;
            }
        }

        //qtbd
        if ("qtbd".equals(name) || "qti".equals(name)) {
            Integer page = toInt("page");
            Integer rows = toInt("rows");
            if ((page == null && rows != null) || (page != null && rows == null)) {
                return false;
            } else if (page != null && rows != null && (page < 1 || rows < 1)) {
                return false;
            }
        }

        //aliQuery
        if ("aliQuery".equals(name)) {
            String outOrderId = request.getParameter("outOrderId");
            String channelType = request.getParameter("channelType");
            if ((outOrderId != null && !"".equals(outOrderId)) && toLong("outOrderId") == null) {
                return false;
            }
            if (channelType == null || "".equals(channelType)) {
                return false;
            }
        }

        return true;
    }

    private boolean validCheckCode(HttpServletRequest request)throws Exception{
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

        return verify_check_code.equals(request.getParameter("checkCode"));
    }

    public Integer toInt(String name) {
        String str = request.getParameter(name);
        try {
            return new Integer(str);
        } catch (Exception e) {
            System.out.println(str);
        }
        return null;
    }

    private Long toLong(String name) {
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
        StringBuilder sb = new StringBuilder();
        Map<String,Object> map = new TreeMap<>();
        map.put("name","tCheck");
        map.put("time",time);
        map.put("tsid","79123480");

        map.put("n","admin");
        map.put("p","496ed5603b21ed0ff5200560b1fe9653");

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry).append("&");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("&checkCode="+MD5Utils.encrypt(sb.toString()));
        System.out.println(sb.toString());

    }

}