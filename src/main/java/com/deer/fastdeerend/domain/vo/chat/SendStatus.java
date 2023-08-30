package com.deer.fastdeerend.domain.vo.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendStatus {
    private String sender;
    private String content;
    private Boolean isSend;
}
