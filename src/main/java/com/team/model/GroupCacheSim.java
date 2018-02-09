package com.team.model;
/**
 * 创建日期：2018-2-9下午4:18:34
 * author:wuzhiheng
 */
public class GroupCacheSim {

	private static final long serialVersionUID = 7441390994629271915L;
	private long imsi;
	private int status; //0:可以使用；1：已经被使用
	public long getImsi() {
		return imsi;
	}
	public void setImsi(long imsi) {
		this.imsi = imsi;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "GroupCacheSim [imsi=" + imsi + ", status=" + status + "]";
	}
}
