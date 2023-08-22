package com.deer.fastdeerend.util;

import jakarta.annotation.Resource;
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
    @Resource
    private RedisTemplate<String, T> redisTemplate;
    public T get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setWithExpire(String key, T value, long time) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, time, TimeUnit.MINUTES);
    }
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    public void drop(String key) {
        redisTemplate.delete(key);
    }
}
