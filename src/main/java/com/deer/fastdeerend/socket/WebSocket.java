package com.deer.fastdeerend.socket;

import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.vo.chat.MessageVo;
import com.deer.fastdeerend.util.SpringUtil;
import com.deer.fastdeerend.domain.dto.Message;
import com.deer.fastdeerend.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Scope("prototype")
@ServerEndpoint("/ws/{userId}")
public class WebSocket {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private ChatService chatService = SpringUtil.getBean(ChatService.class);

    private UserMapper userMapper = SpringUtil.getBean(UserMapper.class);

    private Session session;
    private String userId;

    private static final CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    private static final ConcurrentHashMap<String, Session> sessionPool = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) {
        this.session = session;
        this.userId = userId;
        webSockets.add(this);
        sessionPool.put(userId, session);
        System.out.println("【WebSocket】有新的连接，userId=" + userId + " ,总数为：" + webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        sessionPool.remove(this.userId);
        System.out.println("【WebSocket】连接断开，总数为：" + webSockets.size());
    }

    @SneakyThrows
    @OnMessage
    public void onMessage(String message) {
        System.out.println("【WebSocket】收到客户端消息:" + message);
        Message mes = this.objectMapper.readValue(message, Message.class);
        Boolean isSend = this.sendOneMessage(mes.getUserId(), mes.getContent(), mes.getTargetId());
        if (isSend) {
            this.sendRes(mes.getUserId(), mes.getContent(), mes.getTargetId());
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("用户错误,原因:" + error.getMessage());
        error.printStackTrace();
    }

    @Transactional
    public Boolean sendOneMessage(String sender, String content, String targetId) {
        Session session = sessionPool.get(targetId);
        if (session != null && session.isOpen()) {
            try {
                String message = objectMapper.writeValueAsString(MessageVo.builder()
                        .senderId(sender)
                        .senderName(userMapper.selectById(sender).getNickName())
                        .senderAvatar(userMapper.selectById(sender).getAvatarUrl())
                        .content(content)
                        .isMe(sender.equals(targetId))
                        .build());
                session.getAsyncRemote().sendText(message);
                chatService.sendMessage(sender, targetId, content);
                System.out.println("【WebSocket】单点消息(在线)：" + message + " target=" + targetId);
                return true;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            // The message recipient is not online
            try {
                chatService.sendMessage(sender, targetId, content);
                System.out.println("【WebSocket】单点消息(离线)：" + content + " target=" + targetId);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    public void sendRes(String sender, String content, String targetId) {
        Session session = sessionPool.get(sender);
        if (session != null && session.isOpen()) {
            try {
                String message = objectMapper.writeValueAsString(MessageVo.builder()
                        .senderId(sender)
                        .senderName(userMapper.selectById(sender).getNickName())
                        .senderAvatar(userMapper.selectById(sender).getAvatarUrl())
                        .content(content)
                        .isMe(true)
                        .build());
                session.getAsyncRemote().sendText(message);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMoreMessage(String[] userIds, String message) {
        for (String userId : userIds) {
            Session session = sessionPool.get(userId);
            if (null != session && session.isOpen()) {
                try {
                    System.out.println("【WebSocket】单点消息：" + message);
                    session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
