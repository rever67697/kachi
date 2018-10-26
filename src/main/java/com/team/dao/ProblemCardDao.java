package com.team.dao;

import com.team.model.ProblemCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 11:00 PM 2018/10/25
 */
public interface ProblemCardDao {

    /**
     * 找出特定时间的问题卡
     * @return
     */
    List<ProblemCard> getProblemCard(Map<String,Object> map);

    List<ProblemCard> list(Map<String,Object> map);

    int save(ProblemCard problemCard);

    int delete(@Param("imsi") Long imsi);

    int deleteBySelf();

    int updateCount();
}
