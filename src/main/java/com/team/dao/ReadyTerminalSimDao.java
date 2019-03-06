package com.team.dao;

import java.util.List;
import java.util.Map;

import com.team.model.ReadyTerminalSim;
import org.apache.ibatis.annotations.Param;

/**
 * @author Wzq 指定卡管理数据库操作 2017年12月30日 上午11:31:47
 *
 */
public interface ReadyTerminalSimDao {
  /**
   * 获取指定卡列表记录
   * @return
   */
  List<ReadyTerminalSim> list(Map<String, Object> map);

  /**
   * 根据id删除记录
   * @return
   */
  int delete(ReadyTerminalSim readyTerminalSim);

  /**
   * 插入记录
   * @return
   */
  int insert(List<ReadyTerminalSim> list);

  /**
   * 更新单条记录
   * @return
   */
  int update(ReadyTerminalSim readyTerminalSim);

  ReadyTerminalSim getBydId(@Param("id") Integer id);

}
