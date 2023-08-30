package com.deer.fastdeerend;

import com.deer.fastdeerend.config.redis.RedisConfig;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.bo.chat.ChatRecordBo;
import com.deer.fastdeerend.domain.bo.chat.MessageBo;
import com.deer.fastdeerend.domain.vo.chat.ChatVo;
import com.deer.fastdeerend.domain.vo.chat.MessageVo;
import com.deer.fastdeerend.service.ChatService;
import com.deer.fastdeerend.util.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    @Resource
    private UserMapper userMapper;

    @Resource(name = RedisConfig.REDIS_TEMPLATE_MESSAGE)
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testAddMessage() {
        try {
            chatService.sendMessage("3", "1", "I'm a 3");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetChatRecord() {
        List<MessageVo> chatRecord = null;
        try {
            chatRecord = chatService.getChatRecord("1", "2");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        for (MessageVo message: chatRecord) {
            System.out.println(message);
        }
    }

    @Test
    public void testGetChatList() {
        List<ChatVo> chatList = chatService.getChatList("2");
        for (ChatVo chat: chatList) {
            System.out.println(chat);
        }
    }
}
