package com.team.jobs;

import com.team.dao.QuartzCronDao;
import com.team.dao.SelectCardDao;
import com.team.dto.SelectCardDTO;
import com.team.model.QuartzCron;
import com.team.model.SelectCard;
import com.team.util.CommonUtil;
import com.team.util.LogManager;
import com.team.util.MD5Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author : wuzhiheng
 * @Description : 定时任务，发送短信-取卡异常
 * @Date Created in 下午5:21 2018/7/2
 */
@Component
public class SendMessageTask {

    private Logger logger = Logger.getLogger(SendMessageTask.class);

    @Autowired
    private QuartzCronDao quartzCronDao;

    @Autowired
    private SelectCardDao selectCardDao;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Map<String, String> resultMsg = new HashMap<String, String>() {{
        put("1", "无卡可用");
        put("2", "设备欠费");
        put("3", "没有订单");
        put("4", "拒绝接入");
    }};

    public static Map<Integer,Set<Integer>> MAP = new HashMap<>();

    @Scheduled(cron = "0 0 0 * * ?")
    public void clear(){
        System.out.println("清空MAP");
        MAP.clear();
    }

    private void run() throws Exception {

        QuartzCron quartzCron = quartzCronDao.get();
        Integer minute = quartzCron.getMsgMinute();

        Map<String, Object> map = new HashMap<>();
        try {
            Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            map.put("startDate", new Date(date.getTime() - minute * 60 * 1000));
            map.put("endDate", new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<SelectCardDTO> list = selectCardDao.listNoCard(map);

        List<List<SelectCardDTO>> tempList = new ArrayList<>();

        Integer departmentId = null;
        if(CommonUtil.listNotBlank(list)){
            for (SelectCardDTO selectCard : list) {

                if (selectCard.getPhoneNumber() == null || "".equals(selectCard.getPhoneNumber()))
                    continue;

                if(MAP.get(selectCard.getDepartmentId()) == null)
                    MAP.put(selectCard.getDepartmentId(),new HashSet<>());

                if (!MAP.get(selectCard.getDepartmentId()).contains(selectCard.getTsid())){
                    MAP.get(selectCard.getDepartmentId()).add(selectCard.getTsid());

                    if (tempList.isEmpty() || !departmentId.equals(selectCard.getDepartmentId())){
                        List<SelectCardDTO> ll = new ArrayList<>();
                        ll.add(selectCard);
                        tempList.add(ll);
                    }else {
                        tempList.get(tempList.size()-1).add(selectCard);
                    }
                }

                departmentId = selectCard.getDepartmentId();
            }
        }

        Map<String, String> paramMap;
        StringBuilder params;

        for (List<SelectCardDTO> selectCardDTOS : tempList) {
            String tsid = "";

            int flag = 0;

            paramMap = new TreeMap<>();


            for (SelectCardDTO selectCard : selectCardDTOS) {
                if (flag < 3)
                    tsid += selectCard.getTsid() + ";";

                flag++;
            }

            tsid = tsid.substring(0, tsid.length() - 1);
            if (selectCardDTOS.size() > 3) {
                tsid += "等" + selectCardDTOS.size() + "台";
            }

            paramMap.put("name", "cardselect");
            paramMap.put("tsid", tsid);
            paramMap.put("result", "1-" + resultMsg.get("1"));
            paramMap.put("date", sdf.format(selectCardDTOS.get(0).getSelectDate()));

            for (String phoneNumber : selectCardDTOS.get(0).getPhoneNumber().split(",")) {
                paramMap.put("tel", phoneNumber);

                params = new StringBuilder();
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    params.append(entry.getKey())
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
                }
                params.setLength(params.length() - 1);
                params.append("&sign=" + MD5Utils.encrypt(params.toString()));
                //System.out.println(quartzCron.getMsgUrl() + params.toString());
                //http://yunmifi.ytxun.cn/ntkcard/index.php/UTIL/index/cardselectfail?
                sendMsg(quartzCron.getMsgUrl() + params.toString());
            }

        }

        logger.info("现在时间:" + sdf.format(new Date()) + " 执行处理发送短信任务!");
    }


    private void sendMsg(String toUrl) {

        LogManager.me().executeLog(new TimerTask() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(toUrl);
                    conn = (HttpURLConnection) url.openConnection();

                    conn.setDoOutput(false);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setConnectTimeout(10000);

                    conn.connect();
                    System.out.println("response:" + conn.getResponseCode());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    conn.disconnect();
                }
            }
        });
    }


}
