package com.deer.fastdeerend.domain.vo.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageVo {
    private String senderId;
    private String senderName;
    private String senderAvatar;
    private String content;
    private Boolean isMe;
}
