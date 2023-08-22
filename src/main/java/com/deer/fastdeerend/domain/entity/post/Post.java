package com.deer.fastdeerend.domain.entity.post;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.deer.fastdeerend.domain.entity.user.User;
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
public class Post implements Serializable {

    @Serial
    private static final long serialVersionUID = -4770043180741889916L;

    @TableId
    private String postId;
    private String date;
    private String urls;
    private String text;
    private String title;
    private Long browserCount;
    @TableField("user_id")
    private String userId;

    @TableField(exist = false)
    private User user;
}
