package com.deer.fastdeerend.service.impl;

import com.deer.fastdeerend.domain.bo.MessageBo;
import com.deer.fastdeerend.service.ChatService;
import com.deer.fastdeerend.util.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ChatServiceImpl implements ChatService {

    @Resource
    private RedisUtil<String> redisUtil;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public void sendMessage(String sender, String receiver, String content) throws JsonProcessingException {
        MessageBo messageBo = MessageBo.builder()
                .sender(sender)
                .receiver(receiver)
                .content(content)
                .build();
        ArrayList<MessageBo> senderChatRecord = redisUtil.hasChatRecord(sender, receiver) ?
                objectMapper.readValue(redisUtil.getChatRecord(sender, receiver), new TypeReference<>() {}) :
                new ArrayList<>();
        senderChatRecord.add(messageBo);

        redisUtil.putMessage(sender, receiver, objectMapper.writeValueAsString(senderChatRecord));

        ArrayList<MessageBo> receiverChatRecord = redisUtil.hasChatRecord(receiver, sender) ?
                objectMapper.readValue(redisUtil.getChatRecord(receiver, sender), new TypeReference<>() {}) :
                new ArrayList<>();
        receiverChatRecord.add(messageBo);

        redisUtil.putMessage(receiver, sender, objectMapper.writeValueAsString(receiverChatRecord));
    }
}
