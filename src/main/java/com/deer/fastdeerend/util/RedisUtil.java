package com.deer.fastdeerend.util;

import com.deer.fastdeerend.config.redis.RedisConfig;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
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

    public Boolean hasChatRecord(String key, String hashKey) {
        return messageRedisTemplate.opsForHash().hasKey(key, hashKey);
    }

    public void putMessage(String key, String hashKey, String chatRecord) {
        messageRedisTemplate.opsForHash().put(key, hashKey, chatRecord);
    }

    public String getChatRecord(String key, String hashKey) {
        HashOperations<String, String, String> hashOperations = messageRedisTemplate.opsForHash();
        return hashOperations.get(key, hashKey);
    }

    public Map<String, String> getChatList(String key) {
        HashOperations<String, String, String> hashOperations = messageRedisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    public void dropChat(String key, String hashKey) {
        messageRedisTemplate.opsForHash().delete(key, hashKey);
    }
}
