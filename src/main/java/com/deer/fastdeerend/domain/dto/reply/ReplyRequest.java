package com.deer.fastdeerend.domain.dto.reply;

import com.deer.fastdeerend.domain.entity.reply.ReplyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRequest {
    private String replyId;
    private String date;
    private String contentText;
    private String target;
    private String userId;
    private ReplyType replyType;
}
