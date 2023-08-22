package com.deer.fastdeerend.domain.vo.post.userinfo.social;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1965498101958015647L;

    private Long likes;
    private Long fans;
    private Long following;
}
