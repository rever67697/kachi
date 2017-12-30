package com.team.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.team.dao.ChannelCardDao;
import com.team.model.ChannelCard;
import com.team.service.ChannelCardService;
import com.team.util.CommonUtil;
import com.team.util.IConstant;
import com.team.vo.ResultList;
import com.team.vo.ReturnMsg;

@Transactional
@Service
public class ChannelCardServiceImpl implements ChannelCardService {

	@Autowired
	private ChannelCardDao channelCardDao;

	@Override
	public ResultList getChannelCard(String number,String countryCode, String operatorCode,
			String status, int page, int rows) {
		PageHelper.startPage(page, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("number", number);
		map.put("countryCode", CommonUtil.putInteger(countryCode));
		map.put("operatorCode", CommonUtil.putInteger(operatorCode));
		map.put("status", CommonUtil.putInteger(status));
		List<ChannelCard> list = channelCardDao.getChannelCard(map);
		PageInfo<ChannelCard> pageInfo = new PageInfo<ChannelCard>(list);
		return new ResultList(pageInfo.getTotal(), list);
	}

	@Override
	public ReturnMsg deleteChannelCards(String ids) {
		String[] arr = ids.split(",");
		List<Integer> list = new ArrayList<Integer>(); 
		for (String string : arr) {
			list.add(Integer.valueOf(string));
		}
		int count = channelCardDao.deleteChannelCards(list);
		return count>0?IConstant.MSG_OPERATE_SUCCESS:IConstant.MSG_OPERATE_ERROR;
	}

	@Override
	public void insertBatch(List<ChannelCard> list){
		for (ChannelCard channelCard : list) {
			channelCardDao.insertChannelCard(channelCard);
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	public ReturnMsg getChannelCardList(MultipartFile file) {
		ReturnMsg returnMsg = IConstant.MSG_OPERATE_SUCCESS;
		Workbook book = null;
		Sheet sheet = null;
		Row row = null;
		List<ChannelCard> list = new ArrayList<ChannelCard>();
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
					Long imsi = CommonUtil.getCellLongVal(row.getCell(0));
					String number = CommonUtil.getCellStringVal(row.getCell(1));
					String iccid = CommonUtil.getCellStringVal(row.getCell(2));
					Integer operatorCode = CommonUtil.getCellIntVal(row.getCell(3));
					Integer countryCode = CommonUtil.getCellIntVal(row.getCell(4));
					String mcNumber = CommonUtil.getCellStringVal(row.getCell(5));
					Date rechargeTime = CommonUtil.getCellDateVal(row.getCell(6));
					Double balance = CommonUtil.getCellDoubleVal(row.getCell(7));
					String detail = CommonUtil.getCellStringVal(row.getCell(8));
					
					ChannelCard channelCard = new ChannelCard(imsi, number, iccid, operatorCode, countryCode, mcNumber, rechargeTime, balance, new Integer(0), detail);
					list.add(channelCard);
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

	@Override
	public ReturnMsg saveChannelCard(ChannelCard channelCard) {
		int count = 0;
		if(channelCard.getId()!=null){
			count = channelCardDao.updateChannelCard(channelCard);
		}else{
			count = channelCardDao.insertChannelCard(channelCard);
		}
		if(count > 0){
			return IConstant.MSG_OPERATE_SUCCESS;
		}
		return IConstant.MSG_OPERATE_ERROR;
	}

}
