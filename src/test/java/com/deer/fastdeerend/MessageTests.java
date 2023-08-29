package com.deer.fastdeerend;

import com.deer.fastdeerend.util.RedisUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageTests {

    @Resource
    private RedisUtil<String> redisUtil;

    @Test
    public void testMessage() {
        redisUtil.putMessageRecord("1", "2");
    }

}
