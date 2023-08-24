package com.deer.fastdeerend.socket;

import com.deer.fastdeerend.domain.dto.Message;
import com.deer.fastdeerend.domain.vo.ReceivedMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/ws/{userId}")
public class WebSocket {
    private final ObjectMapper objectMapper = new ObjectMapper();

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
        this.sendOneMessage(this.userId, "发送成功", this.userId);
        this.sendOneMessage(mes.getUserId(), mes.getContent(), mes.getTargetId());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("用户错误,原因:" + error.getMessage());
        error.printStackTrace();
    }

    public void sendAllMessage(String message) {
        System.out.println("【WebSocket】广播消息:" + message);
        for (WebSocket webSocket : webSockets) {
            try {
                if (webSocket.session.isOpen()) {
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendOneMessage(String sender, String content, String targetId) {
        Session session = sessionPool.get(targetId);
        if (session != null && session.isOpen()) {
            try {
                System.out.println("【WebSocket】单点消息：" + content);
                String message = objectMapper.writeValueAsString(ReceivedMessage.builder()
                        .sender(sender)
                        .content(content)
                        .build());
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // The message recipient is not online

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
