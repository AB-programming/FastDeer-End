package com.deer.fastdeerend.domain.vo.comment;

import com.deer.fastdeerend.domain.vo.reply.ReplyVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentVo {
    private String commentId;
    private String name;
    private String date;
    private String contentText;
    private String url;
    private Long likeNum;
    private Boolean isLike;
    private List<ReplyVo> replyList;
}
