package com.team.vo;

import java.io.Serializable;

/**
 * 用作显示概览信息的装载bean
 * 创建日期：2017-12-20下午5:27:27
 * author:wuzhiheng
 */
public class OutlineInfo implements Serializable{

	private int simPoolCount;//卡池总数
	
	private int onlinePoolCount;//在线卡池数
	
	private int offlinePoolCount;//离线卡池数
	
	private int cardSlotCount;//卡槽数
	
	private int simCardCount;//已插卡总数
	
	private int onCardCount;//正常卡数0
	
	private int stopCardCount;//停用卡数1
	
	private int appointCardCount;//指定卡数2
	
	private int readCardCount;//待激活卡数3
	
	private int offCardCount;//作废卡数4

	private int sleepCardCount;//休眠卡数5

	public int getReadCardCount() {
		return readCardCount;
	}

	public void setReadCardCount(int readCardCount) {
		this.readCardCount = readCardCount;
	}

	public int getSimPoolCount() {
		return simPoolCount;
	}

	public void setSimPoolCount(int simPoolCount) {
		this.simPoolCount = simPoolCount;
	}

	public int getOnlinePoolCount() {
		return onlinePoolCount;
	}

	public void setOnlinePoolCount(int onlinePoolCount) {
		this.onlinePoolCount = onlinePoolCount;
	}

	public int getOfflinePoolCount() {
		return offlinePoolCount;
	}

	public void setOfflinePoolCount(int offlinePoolCount) {
		this.offlinePoolCount = offlinePoolCount;
	}

	public int getCardSlotCount() {
		return cardSlotCount;
	}

	public void setCardSlotCount(int cardSlotCount) {
		this.cardSlotCount = cardSlotCount;
	}

	public int getSimCardCount() {
		return simCardCount;
	}

	public void setSimCardCount(int simCardCount) {
		this.simCardCount = simCardCount;
	}

	public int getOnCardCount() {
		return onCardCount;
	}

	public void setOnCardCount(int onCardCount) {
		this.onCardCount = onCardCount;
	}

	public int getOffCardCount() {
		return offCardCount;
	}

	public void setOffCardCount(int offCardCount) {
		this.offCardCount = offCardCount;
	}

	public int getAppointCardCount() {
		return appointCardCount;
	}

	public void setAppointCardCount(int appointCardCount) {
		this.appointCardCount = appointCardCount;
	}

	public int getStopCardCount() {
		return stopCardCount;
	}

	public void setStopCardCount(int stopCardCount) {
		this.stopCardCount = stopCardCount;
	}

	public int getSleepCardCount() {
		return sleepCardCount;
	}

	public void setSleepCardCount(int sleepCardCount) {
		this.sleepCardCount = sleepCardCount;
	}
}
