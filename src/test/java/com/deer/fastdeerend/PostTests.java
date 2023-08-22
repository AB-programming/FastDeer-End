package com.deer.fastdeerend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deer.fastdeerend.dao.post.PostMapper;
import com.deer.fastdeerend.domain.entity.post.Post;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.domain.vo.post.content.Content;
import com.deer.fastdeerend.domain.vo.post.PostVo;
import com.deer.fastdeerend.service.PostService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PostTests {

    @Resource
    private PostMapper postMapper;

    @Resource
    private PostService postService;

    @Test
    public void testAddPost() {
        Post.PostBuilder builder = Post.builder();
        for (int i = 1; i <= 10; i++) {
            Post post = builder.postId(String.valueOf(i))
                    .urls("https://www.abstyle.top/")
                    .text("abstyle " + i)
                    .userId("1")
                    .title("ab " + i)
                    .browserCount((long) i)
                    .date("2020-11-21")
                    .build();

        int res = postMapper.insert(post);
        }
    }

    @Test
    public void testSelectPostById() {
        Post post = postMapper.selectPostById("1");
        System.out.println(post);
    }

    @Test
    public void testSelectPostWithUser() {
        QueryWrapper<Post> postQueryWrapper = new QueryWrapper<>();
        List<Post> posts = postMapper.selectAllPostWithUserAndOrderByDate();
        System.out.println("data length = " + posts.size());
        posts.forEach(System.out::println);
    }

    @Test
    public void testSelectPostWithUserPage() {
        Page<Post> page = new Page<>(2, 2);
        List<Post> posts = postMapper.selectPostWithUserPageOrderByDate(page);
        System.out.println("data length = " + posts.size());
        posts.forEach(System.out::println);
    }

    @Test
    public void testPostVo() {
        PostVo.PostVoBuilder builder = PostVo.builder();
        Content.ContentBuilder contentBuilder = Content.builder();
        User.UserBuilder userBuilder = User.builder();

        User user = userBuilder.id("1")
                .nickName("AB")
                .avatarUrl("https://abstyle.top")
                .gender("男")
                .role("ROLE_user").build();
        PostVo postVo = builder.postId("1")
                .date("2003-1-1")
                .content(contentBuilder.urls(Arrays.asList("https://abstyle.top",
                                "https://www.baidu.com"))
                        .text("文本")
                        .title("标题")
                        .build())
                .build();
    }

    @Test
    public void testSelectPostWithUserAndSocialStatsPageOrderByDate() {
        String url = "123.jpg";
        String filename = StringUtils.getFilenameExtension(url);
        System.out.println(filename);
    }
}
