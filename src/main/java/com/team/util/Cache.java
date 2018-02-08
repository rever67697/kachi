package com.team.util;

import java.util.Date;



import com.danga.MemCached.MemCachedClient;
import com.schooner.MemCached.MemcachedItem;


public class Cache {
	
	public MemCachedClient cachedClient;
	
	public Cache(){}
	
	public Cache(MemCachedClient cachedClient){
		this.cachedClient =cachedClient;
	}
	
	/**
	 * 添加到缓存
	 * @param key 
	 * @param obj
	 * @return
	 */
	public boolean add(String key,Object obj){
		return cachedClient.add(key, obj);
	}
	
	/**
	 * 添加到缓存
	 * @param key
	 * @param obj
	 * @param date 过时时间
	 * @return
	 */
	public boolean add(String key,Object obj,Date date){
		return cachedClient.add(key, obj, date);
	}
	
	/**
	 * 设置缓存 如果不存在则增加到缓存
	 * @param key
	 * @param obj
	 * @return
	 */
	public boolean set(String key,Object obj){
		return cachedClient.set(key, obj);
	}
	
	/**
	 * 设置缓存 如果不存在则增加到缓存
	 * @param key
	 * @param obj
	 * @param date
	 * @return
	 */
	public boolean set(String key,Object obj,Date date){
		return cachedClient.set(key, obj, date);
	}
	
	/**
	 * 获取缓存对象
	 * @param key
	 * @return
	 */
	public Object get(String key){
		return cachedClient.get(key);
	}
	
	/**
	 *
	 * @param key
	 * @return
	 */
	public MemcachedItem gets(String key){
		return cachedClient.gets(key);
	
	}
	/**
	 * 
	 * @param key
	 * @param obj
	 * @param casUniqur
	 * @return
	 */
	public boolean cas(String key,Object obj,long casUniqur){
		return cachedClient.cas(key, obj, casUniqur);
	}
	/** 批量获取关键字的对象
	 * @param keys
	 * @return
	 */
	public Object[] gets(String[] keys){
		 return cachedClient.getMultiArray(keys);
	}
	
	public boolean cas(String key,Object obj,long casUniqur,Date date){
		return cachedClient.cas(key, obj,date,casUniqur);
	}
	
	/**
	 * 从缓存中移除
	 * @param key
	 * @return
	 */
	public boolean remove(String key){
		return cachedClient.delete(key);
	}
	
	/**
	 * 清空当前缓存的内容
	 * @return
	 */
	public boolean clear(){
		return cachedClient.flushAll();
	}
}
