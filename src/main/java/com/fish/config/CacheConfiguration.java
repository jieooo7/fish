package com.fish.config;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

/**
 * Created by thy on 16-11-26.
 */
@Configuration
@EnableCaching
public class CacheConfiguration {

    private net.sf.ehcache.CacheManager cacheManager;

    @PreDestroy
    public void destroy() {
        cacheManager.shutdown();
    }

    @Bean
    public org.springframework.cache.CacheManager cacheManager() {
        cacheManager = net.sf.ehcache.CacheManager.create();
        EhCacheCacheManager ehCacheManager = new EhCacheCacheManager();
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;

    }

}