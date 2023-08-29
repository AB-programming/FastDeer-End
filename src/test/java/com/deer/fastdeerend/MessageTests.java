package com.deer.fastdeerend;

import com.deer.fastdeerend.config.redis.RedisConfig;
import com.deer.fastdeerend.domain.bo.MessageBo;
import com.deer.fastdeerend.service.ChatService;
import com.deer.fastdeerend.util.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class MessageTests {

    @Resource
    private RedisUtil<String> redisUtil;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private ChatService chatService;

    @Resource(name = RedisConfig.REDIS_TEMPLATE_MESSAGE)
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testAddMessage() {

        MessageBo messageBo = MessageBo.builder()
                .sender("2")
                .content("welcome")
                .receiver("1")
                .build();

        try {
            chatService.sendMessage("1", "2", "hello");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetMessage() {
        String chatList = redisUtil.getChatRecord("1", "2");
        try {
            List<MessageBo> o = objectMapper.readValue(chatList, new TypeReference<>() {
            });

            log.info(o.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
