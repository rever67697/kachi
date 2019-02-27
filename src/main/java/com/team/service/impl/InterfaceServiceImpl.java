package com.team.service.impl;

import com.alicom.mns.tools.DefaultAlicomMessagePuller;
import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dycdpapi.model.v20180610.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.team.dao.*;
import com.team.dao.auth.DepartmentDao;
import com.team.dao.auth.OperationLogDao;
import com.team.dto.TerminalDTO;
import com.team.model.*;
import com.team.model.auth.Department;
import com.team.model.auth.OperationLog;
import com.team.service.InterfaceService;
import com.team.service.TerminalChargeService;
import com.team.service.TerminalService;
import com.team.service.TerminalSimService;
import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.util.IPUtils;
import com.team.util.LogManager;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.*;

/**
 * @Author : wuzhiheng
 * @Description :   对外开放的接口
 * @Date Created in 下午5:30 2018/6/21
 */
@Service
@Transactional
public class InterfaceServiceImpl extends BaseService implements InterfaceService {

    @Autowired
    private CostDayDao costDayDao;
    @Autowired
    private TerminalDao terminalDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private TerminalSimDao terminalSimDao;
    @Autowired
    private FlowBalanceDao flowBalanceDao;
    @Autowired
    private TerminalSimFLowDao terminalSimFLowDao;
    @Autowired
    private TerminalChargeRecordDao terminalChargeRecordDao;

    @Autowired
    private TerminalService terminalService;
    @Autowired
    private TerminalSimService terminalSimService;


    @Value("${aliyun.accessKey}")
    private String accessKey;

    @Value("${aliyun.accessSecurity}")
    private String accessSecurity;

    @Value("${bookMessage}")
    private boolean bookMessage;

    static IAcsClient client = null;


    @Override
    public ReturnMsg qtb(Integer tsid) {
        FlowBalance flowBalance = flowBalanceDao.findByTsid(tsid);
        return successTip(flowBalance);
    }

    @Override
    public ReturnMsg qti(Integer tsid,Integer page,Integer rows) {
        //查询终端充值记录
        Map<String,Object> map = new HashMap<>();
        map.put("tsid",tsid);
        List<TerminalChargeRecord> terminalChargeRecordList = terminalChargeRecordDao.list(map);

        //查询终端流水
        if (page != null && rows != null){
            PageHelper.startPage(page, rows);
        }
        List<CostDay> costDayList = costDayDao.getCostDayByTsid(tsid);

        map.clear();
        map.put("flowRecord",costDayList);
        map.put("chargeRecord",terminalChargeRecordList);

        return successTip(map);
    }

