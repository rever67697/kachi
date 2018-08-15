package com.team.jobs;

import com.team.dao.QuartzCronDao;
import com.team.dao.SimCardDao;
import com.team.dto.CardDTO;
import com.team.model.ProblemCard;
import com.team.model.QuartzCron;
import com.team.model.SimCard;
import com.team.service.SimCardService;
import com.team.util.Cache;
import com.team.util.CacheFactory;
import com.team.util.CommonUtil;
import com.team.util.MConstant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午5:21 2018/7/2
 */
@Component
public class ScheduleTask {

    private Logger logger = Logger.getLogger(ScheduleTask.class);

    @Autowired
    private SimCardDao simCardDao;
    @Autowired
    private QuartzCronDao quartzCronDao;
    @Autowired
    private SimCardService simCardService;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    // 卡缓存
    private static final Cache simCache = CacheFactory.getCache(MConstant.MEM_SIM);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private void run(){

        QuartzCron quartzCron = quartzCronDao.get();
        Integer minute = quartzCron.getMinute();

        Map<String,Object> map = new HashMap<>();
        try {
            Date date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            map.put("startDate",new Date(date.getTime()-2*minute*60*1000));
            map.put("endDate",new Date(date.getTime()-minute*60*1000));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<CardDTO> simCardList = simCardDao.getProblemCard(map);

        int index = 0;

        if(CommonUtil.listNotBlank(simCardList)){
            for (CardDTO simCard : simCardList) {

                logger.error("处理问题卡:"+simCard.getImsi()+" ，状态为："+simCard.getStatus());

                boolean ok = true;
                //有可能在扫描的时候存在同一个终端和同一个imsi有两条记录以上的情况
                if(index > 0 && (simCardList.get(index-1).getImsi().equals(simCard.getImsi()) && simCardList.get(index-1).getTsid().equals(simCard.getTsid()))){
                    ok=false;
                }

                if (ok){
                    simCardDao.saveProblemCard(new ProblemCard(simCard.getTsid(),simCard.getImsi(),
                            simCard.getSelectDate(),simCard.getReleased(),simCard.getAuthTime()));

                    if (quartzCron.getIsHandle() == 0){
                        //把卡状态改为1-停用
                        if(simCard.getStatus()!=1 && simCard.getStatus()!=4){
                            simCard.setStatus(1);
                            //更新status
                            map.put("imsi",simCard.getImsi());
                            map.put("status",1);
                            simCardDao.updateStatusByImsi(map);

                            //刷新卡组缓存
                            simCardService.updateGroupSim2Cache(simCard,1);
                            //刷新卡缓存
                            simCache.set(MConstant.CACHE_SIM_KEY_PREF + simCard.getImsi(),
                                    CommonUtil.convertBean(simCard, com.hqrh.rw.common.model.SimCard.class));
                        }
                    }
                }

                index++;
            }
        }

        int count = simCardDao.deleteProblemCards();

        logger.info("删除已经正常的卡："+count);

        logger.info("现在时间:"+sdf.format(new Date())+" 执行处理问题卡任务!");
    }


}
