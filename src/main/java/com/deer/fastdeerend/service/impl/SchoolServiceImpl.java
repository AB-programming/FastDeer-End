package com.deer.fastdeerend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.school.SchoolMapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.entity.school.School;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.service.SchoolService;
import com.deer.fastdeerend.util.JWTUtil;
import com.deer.fastdeerend.util.RedisUtil;
import com.deer.fastdeerend.util.model.SchoolLoginResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Resource
    private SchoolMapper schoolMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private JWTUtil jwtUtil;

    @Resource
    private RedisUtil<String> redisUtil;

    @Override
    @Transactional
    public SchoolLoginResult login(String username, String password) {
        if (!userMapper.exists(new QueryWrapper<User>()
                .lambda()
                .eq(User::getId, username)) ||
                !schoolMapper.exists(new QueryWrapper<School>()
                        .lambda()
                        .eq(School::getUserId, username))) {
            return SchoolLoginResult.builder()
                    .status(false)
                    .data("该账户不存在")
                    .build();
        }
        User user = User.builder()
                .id(username)
                .nickName(userMapper.selectById(username).getNickName())
                .avatarUrl(userMapper.selectById(username).getAvatarUrl())
                .role(userMapper.selectById(username).getRole())
                .build();

        if (!schoolMapper.selectOne(new QueryWrapper<School>()
                .lambda()
                .eq(School::getUserId, username)).getPassword().equals(password)) {
            return SchoolLoginResult.builder()
                    .status(false)
                    .data("密码错误")
                    .build();
        }
        try {
            String token = jwtUtil.createToken(user);
            return SchoolLoginResult.builder()
                    .status(true)
                    .data(token)
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return SchoolLoginResult.builder()
                    .status(false)
                    .data("服务器内部错误")
                    .build();
        }
    }

    @Override
    public Boolean isLogin(String token) {
        return redisUtil.hasToken(token);
    }
}
