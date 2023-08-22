package com.deer.fastdeerend.controller;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.bo.CommentBo;
import com.deer.fastdeerend.domain.dto.comment.CommentRequest;
import com.deer.fastdeerend.domain.vo.comment.CommentVo;
import com.deer.fastdeerend.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/selectAllCommentByPostId")
    public HttpResponse<List<CommentVo>> selectAllCommentByPostId(String postId, String userId) {
        HttpResponse.HttpResponseBuilder<List<CommentVo>> builder = HttpResponse.builder();

        if (null == postId) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少postId参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("评论查询成功")
                .data(commentService.selectAllCommentByPostId(postId, userId))
                .build();
    }

    @PostMapping("/insertComment")
    public HttpResponse<String> insertComment(@RequestBody CommentRequest commentRequest) {
        HttpResponse.HttpResponseBuilder<String> builder = HttpResponse.builder();

        if (!StringUtils.hasText(commentRequest.getContentText())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少contentText参数")
                    .build();
        }
        if (!StringUtils.hasText(commentRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        if (!StringUtils.hasText(commentRequest.getPostId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少postId参数")
                    .build();
        }
        CommentBo commentBo = commentService.insertComment(commentRequest.getDate(), commentRequest.getContentText(),
                commentRequest.getUserId(), commentRequest.getPostId());
        if (commentBo.isStatus()) {
            return builder
                    .code(HttpResponseStatusCodeSet.OK.getValue())
                    .msg("评论成功")
                    .data(commentBo.getCommentId())
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.InternalServerError.getValue())
                .msg("服务端错误")
                .build();
    }

    @PostMapping("/incrementCommentLike")
    public HttpResponse<Boolean> incrementCommentLike(@RequestBody CommentRequest commentRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();

        if (!StringUtils.hasText(commentRequest.getCommentId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少commentId参数")
                    .build();
        }

        if (!StringUtils.hasText(commentRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("点赞成功")
                .data(commentService.incrementCommentLike(commentRequest.getCommentId(), commentRequest.getUserId()))
                .build();
    }

    @DeleteMapping("/decrementCommentLike")
    public HttpResponse<Boolean> decrementCommentLike(@RequestBody CommentRequest commentRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();

        if (!StringUtils.hasText(commentRequest.getCommentId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少commentId参数")
                    .build();
        }

        if (!StringUtils.hasText(commentRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("已取消点赞")
                .data(commentService.decrementCommentLike(commentRequest.getCommentId(), commentRequest.getUserId()))
                .build();
    }

    @GetMapping("/selectCommentCountByUserId")
    public HttpResponse<Long> selectCommentCountByUserId(String userId) {
        HttpResponse.HttpResponseBuilder<Long> builder = HttpResponse.builder();

        if (!StringUtils.hasText(userId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(commentService.selectCommentCountByUserId(userId))
                .build();
    }
}
