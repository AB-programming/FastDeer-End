package com.deer.fastdeerend.domain.bo.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRecordBo {
    private List<MessageBo> chatRecordList;
    private int unreadCount;
}
