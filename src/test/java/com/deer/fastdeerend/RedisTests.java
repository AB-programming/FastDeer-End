package com.deer.fastdeerend;

import com.deer.fastdeerend.util.JWTUtil;
import com.deer.fastdeerend.util.RedisUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTests {

    @Resource
    private RedisUtil<String> redisUtil;

    @Resource
    private JWTUtil jwtUtil;

}
