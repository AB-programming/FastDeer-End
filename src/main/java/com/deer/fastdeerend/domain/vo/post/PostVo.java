package com.deer.fastdeerend.domain.vo.post;

import com.deer.fastdeerend.domain.vo.post.content.Content;
import com.deer.fastdeerend.domain.vo.post.social.SocialStats;
import com.deer.fastdeerend.domain.vo.post.userinfo.UserInfo;
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
public class PostVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -5534864305769790387L;

    private String postId;
    private String date;

    private Content content;
    private UserInfo userInfo;
    private SocialStats socialStats;
    private Long commentCount;
}