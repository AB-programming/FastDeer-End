package com.deer.fastdeerend.controller;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.dto.Message;
import com.deer.fastdeerend.domain.vo.chat.ChatVo;
import com.deer.fastdeerend.domain.vo.chat.MessageVo;
import com.deer.fastdeerend.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Resource
    private ChatService chatService;

    @GetMapping("/getChatList")
    public HttpResponse<List<ChatVo>> getChatList(String userId) {
        HttpResponse.HttpResponseBuilder<List<ChatVo>> builder = HttpResponse.builder();
        if (!StringUtils.hasText(userId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(chatService.getChatList(userId))
                .build();
    }

    @GetMapping("/getChatRecord")
    public HttpResponse<List<MessageVo>> getChatRecord(String userId, String targetId) {
        HttpResponse.HttpResponseBuilder<List<MessageVo>> builder = HttpResponse.builder();
        if (!StringUtils.hasText(userId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        if (!StringUtils.hasText(targetId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少targetId参数")
                    .build();
        }
        try {
            return builder
                    .code(HttpResponseStatusCodeSet.OK.getValue())
                    .msg("查询成功")
                    .data(chatService.getChatRecord(userId, targetId))
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return builder
                    .code(HttpResponseStatusCodeSet.InternalServerError.getValue())
                    .msg("服务器内部错误")
                    .build();
        }
    }

    @PostMapping("/readMessage")
    public HttpResponse<Boolean> readMessage(@RequestBody  Message message) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(message.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        if (!StringUtils.hasText(message.getTargetId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少targetId参数")
                    .build();
        }
        try {
            chatService.readMessage(message.getUserId(), message.getTargetId());
            return builder
                    .code(HttpResponseStatusCodeSet.OK.getValue())
                    .msg("读取成功")
                    .data(true)
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return builder
                    .code(HttpResponseStatusCodeSet.InternalServerError.getValue())
                    .msg("服务器内部错误")
                    .data(false)
                    .build();
        }
    }

    @DeleteMapping("/deleteChat")
    public HttpResponse<Boolean> deleteChat(@RequestBody Message message) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(message.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        if (!StringUtils.hasText(message.getTargetId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少targetId参数")
                    .build();
        }
        chatService.deleteChat(message.getUserId(), message.getTargetId());
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("删除成功")
                .build();
    }
}
