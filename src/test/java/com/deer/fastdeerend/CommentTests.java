package com.deer.fastdeerend;

import com.deer.fastdeerend.domain.vo.comment.CommentVo;
import com.deer.fastdeerend.service.CommentService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CommentTests {

    @Resource
    private CommentService commentService;

    @Test
    public void testSelectAllCommentByPostId() {
        List<CommentVo> commentVos = commentService.selectAllCommentByPostId("1", "1");
        commentVos.forEach(System.out::println);
    }

}
