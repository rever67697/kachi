package com.team.cache;

import com.google.code.ssm.Cache;
import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.AddressProvider;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.CacheConfiguration;
import com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl;
import com.google.code.ssm.spring.SSMCache;
import com.google.code.ssm.spring.SSMCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * @author: shipeng.yu
 * @time: 2016年10月09日 下午4:36
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Configuration
public class MemCacheConfig extends CachingConfigurerSupport {

    private static final String USER_CACHE = "userCache";
    
    @Value("${memcached.servers}")
    private String addrPort;
    
    @Value("${memcached.expired}")
    private int expired;

    @Bean
    public CacheManager cacheManager() {
        MemcacheClientFactoryImpl cacheClientFactory = new MemcacheClientFactoryImpl();

        AddressProvider addressProvider = new DefaultAddressProvider(addrPort);

        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setConsistentHashing(true);

        CacheFactory cacheFactory = new CacheFactory();

        cacheFactory.setCacheName(USER_CACHE);

        cacheFactory.setCacheClientFactory(cacheClientFactory);
        cacheFactory.setAddressProvider(addressProvider);
        cacheFactory.setConfiguration(cacheConfiguration);

        Cache object = null;
        try {
            object = cacheFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 10000 表示超时时间,我们也可以配置在 application.yml 文件中
        SSMCache ssmCache = new SSMCache(object, expired, true);

        ArrayList<SSMCache> ssmCaches = new ArrayList<>();
        ssmCaches.add(0, ssmCache);

        SSMCacheManager ssmCacheManager = new SSMCacheManager();
        ssmCacheManager.setCaches(ssmCaches);

        return ssmCacheManager;
    }
}