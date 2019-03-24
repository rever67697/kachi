package com.team.service.auth.impl;

import com.team.dao.auth.IpPersonalConfigDao;
import com.team.model.auth.IpPersonalConfig;
import com.team.service.auth.IpPersonalConfigService;
import com.team.service.impl.BaseService;
import com.team.util.CommonUtil;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 18:10 2019-03-23
 */
@Service
public class IpPersonalConfigServiceImpl extends BaseService implements IpPersonalConfigService {

    @Autowired
    private IpPersonalConfigDao ipPersonalConfigDao;

    @Override
    public ResultList list() {

        List<IpPersonalConfig> list = ipPersonalConfigDao.list(CommonUtil.getIp());
        for (IpPersonalConfig ipPersonalConfig : list) {
            String[] tmp = ipPersonalConfig.getContent().split("\\|");
            ipPersonalConfig.setContent(tmp[0]);
            ipPersonalConfig.setOperator(tmp[1]);
        }
        return new ResultList(list.size(),list);
    }

    @Override
    public ReturnMsg personal(String ip) {

        List<IpPersonalConfig> list = ipPersonalConfigDao.list(ip);
        for (IpPersonalConfig ipPersonalConfig : list) {
            String[] tmp = ipPersonalConfig.getContent().split("\\|");
            ipPersonalConfig.setContent(tmp[0]);
            ipPersonalConfig.setOperator(tmp[1]);
        }
        return successTip(list);
    }

    @Override
    @Transactional
    public ReturnMsg save(MultipartFile file, String titleText, String originUrl) {

        String ip = CommonUtil.getIp();
        //上传图片到本地
        String bgImgPath;
        String originalFilename = file.getOriginalFilename();
        if(CommonUtil.StringIsNull(originalFilename)){
            bgImgPath = originUrl;
        }else {
            bgImgPath = "background/"+ip.replaceAll("\\.","-")+
                    "-"+System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));
            System.out.println(bgImgPath);

            try {
                FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(bgImgPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String operator = CommonUtil.getUser().getName();

        List<IpPersonalConfig> list = new ArrayList<>();
        list.add(new IpPersonalConfig(ip,bgImgPath,"0",operator));
        list.add(new IpPersonalConfig(ip,titleText,"1",operator));

        ipPersonalConfigDao.delete(ip);
        ipPersonalConfigDao.save(list);


        return successTip();
    }

}
