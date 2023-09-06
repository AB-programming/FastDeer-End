package com.deer.fastdeerend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.post.PostLikeMapper;
import com.deer.fastdeerend.dao.post.PostMapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.dao.user.UserRelateMapper;
import com.deer.fastdeerend.domain.bo.AvatarBo;
import com.deer.fastdeerend.domain.entity.post.Post;
import com.deer.fastdeerend.domain.entity.post.PostLike;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.domain.entity.user.UserRelate;
import com.deer.fastdeerend.domain.vo.post.userinfo.UserInfo;
import com.deer.fastdeerend.domain.vo.post.userinfo.base.BaseInfo;
import com.deer.fastdeerend.domain.vo.post.userinfo.school.SchoolInfo;
import com.deer.fastdeerend.domain.vo.post.userinfo.social.SocialInfo;
import com.deer.fastdeerend.service.UserService;
import com.deer.fastdeerend.util.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 用户模块业务层处理
 *
 * @author AB-style
 * @date 2023/07/05
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${dev.avatarUpload.uploadLocation}")
    private String uploadLocation;

    @Value("${dev.avatarUpload.avatarUrl}")
    private String avatarUrl;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisUtil<String> redisUtil;

    @Resource
    private UserRelateMapper userRelateMapper;

    @Resource
    private PostMapper postMapper;

    @Resource
    private PostLikeMapper postLikeMapper;

    /**
     * 通过id更新用户信息
     *
     * @param id             id
     * @param nickName       昵称
     * @param gender         性别
     * @param place          居住地
     * @param birth          出生日期
     * @param school         学校
     * @param major          专业
     * @param qualification  学历
     * @param graduationDate 毕业时间
     * @return {@link Integer}
     */
    @Override
    public Integer updateUserById(String id, String nickName, String gender,
                                  String place, String birth, String school,
                                  String major, String qualification,
                                  String graduationDate) {
        User user = userMapper.selectById(id);
        user.setNickName(nickName);
        user.setGender(gender);
        user.setPlace(place);
        user.setBirth(birth);
        user.setSchool(school);
        user.setMajor(major);
        user.setQualification(qualification);
        user.setGraduationDate(graduationDate);
        return userMapper.updateById(user);
    }

    @Override
    @Transactional
    public Integer deleteUserById(String id, String token) {
        redisUtil.dropToken(token);
        String fileName = StringUtils.getFilename(userMapper.selectById(id).getAvatarUrl());
        if (!"logo.png".equals(fileName)) {
            FileSystemUtils.deleteRecursively(new File(uploadLocation + fileName));
        }
        return userMapper.deleteById(id);
    }

    @Override
    public User getUserById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    @Transactional
    public AvatarBo updateAvatar(MultipartFile file, String id) {
        AvatarBo.AvatarBoBuilder builder = AvatarBo.builder();

        User user = userMapper.selectById(id);

        String originUrl = user.getAvatarUrl();
        String originFileName = StringUtils.getFilename(originUrl);

        boolean delRes = true;

        if (!"logo.png".equals(originFileName)) {
            delRes = FileSystemUtils.deleteRecursively(new File(uploadLocation + originFileName));
        }

        String fileType = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String loadPosition = uploadLocation + id + "." + fileType;
        try {
            file.transferTo(new File(loadPosition));
        } catch (IOException e) {
            return builder.status(false).build();
        }

        user.setAvatarUrl(avatarUrl + id + "." + fileType);
        int updateRes = userMapper.updateById(user);

        if (delRes && updateRes > 0) {
            return builder.status(true).avatarUrl(avatarUrl + id + "." + fileType).build();
        }
        return builder.status(false).build();
    }

    @Override
    public Boolean incrementAttention(String userId, String targetId) {
        return userRelateMapper.insert(UserRelate.builder()
                .userRelateId(UUID.randomUUID().toString())
                .userId(userId)
                .target(targetId)
                .build()) > 0;
    }

    @Override
    public Boolean decrementAttention(String userId, String targetId) {
        return userRelateMapper.delete(new QueryWrapper<UserRelate>()
                .eq("user_id", userId)
                .and(query -> query.eq("target", targetId))) > 0;
    }

    @Override
    public Boolean isAttention(String userId, String targetId) {
        return userRelateMapper.exists(new QueryWrapper<UserRelate>()
                .eq("user_id", userId)
                .and(query -> query.eq("target", targetId)));
    }

    @Override
    public Long selectUserRelateCountByUserId(String userId) {
        return userRelateMapper.selectCount(new QueryWrapper<UserRelate>()
                .eq("user_id", userId));
    }

    @Override
    public UserInfo getUserInfoByUserId(String userId) {
        User user = userMapper.selectById(userId);
        Long fans = userRelateMapper.selectCount(new QueryWrapper<UserRelate>()
                .eq("target", userId));
        Long following = userRelateMapper.selectCount(new QueryWrapper<UserRelate>()
                .eq("user_id", userId));
        List<Post> userPosts = postMapper.selectList(new QueryWrapper<Post>()
                .eq("user_id", user.getId()));
        AtomicReference<Long> likes = new AtomicReference<>(0L);
        userPosts.forEach(userPost -> {
            likes.updateAndGet(v -> v + postLikeMapper.selectCount(new QueryWrapper<PostLike>()
                    .eq("post_id", userPost.getPostId())));
        });

        return UserInfo.builder()
                .userId(userId)
                .name(user.getNickName())
                .avatar(user.getAvatarUrl())
                .role(user.getRole())
                .socialInfo(SocialInfo.builder()
                        .likes(likes.get())
                        .fans(fans)
                        .following(following)
                        .build())
                .baseInfo(BaseInfo.builder()
                        .gender(user.getGender())
                        .address(user.getPlace())
                        .build())
                .schoolInfo(SchoolInfo.builder()
                        .schoolName(user.getSchool())
                        .major(user.getMajor())
                        .degree(user.getQualification())
                        .graduation(user.getGraduationDate())
                        .build())
                .build();
    }

    @Override
    public List<User> selectUserByKeyword(String keyword) {
        List<User> res = new ArrayList<>();
        if (userMapper.exists(new QueryWrapper<User>().eq("id", keyword))) {
            res.add(userMapper.selectById(keyword));
        }
        res.addAll(userMapper.selectList(new QueryWrapper<User>().like("nick_name", keyword)));
        return res;
    }
}