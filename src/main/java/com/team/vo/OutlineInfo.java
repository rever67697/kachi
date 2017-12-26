package com.team.vo;
/**
 * 用作显示概览信息的装载bean
 * 创建日期：2017-12-20下午5:27:27
 * author:wuzhiheng
 */
public class OutlineInfo {

	private Integer simPoolCount;//卡池总数
	
	private Integer onlinePoolCount;//在线卡池数
	
	private Integer offlinePoolCount;//离线卡池数
	
	private Integer cardSlotCount;//卡槽数
	
	private Integer simCardCount;//已插卡总数
	
	private Integer onCardCount;//正常卡数0
	
	private Integer stopCardCount;//停用卡数1
	
	private Integer appointCardCount;//指定卡数2
	
	private Integer readCardCount;//待激活卡数3
	
	private Integer offCardCount;//作废卡数4

	public Integer getReadCardCount() {
		return readCardCount;
	}

	public void setReadCardCount(Integer readCardCount) {
		this.readCardCount = readCardCount;
	}

	public Integer getSimPoolCount() {
		return simPoolCount;
	}

	public void setSimPoolCount(Integer simPoolCount) {
		this.simPoolCount = simPoolCount;
	}

	public Integer getOnlinePoolCount() {
		return onlinePoolCount;
	}

	public void setOnlinePoolCount(Integer onlinePoolCount) {
		this.onlinePoolCount = onlinePoolCount;
	}

	public Integer getOfflinePoolCount() {
		return offlinePoolCount;
	}

	public void setOfflinePoolCount(Integer offlinePoolCount) {
		this.offlinePoolCount = offlinePoolCount;
	}

	public Integer getCardSlotCount() {
		return cardSlotCount;
	}

	public void setCardSlotCount(Integer cardSlotCount) {
		this.cardSlotCount = cardSlotCount;
	}

	public Integer getSimCardCount() {
		return simCardCount;
	}

	public void setSimCardCount(Integer simCardCount) {
		this.simCardCount = simCardCount;
	}

	public Integer getOnCardCount() {
		return onCardCount;
	}

	public void setOnCardCount(Integer onCardCount) {
		this.onCardCount = onCardCount;
	}

	public Integer getOffCardCount() {
		return offCardCount;
	}

	public void setOffCardCount(Integer offCardCount) {
		this.offCardCount = offCardCount;
	}

	public Integer getAppointCardCount() {
		return appointCardCount;
	}

	public void setAppointCardCount(Integer appointCardCount) {
		this.appointCardCount = appointCardCount;
	}

	public Integer getStopCardCount() {
		return stopCardCount;
	}

	public void setStopCardCount(Integer stopCardCount) {
		this.stopCardCount = stopCardCount;
	}
	
	
	
}
