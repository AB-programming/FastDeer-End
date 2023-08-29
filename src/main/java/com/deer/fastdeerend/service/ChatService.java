package com.deer.fastdeerend.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ChatService {
     public void sendMessage(String sender, String receiver, String content) throws JsonProcessingException;
}


