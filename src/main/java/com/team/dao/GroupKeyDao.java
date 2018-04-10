package com.team.dao;

import com.hqrh.rw.common.model.GroupKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 下午2:27 2018/4/10
 */
public interface GroupKeyDao {

    List<GroupKey> getGroupKey(@Param("groupKey") String groupKey);

    int addGroupKey(GroupKey groupKey);

}
