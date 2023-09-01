package com.deer.fastdeerend.service;

import com.deer.fastdeerend.domain.bo.AvatarBo;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.domain.vo.post.userinfo.UserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    public Integer updateUserById(String id, String nickName, String gender,
                                  String place, String birth, String school,
                                  String major, String qualification,
                                  String graduationDate);

    public Integer deleteUserById(String openId, String token);

    public User getUserById(String id);

    public AvatarBo updateAvatar(MultipartFile file, String id);

    public Boolean incrementAttention(String userId, String targetId);

    public Boolean decrementAttention(String userId, String targetId);

    public Boolean isAttention(String userId, String targetId);

    public Long selectUserRelateCountByUserId(String userId);

    public UserInfo getUserInfoByUserId(String userId);

    public List<User> selectUserByKeyword(String keyword);
}
