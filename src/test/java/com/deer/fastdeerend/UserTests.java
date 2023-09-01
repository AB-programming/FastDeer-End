package com.deer.fastdeerend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.service.LoginService;
import com.deer.fastdeerend.service.UserService;
import com.deer.fastdeerend.util.JWTUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户测试类
 *
 * @author AB-style
 * @date 2023/07/05
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class UserTests {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private LoginService loginService;

    @Resource
    private JWTUtil jwtUtil;


    @Test
    public void testLogin() {
        User.UserBuilder builder = User.builder();
        User user = builder.id("1")
                .nickName("AB")
                .avatarUrl("https://abstyle.top")
                .gender("男")
                .role("ROLE_user").build();

        try {
            String login = loginService.login("1", "AB", "https://abstyle.top", "男", "ROLE_user");
            System.out.println(login);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateUserById() {
        Integer res = userService.updateUserById("1", "tom", "男", "山东省滨州市沾化区", "2022-03-02",
                "山东交通学院", "飞行器制造工程", "本科", "2025-09-03");
        System.out.println("res = " + res);
    }

    @Test
    public void testSelectLike() {
        List<User> res = new ArrayList<>();
        String str = "1";

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        boolean exists = userMapper.exists(wrapper.eq("id", str));
        if (exists) {
            User user = userMapper.selectById(str);
            res.add(user);
        }

        List<User> users = userMapper.selectList(new QueryWrapper<User>().like("nick_name", "%" + str + "%"));
        res.addAll(users);
        log.info(res.toString());
    }
}