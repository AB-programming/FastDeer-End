package com.deer.fastdeerend.domain.entity.reply;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyLike implements Serializable {
    @Serial
    private static final long serialVersionUID = -2161270873818197186L;

    @TableId
    private String replyLikeId;
    private String userId;
    private String replyId;
}
