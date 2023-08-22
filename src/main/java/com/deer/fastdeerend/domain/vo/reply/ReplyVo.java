package com.deer.fastdeerend.domain.vo.reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 798523101562518030L;

    private String replyId;
    private String name;
    private String date;
    private String contentText;
    private Long likeNum;
    private String url;
    private Boolean isLike;

    private SubReply reply;
}
