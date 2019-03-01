package com.team.jobs;

import com.team.dao.QuartzCronDao;
import com.team.dao.SelectCardDao;
import com.team.model.QuartzCron;
import com.team.model.SelectCard;
import com.team.util.CommonUtil;
import com.team.util.LogManager;
import com.team.util.MD5Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

        List<SelectCard> list = selectCardDao.listNoCard(map);

        Map<String, String> paramMap = new TreeMap<>();
        StringBuilder params = new StringBuilder();
        if (CommonUtil.listNotBlank(list)) {

            Set<String> set = new HashSet<>();
            for (SelectCard selectCard : list) {
                set.add(selectCard.getTsid().toString());
            }

            String tsid = "";

            int flag = 0;
            for (String s : set) {
                if(flag < 3)
                    tsid += s+";";

                flag++;
            }

            tsid = tsid.substring(0, tsid.length() - 1);
            if (set.size() > 3){
                tsid += "等"+set.size()+"台";
            }

            paramMap.put("name", "cardselect");
            paramMap.put("tsid", tsid);
            paramMap.put("result", "1-" + resultMsg.get("1"));
            paramMap.put("date", sdf.format(list.get(0).getSelectDate()));
            paramMap.put("tel", quartzCron.getMsgPhone());

            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                params.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
            }
            params.append("sign=" + MD5Utils.encrypt(params.toString().substring(0, params.toString().length() - 1)));
//                System.out.println(quartzCron.getMsgUrl() + params.toString());
            sendMsg(quartzCron.getMsgUrl() + params.toString());
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
