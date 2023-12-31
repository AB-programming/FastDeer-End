package com.deer.fastdeerend.script;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.post.PostMapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.entity.post.Post;
import com.deer.fastdeerend.domain.entity.user.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * A script to bulk change the url in the database
 *
 * @author AB-style
 * @date 2023/08/27
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UrlScriptTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PostMapper postMapper;

    @Value("${dev.host}")
    private String host;

    @Test
    public void testUpdateUserUrl() {
        String address = host.split("/")[2];
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        for (User user : users) {
            if (user.getRole().equals("ROLE_admin")) continue;
            if (user.getRole().equals("ROLE_school")) continue;
            String avatarUrl = user.getAvatarUrl();
            String[] split = avatarUrl.split("/");
            split[2] = address;
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : split) {
                stringBuilder.append(s);
                stringBuilder.append('/');
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            user.setAvatarUrl(stringBuilder.toString());
            userMapper.updateById(user);
        }
    }

    @Test
    public void testUpdatePostUrls() {
        String address = host.split("/")[2];
        List<Post> posts = postMapper.selectList(new QueryWrapper<>());
        for (Post post : posts) {
            String urls = post.getUrls();
            String[] urlsSplit = urls.split(";");
            StringBuilder urlsBuilder = new StringBuilder();
            for (String url : urlsSplit) {
                String[] split = url.split("/");
                split[2] = address;
                StringBuilder stringBuilder = new StringBuilder();
                for (String s : split) {
                    stringBuilder.append(s);
                    stringBuilder.append('/');
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                urlsBuilder.append(stringBuilder);
                urlsBuilder.append(";");
            }
            urlsBuilder.deleteCharAt(urlsBuilder.length() - 1);
            post.setUrls(urlsBuilder.toString());
            postMapper.updateById(post);
        }
    }
}
