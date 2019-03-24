package com.team.service.auth;

import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 18:09 2019-03-23
 */
public interface IpPersonalConfigService {

    ResultList list();

    ReturnMsg personal(String ip);

    ReturnMsg save(MultipartFile file, String titleText, String originUrl);

}
