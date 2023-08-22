package com.deer.fastdeerend.domain.entity.comment;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class Comment implements Serializable {
    @Serial
    private static final long serialVersionUID = 3312881638607677754L;

    @TableId
    private String commentId;
    private String date;
    private String contentText;
    @TableField("user_id")
    private String userId;
    @TableField("post_id")
    private String postId;
}
