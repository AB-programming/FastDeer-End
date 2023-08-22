package com.deer.fastdeerend.domain.entity.reply;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class Reply implements Serializable {
    @Serial
    private static final long serialVersionUID = -7053346221356002496L;

    @TableId
    private String replyId;
    private String date;
    private String contentText;
    private String commentId;
    private String targetId;
    @TableField("user_id")
    private String userId;
    private ReplyType replyType;
}
