package com.deer.fastdeerend.domain.entity.user;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author AB-style
 * @date 2023/07/01
 */
@Data
@Builder
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -485535469501411414L;
    private String id;
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
