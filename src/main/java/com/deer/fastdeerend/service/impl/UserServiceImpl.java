package com.deer.fastdeerend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.dao.user.UserRelateMapper;
import com.deer.fastdeerend.domain.bo.AvatarBo;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.domain.entity.user.UserRelate;
import com.deer.fastdeerend.service.UserService;
import com.deer.fastdeerend.util.RedisUtil;
import com.google.protobuf.compiler.PluginProtos;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

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
        FileSystemUtils.deleteRecursively(new File(uploadLocation + fileName));
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

        if (!"logo.jpg".equals(originFileName)) {
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
}