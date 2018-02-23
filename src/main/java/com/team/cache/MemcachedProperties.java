package com.team.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 创建日期：2018-2-6下午4:15:38 author:wuzhiheng
 */
@Component
@ConfigurationProperties(prefix = "memcache")
public class MemcachedProperties {

	private String simGroup;
	
	private String sim;
	
	private String simFlow;
	
	private String vpublic;
	
	private int initConn;
	
	private int minConn;
	
	private int maxConn;
	
	private int maintSleep;
	
	private boolean nagle;
	
	private int maxIdle;
	
	private int socketTO;
	
	private int socketConnectTO;
	
	private boolean aliveCheck;
	
	private boolean failback;
	
	private boolean failover;

	public String getSimGroup() {
		return simGroup;
	}

	public void setSimGroup(String simGroup) {
		this.simGroup = simGroup;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getSimFlow() {
		return simFlow;
	}

	public void setSimFlow(String simFlow) {
		this.simFlow = simFlow;
	}

	public String getVpublic() {
		return vpublic;
	}

	public void setVpublic(String vpublic) {
		this.vpublic = vpublic;
	}

	public int getInitConn() {
		return initConn;
	}

	public void setInitConn(int initConn) {
		this.initConn = initConn;
	}

	public int getMinConn() {
		return minConn;
	}

	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}

	public int getMaxConn() {
		return maxConn;
	}

	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}

	public int getMaintSleep() {
		return maintSleep;
	}

	public void setMaintSleep(int maintSleep) {
		this.maintSleep = maintSleep;
	}

	public boolean isNagle() {
		return nagle;
	}

	public void setNagle(boolean nagle) {
		this.nagle = nagle;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getSocketTO() {
		return socketTO;
	}

	public void setSocketTO(int socketTO) {
		this.socketTO = socketTO;
	}

	public int getSocketConnectTO() {
		return socketConnectTO;
	}

	public void setSocketConnectTO(int socketConnectTO) {
		this.socketConnectTO = socketConnectTO;
	}

	public boolean isAliveCheck() {
		return aliveCheck;
	}

	public void setAliveCheck(boolean aliveCheck) {
		this.aliveCheck = aliveCheck;
	}

	public boolean isFailback() {
		return failback;
	}

	public void setFailback(boolean failback) {
		this.failback = failback;
	}

	public boolean isFailover() {
		return failover;
	}

	public void setFailover(boolean failover) {
		this.failover = failover;
	}

	

}
