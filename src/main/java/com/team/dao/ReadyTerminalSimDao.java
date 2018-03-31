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
   * 
   * @param map tsid，imsi
   * @return
   */
  public List<ReadyTerminalSim> list(Map<String, Object> map);

  /**
   * 根据id删除记录
   * 
   * @param
   * @return
   */
  public int delete(ReadyTerminalSim readyTerminalSim);

  /**
   * 插入记录
   * 
   * @param list
   * @return
   */
  public int insert(List<ReadyTerminalSim> list);

  /**
   * 更新单条记录
   * 
   * @param readyTerminalSim
   * @return
   */
  public int update(ReadyTerminalSim readyTerminalSim);

  public ReadyTerminalSim getBydId(@Param("id") Integer id);

}
