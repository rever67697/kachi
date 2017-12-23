package com.team.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.TerminalDao;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import com.team.model.Terminal;
import com.team.service.TerminalService;
import com.team.util.IConstant;
import com.team.util.CommonUtil;

/**
 * 终端的相关操作	m_terminal
 * 创建日期：2017-12-21下午4:53:20
 * author:wuzhiheng
 */
@Service
public class TerminalServiceImpl implements TerminalService{

	@Autowired
	private TerminalDao terminalDao;
	
	@Override
	/**
	 * 通过代理商找出终端列表
	 *@param 
	 *@return
	 *return
	 */
	public ResultList getTerminalByDeparment(String departmentId, String tsid,
			String status,String activated, int page, int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("departmentId", CommonUtil.putInteger(departmentId));
		map.put("tsid", CommonUtil.putInteger(tsid));
		map.put("status", CommonUtil.putInteger(status));
		map.put("activated", CommonUtil.putInteger(activated));
		List<Terminal> list = terminalDao.getTerminalByDeparment(map);
		PageInfo<Terminal> pageInfo = new PageInfo<>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	@Override
	/**
	 * 根据id批量删除终端
	 */
	public int deleteTerminalByIds(String ids) {
		if(ids!=null&&!"".equals(ids)){
			String[] arr = ids.split(",");
			List<Integer> list = new ArrayList<Integer>(); 
			for (String string : arr) {
				list.add(Integer.valueOf(string));
			}
			return terminalDao.deleteTerminalByIds(list);
		}
		return 0;
	}

	@Override
	public int insertTerminal(Terminal terminal) {
		terminal.setId(CommonUtil.getNewId());
		return terminalDao.insertTerminal(terminal);
	}

	@Override
	public int updateTerminalById(Terminal terminal) {
		// TODO Auto-generated method stub
		return terminalDao.updateTerminalById(terminal);
	}
	
	@SuppressWarnings("finally")
	@Override
	/***
	 * 通过excel上传终端信息
	 */
	public ReturnMsg getTerminalList(MultipartFile file){
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		Workbook book = null;
		Sheet sheet = null;
		Row row = null;
		List<Terminal> list = new ArrayList<Terminal>();
        try {
			book = WorkbookFactory.create(file.getInputStream());
			sheet = book.getSheetAt(0);
			int total = sheet.getLastRowNum()+1;
			for (int i = 2; i < total; i++) {
				row = sheet.getRow(i);
				if(row == null || row.getCell(0) == null){
					continue;
				}
				try {
					Integer tsid = CommonUtil.getCellIntVal(row.getCell(0));
					String mac = CommonUtil.getCellStringVal(row.getCell(1));
					String model = CommonUtil.getCellStringVal(row.getCell(2));
					String batch = CommonUtil.getCellStringVal(row.getCell(3));
					String sVersion = CommonUtil.getCellStringVal(row.getCell(4));
					String key = CommonUtil.getCellStringVal(row.getCell(5));
					Integer status = CommonUtil.getCellIntVal(row.getCell(6));
					Integer upLog = CommonUtil.getCellIntVal(row.getCell(7));
					String imei = CommonUtil.getCellStringVal(row.getCell(8));
					Integer activated = CommonUtil.getCellIntVal(row.getCell(9));
					Integer homeLocation = CommonUtil.getCellIntVal(row.getCell(10));
					String ssid = CommonUtil.getCellStringVal(row.getCell(11));
					String wifiPassword = CommonUtil.getCellStringVal(row.getCell(12));
					String licFix = CommonUtil.getCellStringVal(row.getCell(13));
					Integer usedVpn = CommonUtil.getCellIntVal(row.getCell(14));
					Integer usedSoft = CommonUtil.getCellIntVal(row.getCell(15));
					Integer departmentId = CommonUtil.getCellIntVal(row.getCell(16));
					String meid = CommonUtil.getCellStringVal(row.getCell(17));
					Integer saleType = CommonUtil.getCellIntVal(row.getCell(18));
					Integer resetWifi = CommonUtil.getCellIntVal(row.getCell(19));
					String androidVersion = CommonUtil.getCellStringVal(row.getCell(20));
					
					Terminal terminal = new Terminal(CommonUtil.getNewId(), tsid, mac, model, batch, sVersion, key, status, upLog, imei, activated, 
							homeLocation, ssid, wifiPassword, licFix, usedVpn, usedSoft, departmentId, meid, saleType, resetWifi, androidVersion);
					list.add(terminal);
				} catch (Exception e) {
					returnMsg.setCode(IConstant.CODE_ERROR);
					returnMsg.setMsg("第"+(row.getRowNum()+1)+"行数据格式错误，请检查！");
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			returnMsg.setCode(IConstant.CODE_ERROR);
			returnMsg.setMsg("无效的文件");
			e.printStackTrace();
		}finally{
			returnMsg.setData(list);
			return returnMsg;
		}
	}

	
}
