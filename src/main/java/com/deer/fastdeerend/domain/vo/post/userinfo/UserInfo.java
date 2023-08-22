package com.deer.fastdeerend.domain.vo.post.userinfo;

import com.deer.fastdeerend.domain.vo.post.userinfo.base.BaseInfo;
import com.deer.fastdeerend.domain.vo.post.userinfo.school.SchoolInfo;
import com.deer.fastdeerend.domain.vo.post.userinfo.social.SocialInfo;
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
public class UserInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 7130706640034489745L;

    private String userId;
    private String name;
    private String avatar;
    private String role;

    private SocialInfo socialInfo;
    private BaseInfo baseInfo;
    private SchoolInfo schoolInfo;
}
