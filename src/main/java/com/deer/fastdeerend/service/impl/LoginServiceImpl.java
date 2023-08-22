package com.deer.fastdeerend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.service.LoginService;
import com.deer.fastdeerend.util.JWTUtil;
import com.deer.fastdeerend.util.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 登录模块业务层处理
 *
 * @author AB-style
 * @date 2023/07/01
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private JWTUtil jwtUtil;

    @Resource
    private RedisUtil<String> redisUtil;

    @Override
    @Transactional
    public String login(String id, String nickName, String avatarUrl, String gender, String role) throws JsonProcessingException {
        User user = User.builder()
                .id(id)
                .nickName(nickName)
                .avatarUrl(avatarUrl)
                .gender(gender)
                .role(role).build();

        if (!userMapper.exists(new QueryWrapper<User>().eq("id", id))) {
            userMapper.insert(user);
        }

        return jwtUtil.createToken(user);
    }

    @Override
    public Boolean isLogin(String token) {
        return redisUtil.hasKey(token);
    }
}
