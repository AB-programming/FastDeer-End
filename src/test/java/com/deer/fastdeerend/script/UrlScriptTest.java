package com.deer.fastdeerend.script;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.entity.user.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UrlScriptTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testUpdateUrl() {
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        for (User user: users) {
            System.out.println(user);
        }
    }
}
