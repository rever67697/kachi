package com.team.dao;

import java.util.List;
import java.util.Map;

import com.team.dto.TerminalDTO;
import com.team.model.Terminal;
import org.apache.ibatis.annotations.Param;


/**
 * 终端的相关操作	m_terminal
 * 创建日期：2017-12-18下午3:38:21
 * author:wuzhiheng
 */
public interface TerminalDao {
	
	/**
	 * 通过代理商找出终端列表
	 *@param map
	 *@return
	 *return
	 */
	List<Terminal> getTerminalList(Map<String, Object> map);
	
	/**
	 * 根据id批量删除终端
	 *@param list
	 *@return
	 *return
	 */
	int deleteTerminalByIds(List<Integer> ids);
	
	/**
	 * 插入一条终端数据
	 *@param terminal
	 *@return
	 *return
	 */
	int insertTerminal(Terminal terminal);
	
	/**
	 * 根据id更新终端信息
	 *@param terminal
	 *@return
	 *return
	 */
	int updateTerminalById(Terminal terminal);
	
	/**
	 * 批量更改终端的额所属部门
	 *@param map
	 *@return
	 *return
	 */
	int updateDepartment(Map<String, Object> map);

	/**
	 * 根据终端编号列表查询终端列表
	 * @param list
	 * @return
	 */
	List<Terminal> getSelectedList(String[] list);

	int updateStatus(@Param("tsid") Integer tsid);

	int updateWiFiPass(Map<String,Object> map);

	Terminal getByTsid(Map<String,Object> map);

	List<TerminalDTO> qtbd(Map<String,Object> map);
	
	int updateSSID(Terminal terminal);

}
