package com.team.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.danga.MemCached.MemCachedClient;


public class CacheFactory {
	
	private static final Lock lock =new ReentrantLock();

	private static final  Map<String,Cache> cacheMap = new HashMap<String,Cache>();
	
	public static Cache getCache(String key){
		Cache cache =null;
		MemCachedClient cachedClient=null;
		try{
			lock.lock();
			cache =cacheMap.get(key);
			if(null ==cache){
				cachedClient =new MemCachedClient(key);
				cache =new Cache(cachedClient);
				cacheMap.put(key, cache);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return cache;
	}
}
