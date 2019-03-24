package com.team.dao.auth;

import com.team.model.auth.IpPersonalConfig;

import java.util.List;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 17:57 2019-03-23
 */
public interface IpPersonalConfigDao {

    List<IpPersonalConfig> list(String ip);

    int save(List<IpPersonalConfig> list);

    int delete(String ip);

}
