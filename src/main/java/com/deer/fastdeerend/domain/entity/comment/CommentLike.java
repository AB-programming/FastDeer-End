package com.deer.fastdeerend.domain.entity.comment;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class CommentLike implements Serializable {
    @Serial
    private static final long serialVersionUID = 5634552865452472473L;

    @TableId
    private String commentLikeId;
    private String userId;
    private String commentId;
}
