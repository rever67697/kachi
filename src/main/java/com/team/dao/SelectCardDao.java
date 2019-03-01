package com.team.dao;

import com.team.model.SelectCard;

import java.util.List;
import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 3:51 PM 2018/10/25
 */
public interface SelectCardDao {

    List<SelectCard> listUnnormal(Map<String,Object> map);

    List<SelectCard> listSelectCardLog(Map<String,Object> map);

    List<SelectCard> listNoCard(Map<String,Object> map);


}
