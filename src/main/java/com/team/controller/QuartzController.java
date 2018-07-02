package com.team.controller;

import com.team.service.QuartzService;
import com.team.service.impl.BaseService;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午10:30 2018/7/2
 */
@RestController
@RequestMapping("/quartz")
public class QuartzController extends BaseService{

    @Autowired
    private QuartzService quartzService;

    @RequestMapping("/reset")
    public ReturnMsg set(Integer minute) throws Exception{

        if(minute == null || minute<1 || minute>59){
            return errorTip("参数错误");
        }

        return  quartzService.scheduleUpdateCronTrigger(minute);
    }

    @PostMapping("/getNow")
    public Integer getNow(){
        return quartzService.getNow();
    }


}
