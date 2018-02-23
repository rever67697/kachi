package com.team.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.danga.MemCached.SockIOPool;
import com.team.util.MConstant;

/**
 * 创建日期：2018-2-6下午4:23:24 author:wuzhiheng
 */
@Configuration
public class MemcachedConfig {

	@Autowired
	private MemcachedProperties properties;

	@Bean(name=MConstant.MEM_SIM)
	public SockIOPool sockIOPool_simPool() {
		// 获取连接池的实例
		SockIOPool pool = SockIOPool.getInstance(MConstant.MEM_SIM);
		// 服务器列表及其权重
		// 设置服务器信息
		pool.setServers(new String[]{properties.getSim()});
		// 设置初始连接数、最小连接数、最大连接数、最大处理时间
		pool.setInitConn(properties.getInitConn());
		pool.setMinConn(properties.getMinConn());
		pool.setMaxConn(properties.getMaxConn());
		// 设置连接池守护线程的睡眠时间
		pool.setMaintSleep(properties.getMaintSleep());
		// 设置TCP参数，连接超时
		pool.setNagle(properties.isNagle());
		pool.setSocketConnectTO(properties.getSocketConnectTO());
		
		pool.setAliveCheck(properties.isAliveCheck());
		pool.setFailback(properties.isFailback());
		pool.setFailover(properties.isFailover());
		// 初始化并启动连接池
		pool.initialize();
		return pool;
	}
	@Bean(name=MConstant.MEM_SIM_GROUP)
	public SockIOPool sockIOPool_simGroupPool() {
		// 获取连接池的实例
		SockIOPool pool = SockIOPool.getInstance(MConstant.MEM_SIM_GROUP);
		// 服务器列表及其权重
		// 设置服务器信息
		pool.setServers(new String[]{properties.getSimGroup()});
		// 设置初始连接数、最小连接数、最大连接数、最大处理时间
		pool.setInitConn(properties.getInitConn());
		pool.setMinConn(properties.getMinConn());
		pool.setMaxConn(properties.getMaxConn());
		// 设置连接池守护线程的睡眠时间
		pool.setMaintSleep(properties.getMaintSleep());
		// 设置TCP参数，连接超时
		pool.setNagle(properties.isNagle());
		pool.setSocketConnectTO(properties.getSocketConnectTO());
		
		pool.setAliveCheck(properties.isAliveCheck());
		pool.setFailback(properties.isFailback());
		pool.setFailover(properties.isFailover());
		// 初始化并启动连接池
		pool.initialize();
		return pool;
	}
	@Bean(name=MConstant.MEM_SIM_FlOW)
	public SockIOPool sockIOPool_simFlowPool() {
		// 获取连接池的实例
		SockIOPool pool = SockIOPool.getInstance(MConstant.MEM_SIM_FlOW);
		// 服务器列表及其权重
		// 设置服务器信息
		pool.setServers(new String[]{properties.getSimFlow()});
		// 设置初始连接数、最小连接数、最大连接数、最大处理时间
		pool.setInitConn(properties.getInitConn());
		pool.setMinConn(properties.getMinConn());
		pool.setMaxConn(properties.getMaxConn());
		// 设置连接池守护线程的睡眠时间
		pool.setMaintSleep(properties.getMaintSleep());
		// 设置TCP参数，连接超时
		pool.setNagle(properties.isNagle());
		pool.setSocketConnectTO(properties.getSocketConnectTO());
		
		pool.setAliveCheck(properties.isAliveCheck());
		pool.setFailback(properties.isFailback());
		pool.setFailover(properties.isFailover());
		// 初始化并启动连接池
		pool.initialize();
		return pool;
	}
	@Bean(name=MConstant.MEM_PUBLIC)
	public SockIOPool sockIOPool_publicPool() {
		// 获取连接池的实例
		SockIOPool pool = SockIOPool.getInstance(MConstant.MEM_PUBLIC);
		// 服务器列表及其权重
		// 设置服务器信息
		pool.setServers(new String[]{properties.getVpublic()});
		// 设置初始连接数、最小连接数、最大连接数、最大处理时间
		pool.setInitConn(properties.getInitConn());
		pool.setMinConn(properties.getMinConn());
		pool.setMaxConn(properties.getMaxConn());
		// 设置连接池守护线程的睡眠时间
		pool.setMaintSleep(properties.getMaintSleep());
		// 设置TCP参数，连接超时
		pool.setNagle(properties.isNagle());
		pool.setSocketConnectTO(properties.getSocketConnectTO());
		
		pool.setAliveCheck(properties.isAliveCheck());
		pool.setFailback(properties.isFailback());
		pool.setFailover(properties.isFailover());
		// 初始化并启动连接池
		pool.initialize();
		return pool;
	}

}
