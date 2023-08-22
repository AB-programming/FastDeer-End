package com.deer.fastdeerend.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户请求DTO
 *
 * @author AB-style
 * @date 2023/07/01
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String openId;
    private String nickName;
    private String avatarUrl;
    private String gender;
    private String place;
    private String birth;
    private String school;
    private String major;
    private String qualification;
    private String graduationDate;
    private String role;
}
