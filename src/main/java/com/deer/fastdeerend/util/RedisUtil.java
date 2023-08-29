package com.deer.fastdeerend.util;

import com.deer.fastdeerend.config.redis.RedisConfig;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 自定义封装Redis工具类
 *
 * @author AB-style
 * @date 2023/06/28
 */
@Component
public class RedisUtil<T> {
    @Resource(name = RedisConfig.REDIS_TEMPLATE_USER)
    private RedisTemplate<String, T> userRedisTemplate;

    @Resource(name = RedisConfig.REDIS_TEMPLATE_MESSAGE)
    private RedisTemplate<String, T> messageRedisTemplate;

    public T getToken(String key) {
        return userRedisTemplate.opsForValue().get(key);
    }

    public void setToken(String key, T value) {
        userRedisTemplate.opsForValue().set(key, value);
    }

    public void setTokenWithExpire(String key, T value, long time) {
        userRedisTemplate.opsForValue().set(key, value);
        userRedisTemplate.expire(key, time, TimeUnit.MINUTES);
    }
    public Boolean hasToken(String key) {
        return userRedisTemplate.hasKey(key);
    }

    public Long getTokenExpire(String key) {
        return userRedisTemplate.getExpire(key);
    }

    public void dropToken(String key) {
        userRedisTemplate.delete(key);
    }

    public void putMessageRecord(String key, T record) {
        messageRedisTemplate.opsForList().leftPush(key, record);
    }
}
