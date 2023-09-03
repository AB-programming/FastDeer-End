package com.deer.fastdeerend.service.impl;

import com.deer.fastdeerend.config.redis.RedisConfig;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.service.AdminService;
import com.deer.fastdeerend.util.JWTUtil;
import com.deer.fastdeerend.util.RedisUtil;
import com.deer.fastdeerend.util.model.VerifyTokenResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Value("${fastDeer.admin.username}")
    private String username;

    @Value("${fastDeer.admin.password}")
    private String password;

    @Resource
    private JWTUtil jwtUtil;

    @Resource
    private RedisUtil<String> redisUtil;

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
}
