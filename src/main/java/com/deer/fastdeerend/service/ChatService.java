package com.deer.fastdeerend.service;

import com.deer.fastdeerend.domain.vo.chat.ChatVo;
import com.deer.fastdeerend.domain.vo.chat.MessageVo;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ChatService {
     public void sendMessage(String sender, String receiver, String content) throws JsonProcessingException;

     public List<ChatVo> getChatList(String userId);

     public List<MessageVo> getChatRecord(String userId, String targetId) throws JsonProcessingException;

     public void readMessage(String userId, String targetId) throws JsonProcessingException;
}


