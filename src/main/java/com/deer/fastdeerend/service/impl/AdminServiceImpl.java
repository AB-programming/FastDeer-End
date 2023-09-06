package com.deer.fastdeerend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.service.AdminService;
import com.deer.fastdeerend.util.JWTUtil;
import com.deer.fastdeerend.util.RedisUtil;
import com.deer.fastdeerend.util.model.VerifyTokenResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {

    @Value("${fastDeer.admin.username}")
    private String username;

    @Value("${fastDeer.admin.password}")
    private String password;

    @Value("${dev.avatarUpload.uploadLocation}")
    private String uploadLocation;

    @Resource
    private JWTUtil jwtUtil;

    @Resource
    private RedisUtil<String> redisUtil;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public VerifyTokenResult login(String username, String password) throws JsonProcessingException {
        VerifyTokenResult.VerifyTokenResultBuilder builder = VerifyTokenResult.builder();
        if (username.equals(this.username) && password.equals(this.password)) {
            String token = jwtUtil.createToken(User.builder()
                    .id("0")
                    .role("ROLE_admin")
                    .build());
            return builder.status(true)
                    .data(token)
                    .build();
        }

        return builder
                .status(false)
                .data("用户名或密码错误")
                .build();
    }

    @Override
    public Boolean isLogin(String token) {
        return redisUtil.hasToken(token);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectList(new QueryWrapper<User>().ne("role", "ROLE_admin"));
    }

    @Override
    @Transactional
    public Boolean removeUserById(String id) throws JsonProcessingException {
        Set<String> tokens = redisUtil.getAllToken();
        for (String token : tokens) {
            VerifyTokenResult result = jwtUtil.verifyToken(token);
            if (result.isStatus()) {
                User user = objectMapper.readValue(result.getData(), User.class);
                if (user.getId().equals(id)) {
                    redisUtil.dropToken(token);
                }
            }
        }
        String fileName = StringUtils.getFilename(userMapper.selectById(id).getAvatarUrl());
        if (!"logo.png".equals(fileName)) {
            FileSystemUtils.deleteRecursively(new File(uploadLocation + fileName));
        }
        return userMapper.deleteById(id) > 0;
    }
}
