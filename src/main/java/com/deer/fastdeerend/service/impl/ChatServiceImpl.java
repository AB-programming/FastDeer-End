package com.deer.fastdeerend.service.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {

    @Resource
    private RedisUtil<String> redisUtil;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public void sendMessage(String sender, String receiver, String content) throws JsonProcessingException {
        MessageBo messageBo = MessageBo.builder()
                .sender(sender)
                .receiver(receiver)
                .content(content)
                .build();
        ChatRecordBo senderChatRecordBo = redisUtil.hasChatRecord(sender, receiver) ?
                objectMapper.readValue(redisUtil.getChatRecord(sender, receiver), ChatRecordBo.class) :
                ChatRecordBo.builder()
                        .unreadCount(0)
                        .chatRecordList(new ArrayList<>())
                        .build();
        senderChatRecordBo.getChatRecordList().add(messageBo);

        redisUtil.putMessage(sender, receiver, objectMapper.writeValueAsString(senderChatRecordBo));

        ChatRecordBo receiverChatRecordBo = redisUtil.hasChatRecord(receiver, sender) ?
                objectMapper.readValue(redisUtil.getChatRecord(receiver, sender), ChatRecordBo.class) :
                ChatRecordBo.builder()
                        .unreadCount(0)
                        .chatRecordList(new ArrayList<>())
                        .build();
        receiverChatRecordBo.getChatRecordList().add(messageBo);
        receiverChatRecordBo.setUnreadCount(receiverChatRecordBo.getUnreadCount() + 1);

        redisUtil.putMessage(receiver, sender, objectMapper.writeValueAsString(receiverChatRecordBo));
    }

    @Override
    public List<ChatVo> getChatList(String userId) {
        List<ChatVo> res = new ArrayList<>();

        Map<String, String> chatList = redisUtil.getChatList(userId);
        chatList.forEach((key, value) -> {
            try {
                ChatRecordBo chatRecordBo = objectMapper.readValue(value, ChatRecordBo.class);
                res.add(ChatVo.builder()
                        .senderId(key)
                        .senderName(userMapper.selectById(key).getNickName())
                        .senderAvatar(userMapper.selectById(key).getAvatarUrl())
                        .unreadCount(chatRecordBo.getUnreadCount())
                        .latestMessage(chatRecordBo.getChatRecordList().get(chatRecordBo.getChatRecordList().size() - 1).getContent())
                        .build());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        return res;
    }

    @Override
    public List<MessageVo> getChatRecord(String userId, String targetId) throws JsonProcessingException {
        ChatRecordBo chatRecordBo = objectMapper.readValue(redisUtil.getChatRecord(userId, targetId), ChatRecordBo.class);
        return chatRecordBo.getChatRecordList().stream().map(messageBo -> MessageVo.builder()
                .senderId(messageBo.getSender())
                .senderName(userMapper.selectById(messageBo.getSender()).getNickName())
                .senderAvatar(userMapper.selectById(messageBo.getSender()).getAvatarUrl())
                .content(messageBo.getContent())
                .isMe(userId.equals(messageBo.getSender()))
                .build()).toList();
    }

    @Override
    @Transactional
    public void readMessage(String userId, String targetId) throws JsonProcessingException {
        ChatRecordBo chatRecordBo = objectMapper.readValue(redisUtil.getChatRecord(userId, targetId), ChatRecordBo.class);
        chatRecordBo.setUnreadCount(0);
        redisUtil.putMessage(userId, targetId, objectMapper.writeValueAsString(chatRecordBo));
    }
}
