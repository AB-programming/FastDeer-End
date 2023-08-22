package com.deer.fastdeerend.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis配置文件
 *
 * @author AB-style
 * @date 2023/06/28
 */
@Configuration
public class RedisConfig {

    @Value("${dev.redis.host}")
    private String host;

    @Value("${dev.redis.password}")
    private String password;

    @Value("${dev.redis.port}")
    private int port;

    @Value("${dev.redis.database}")
    private int database;

    @Value("${dev.redis.maxTotal}")
    private int maxTotal;

    @Value("${dev.redis.maxIdle}")
    private int maxIdle;

    /**
     * Redis模版配置，配置序列化类型
     *
     * @param factory 工厂
     * @return {@link RedisTemplate}<{@link String}, {@link Object}>
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return template;
    }

    /**
     * 基于Jedis的基础配置，包括连接参数以及连接池相关参数
     *
     * @return {@link JedisConnectionFactory}
     */
    @Bean
    JedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, port);
//        config.setPassword(password);
        config.setDatabase(database);

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxTotal(maxTotal);

        jedisPoolConfig.setMaxIdle(maxIdle);

        jedisPoolConfig.setTestOnBorrow(false);
        jedisPoolConfig.setTestOnReturn(false);

        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().poolConfig(
                jedisPoolConfig).build();

        return new JedisConnectionFactory(config, jedisClientConfiguration);
    }
}
