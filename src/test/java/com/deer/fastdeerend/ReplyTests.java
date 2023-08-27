package com.deer.fastdeerend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.reply.ReplyMapper;
import com.deer.fastdeerend.domain.entity.reply.Reply;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ReplyTests {
    @Resource
    private ReplyMapper replyMapper;

    @Test
    public void testSelectAllReply() {
        List<Reply> replies = replyMapper.selectList(new QueryWrapper<>());
        replies.forEach(System.out::println);
    }
}
