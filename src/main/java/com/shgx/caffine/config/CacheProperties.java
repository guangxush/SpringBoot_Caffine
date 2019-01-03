package com.shgx.caffine.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "caffeine.config")
@Data
public class CacheProperties {
    private int initialCapacity;

    private long maximumSize;

    private long maximumWeight;

    private long expireAfterWriteNanos;

    private long expireAfterAccessNanos;

    private long refreshNanos;
}
