package com.team.controller.auth;

import com.team.annotation.PermissionLog;
import com.team.service.auth.IpPersonalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 18:09 2019-03-23
 */
@RestController
@RequestMapping("/personal")
@PermissionLog("个性设置")
public class PersonalController {

    @Autowired
    private IpPersonalConfigService ipPersonalConfigService;

    @PostMapping("/list")
    public Object list(){
        return ipPersonalConfigService.list();
    }

    @PostMapping("/personal")
    public Object personal(String ip){
        return ipPersonalConfigService.personal(ip);
    }

    @PostMapping("/save")
    @PermissionLog
    public Object save(MultipartFile file,String titleText,String originUrl){
        return ipPersonalConfigService.save(file, titleText,originUrl);
    }

}
