package com.team.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team.model.Country;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.dao.TerminalDao;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;
import com.team.model.Terminal;
import com.team.service.TerminalService;
import com.team.util.CommonUtil;

/**
 * 终端的相关操作	m_terminal
 * 创建日期：2017-12-21下午4:53:20
 * author:wuzhiheng
 */
@Transactional
@Service
public class TerminalServiceImpl extends BaseService implements TerminalService{

	@Autowired
	private TerminalDao terminalDao;
	
	@Override
	/**
	 * 通过代理商找出终端列表
	 *@param 
	 *@return
	 *return
	 */
	public ResultList getTerminalList(Integer departmentId,Integer dId, Integer tsid,
			Integer status,Integer activated, int page, int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("departmentId", departmentId);
		map.put("dId", CommonUtil.changeDepartmentId(dId));
		map.put("tsid", tsid);
		map.put("status", status);
		map.put("activated", activated);
		List<Terminal> list = terminalDao.getTerminalList(map);
		PageInfo<Terminal> pageInfo = new PageInfo<Terminal>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

    @Override
    public ResultList getSelectedList(String terminalList) {
		if(!CommonUtil.StringIsNull(terminalList)){
			String[] code = terminalList.split(",");
			List<Terminal> list = terminalDao.getSelectedList(code);
			PageInfo<Terminal> pageInfo = new PageInfo<Terminal>(list);
			return new ResultList(pageInfo.getTotal(), list);
		}
		return null;
    }

    @Override
	/**
	 * 根据id批量删除终端
	 */
	public ReturnMsg deleteTerminalByIds(String ids) {
		String[] arr = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for (String string : arr) {
			list.add(Integer.valueOf(string));
		}
		int count = terminalDao.deleteTerminalByIds(list);
		return count>0?super.successTip():super.errorTip();
	}

	@SuppressWarnings("finally")
	@Override
	/***
	 * 通过excel上传终端信息
	 */
	public ReturnMsg getTerminalList(MultipartFile file){
		ReturnMsg returnMsg = super.successTip();
		Workbook book = null;
		Sheet sheet = null;
		Row row = null;
		List<Terminal> list = new ArrayList<Terminal>();
        try {
			book = WorkbookFactory.create(file.getInputStream());
			sheet = book.getSheetAt(0);
			int total = sheet.getLastRowNum()+1;
			Terminal terminal = null;
			
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
					status = status==null?0:status;
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
					String sendWiFiPass = CommonUtil.getCellStringVal(row.getCell(18));
					Integer saleType = CommonUtil.getCellIntVal(row.getCell(19));
					Integer resetWifi = CommonUtil.getCellIntVal(row.getCell(20));
					String androidVersion = CommonUtil.getCellStringVal(row.getCell(21));
					
					terminal = new Terminal(tsid, mac, model, batch, sVersion, key, status, upLog, imei, activated,
							homeLocation, ssid, wifiPassword, licFix, usedVpn, usedSoft, departmentId, meid, sendWiFiPass,saleType, resetWifi, androidVersion);
					list.add(terminal);
				} catch (Exception e) {
					returnMsg = super.errorTip();
					returnMsg.setMsg("第"+(row.getRowNum()+1)+"行数据格式错误，请检查！");
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			returnMsg = super.errorTip();
			returnMsg.setMsg("无效的文件");
			e.printStackTrace();
		}finally{
			returnMsg.setData(list);
			return returnMsg;
		}
	}

	@Override
	public void insertBatch(List<Terminal> list) {
		for (Terminal terminal : list) {
			terminalDao.insertTerminal(terminal);
		}
	}

	@Override
	public ReturnMsg saveTerminal(Terminal terminal) {
		int count = 0;
		if(terminal.getId()!=null){
			count = terminalDao.updateTerminalById(terminal);
		}else{
			count = terminalDao.insertTerminal(terminal);
		}
		return count>0?super.successTip():super.errorTip();
	}

	@Override
	/**
	 * 批量更改终端的额所属部门
	 */
	public ReturnMsg updateDepartment(String ids, Integer departmentId) {
		if(!CommonUtil.StringIsNull(ids)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ids", ids.split(","));
			map.put("departmentId", departmentId);
			terminalDao.updateDepartment(map);
		}
		return super.successTip();
	}

	
}