    @Override
    public ReturnMsg tCharge(TerminalChargeRecord terminalChargeRecord,boolean clearFlow,boolean clearDate,boolean noLimit) {
        Map<String,Object> map = new HashMap<>();
        map.put("tsid",terminalChargeRecord.getTsid());
        Terminal terminal = terminalDao.getByTsid(map);
        if(terminal==null){
            return errorTip("充值失败，终端不存在");
        }

        //获取源流量和日期参数
        FlowBalance flowBalance = flowBalanceDao.findByTsid(terminalChargeRecord.getTsid());
        if(flowBalance==null){
            flowBalance = new FlowBalance();
            flowBalance.setTsid(terminalChargeRecord.getTsid());
        }

        terminalChargeRecord.setOriginFlow(flowBalance.getAllowFlow());
        terminalChargeRecord.setOriginDate(flowBalance.getValidityDate());

        //1.处理流量，单位G
        if(terminalChargeRecord.getChargeFlow()!=null){
            Long allowFlow = flowBalance.getAllowFlow()==null?0L:flowBalance.getAllowFlow();
            if(allowFlow<0){
                allowFlow=0L;
            }
            flowBalance.setAllowFlow(allowFlow+terminalChargeRecord.getChargeFlow()*1024*1024);
            terminalChargeRecord.setChargeFlow(terminalChargeRecord.getChargeFlow()*1024*1024);
        }

        //2.处理天数，单位天
        if(terminalChargeRecord.getChargeDate()!=null){
            Date validityDate = flowBalance.getValidityDate()==null?new Date():flowBalance.getValidityDate();
            if(validityDate.before(new Date())){
                validityDate = new Date();
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(validityDate);
            calendar.add(Calendar.DATE,terminalChargeRecord.getChargeDate());
            flowBalance.setValidityDate(calendar.getTime());

        }

        if(clearFlow){
            flowBalance.setAllowFlow(0L);
            terminalChargeRecord.setChargeFlow(null);
        }
        if(clearDate){
            flowBalance.setValidityDate(null);
            terminalChargeRecord.setChargeDate(null);
        }

        //特殊处理，无限量100000000000kb
        if(noLimit){
            flowBalance.setAllowFlow(IConstant.NO_LIMIT);
            terminalChargeRecord.setChargeFlow(IConstant.NO_LIMIT);
        }

        //3.填充充值数据
        terminalChargeRecord.setAllowFlow(flowBalance.getAllowFlow());
        terminalChargeRecord.setValidityDate(flowBalance.getValidityDate());
        terminalChargeRecord.setCreateDate(new Date());

        //4.保存终端信息
        if(flowBalance.getId()!=null){
            flowBalanceDao.update(flowBalance);
        }else {
            flowBalanceDao.save(flowBalance);
        }

        //5.保存充值流水
        terminalChargeRecord.setOperator("接口充值");
        int count = terminalChargeRecordDao.save(terminalChargeRecord);

        return count>0?successTip():errorTip();
    }

    @Override
    public ReturnMsg qd() {
        List<Department> list = departmentDao.getAllDepartment();
        return successTip(list);
    }

    @Override
    public ReturnMsg qtbd(Integer departmentId, Integer tsid, Integer page, Integer rows) {
        if (page != null && rows != null){
            PageHelper.startPage(page, rows);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("departmentId",departmentId);
        map.put("tsid",tsid);

        List<TerminalDTO> list = terminalDao.qtbd(map);

        return successTip(list);
    }

    @Override
    public ReturnMsg qte(Integer tsid,Integer departmentId) {
        Map<String,Object> map = new HashMap<>();
        map.put("tsid",tsid);
        map.put("departmentId",departmentId);

        Terminal terminal = terminalDao.getByTsid(map);
        map.clear();
        map.put("exist",terminal!=null);
        return successTip(map);
    }

    private void init() throws ClientException {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //产品名称
        final String product = "Dycdpapi";
        //域
        final String domain = "dycdpapi.aliyuncs.com";
        //初始化客户端
        IClientProfile profile = DefaultProfile.getProfile("cn‐hangzhou", accessKey, accessSecurity);
        DefaultProfile.addEndpoint("cn‐hangzhou", "cn‐hangzhou", product, domain);
        client = new DefaultAcsClient(profile);
    }

    @Override
    public ReturnMsg aliCharge(Long offerId, Long phoneNumber,String outOrderId) {
        try {
            init();
            CreateCdpOrderRequest request = new CreateCdpOrderRequest();
            request.setPhoneNumber(phoneNumber.toString());
            System.out.println("outOrderId==="+outOrderId);
            request.setOutOrderId(outOrderId);
            request.setOfferId(offerId);
            CreateCdpOrderResponse acsResponse = client.getAcsResponse(request);
            System.out.println("RequestId:" + acsResponse.getRequestId());
            System.out.println("Code:" + acsResponse.getCode());
            System.out.println("Message:" + acsResponse.getMessage());

            if ("OK".equals(acsResponse.getCode())) {
                //业务处理
                CreateCdpOrderResponse.Data data = acsResponse.getData();
                System.out.println("ResultCode:" + data.getResultCode());
                System.out.println("ResultMsg:" + data.getResultMsg());
                System.out.println("OrderId:" + data.getOrderId());
                System.out.println("ExtendParam:" + data.getExtendParam());

                QueryCdpOrderResponse response = (QueryCdpOrderResponse)aliStatusQuery(outOrderId).getData();

                Map<String,Object> map = new HashMap<>();
                map.put("detailInfo",response);
                map.put("info",acsResponse);
                return successTip(map);
            } else {
                System.out.println("error");
                // 说明请求超时，没有拿到结果，需要用该outOrderId重试，获取幂等结果
                //如果重试获取的CreateCdpOrderResponse.Code为10004或00000则可标识下单成功
                //10004表示重复的outOrderId，说明可能是上次超时的请求已下单成功，或之前你用过该outOrderId下过单
                //你自己要保证该outOrderId的唯一
                //00000表示下单成功，之前超时的请求没有成功。
                acsResponse = client.getAcsResponse(request);
                if ("OK".equals(acsResponse.getCode())) {
                    QueryCdpOrderResponse response = (QueryCdpOrderResponse)aliStatusQuery(outOrderId).getData();

                    Map<String,Object> map = new HashMap<>();
                    map.put("detailInfo",response);
                    map.put("info",acsResponse);
                    return successTip(map);
                }else {
                    return successTip("充值失败");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return errorTip("请求失败");
        }
    }

    @Override
    public ReturnMsg aliQuery(Long offerId,String channelType,String province,String vendor) {
        try {
            init();
            AcsResponse acsResponse = null;
            if(offerId!=null){
                QueryCdpOfferByIdRequest request = new QueryCdpOfferByIdRequest();
                request.setOfferId(offerId);
                acsResponse = client.getAcsResponse(request);
            }else {
                QueryCdpOfferRequest request = new QueryCdpOfferRequest();
                request.setVendor(vendor);
                request.setChannelType(channelType);
                request.setProvince(province);
                acsResponse = client.getAcsResponse(request);
            }
            return successTip(acsResponse);
        } catch (ClientException e) {
            e.printStackTrace();

            return errorTip();
        }
    }

    @Override
    public ReturnMsg aliStatusQuery(String outOrderId) {
        try {
            init();
            QueryCdpOrderRequest request = new QueryCdpOrderRequest();
            request.setOutOrderId(outOrderId);
            QueryCdpOrderResponse acsResponse = client.getAcsResponse(request);
            System.out.println("RequestId:" + acsResponse.getRequestId());
            System.out.println("Code:" + acsResponse.getCode());
            System.out.println("Message:" + acsResponse.getMessage());
            if ("OK".equals(acsResponse.getCode())) {
                //业务处理
                QueryCdpOrderResponse.Data data = acsResponse.getData();
                System.out.println("ResultCode:" + data.getResultCode());
                System.out.println("ResultMsg:" + data.getResultMsg());
                System.out.println("ExtendParam:" + data.getExtendParam());
                return successTip(acsResponse);
            } else {
                // 说明请求超时，没有拿到结果
                return errorTip("请求失败");
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return errorTip();
        }
    }

    static class MyMessageListener implements MessageListener {
        private Gson gson=new Gson();
        @Override
        public boolean dealMessage(Message message) {

            String toUrl = "http://120.77.58.60/flow/index.php/Api/Response/cb/ccode/aliflow";

            //消息的几个关键值
//            System.out.println("message handle: " + message.getReceiptHandle());
//            System.out.println("message body: " + message.getMessageBodyAsString());
//            System.out.println("message id: " + message.getMessageId());
//            System.out.println("message dequeue count:" + message.getDequeueCount());

            HttpURLConnection conn = null;
            try{
                Map<String,Object> contentMap=gson.fromJson(message.getMessageBodyAsString(), HashMap.class);
                String phone=(String)contentMap.get("phone");
                String outId=(String)contentMap.get("out_id");
                String result=(String)contentMap.get("result");
                String errCode=(String)contentMap.get("err_code");
                String errMsg=(String)contentMap.get("err_msg");

                String params = "/phone/"+phone+"/outId/"+outId+"/result/"+result+"/errCode/"+errCode+"/errMsg/"+errMsg;

                System.out.println(toUrl+params);
                URL url = new URL(toUrl+params);
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(false);
                conn.setDoInput(true);
                conn.setUseCaches(false);
                conn.setConnectTimeout(10000);

                conn.connect();
                System.out.println("response:"+conn.getResponseCode());

                //TODO 这里开始编写您的业务代码

            }catch(com.google.gson.JsonSyntaxException e){
                //理论上不会出现格式错误的情况，所以遇见格式错误的消息，只能先delete,否则重新推送也会一直报错
                return true;
            } catch (Throwable e) {
                //您自己的代码部分导致的异常，应该return false,这样消息不会被delete掉，而会根据策略进行重推
                return false;
            }finally {
                conn.disconnect();
            }

            //消息处理成功，返回true, SDK将调用MNS的delete方法将消息从队列中删除掉
            return true;
        }

    }

    public void aliMessage() throws com.aliyuncs.exceptions.ClientException, ParseException {

        if(bookMessage){
            DefaultAlicomMessagePuller puller=new DefaultAlicomMessagePuller();

            //TODO 此处需要替换成开发者自己的AK信息
            String accessKeyId=accessKey;
            String accessKeySecret=accessSecurity;

            /*
            * TODO 将messageType和queueName替换成您需要的消息类型名称和对应的队列名称
            *云通信产品下所有的回执消息类型:
            *1:短信回执：SmsReport，
            *2:短息上行：SmsUp
            *3:语音呼叫：VoiceReport
            *4:流量直冲：FlowReport
            */
            String messageType="FlowReport";//此处应该替换成相应产品的消息类型
            String queueName="Alicom-Queue-1059202185248422-FlowReport";//在云通信页面开通相应业务消息后，就能在页面上获得对应的queueName,每一个消息类型

            System.out.println("订阅消息中。。。");
            puller.startReceiveMsg(accessKeyId,accessKeySecret, messageType, queueName, new MyMessageListener());
        }

    }

    @Override
    public ReturnMsg tOffline(Integer tsid) {
        return terminalService.offline(tsid);
    }

    @Override
    public ReturnMsg tChangeCard(Integer tsid) {
        return terminalSimService.changeCard(tsid);
    }

    @Override
    public ReturnMsg tPassword(Integer tsid,String wifiPassword,String ssid) {
        return terminalService.updateWiFiPass(tsid,wifiPassword,ssid);
    }

    @Override
    public ReturnMsg tCheck(Integer tsid) {
        //1.先查询终端是否在线
        TerminalSim terminalSim = terminalSimDao.getByTsid(tsid);
        boolean isOnline = terminalSim != null;
        Integer flag = isOnline ? 1 : null;

        Map<String,Object> map = new HashMap<>();
        map.put("tsid",tsid);
        map.put("flag",flag);

        //2.查询时间
        String time = terminalSimFLowDao.queryTime(map);

        map = new TreeMap<>();
        map.put("isOnline",isOnline);//是否在线
        map.put("onlineTime",isOnline ? time : "");//在线开始时间
        map.put("offlineTime",isOnline ? "" : time);//下线时间
        return successTip(map);
    }

    @Override
    public ReturnMsg tTerminal(Integer tsid) {
        Map<String,Object> map = new HashMap<>();
        map.put("tsid",tsid);
        return successTip(terminalDao.getByTsid(map));
    }

}
