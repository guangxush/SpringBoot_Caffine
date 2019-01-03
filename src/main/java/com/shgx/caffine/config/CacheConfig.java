package com.shgx.caffine.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableConfigurationProperties(CacheProperties.class)
@EnableCaching
@Configuration
public class CacheConfig {

    @Autowired
    private CacheProperties cacheProperties;

    public static final String DEVICE_CACHE = "deviceCache";

    @Bean(DEVICE_CACHE)
    public Caffeine<Object, Object> caffeineBuilder() {
        int initSize = cacheProperties.getInitialCapacity();
        long maximumSize = cacheProperties.getMaximumSize();
        long duration = cacheProperties.getExpireAfterAccessNanos();

        return Caffeine.newBuilder()
                .initialCapacity(initSize)
                .maximumSize(maximumSize)
                .expireAfterAccess(duration, TimeUnit.MINUTES);
    }
}
