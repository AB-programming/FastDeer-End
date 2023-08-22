package com.deer.fastdeerend.controller;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.dto.post.PostRequest;
import com.deer.fastdeerend.domain.vo.post.PostVo;
import com.deer.fastdeerend.service.PostService;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Resource
    private PostService postService;

    @GetMapping("/selectPostPage")
    public HttpResponse<List<PostVo>> selectPostPage(String userId, Long current, Long size) {
        HttpResponse.HttpResponseBuilder<List<PostVo>> builder = HttpResponse.builder();
        if (null == current) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少current参数")
                    .build();
        }

        if (null == size) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少size参数")
                    .build();
        }

        List<PostVo> postVos = postService.selectPostWithUserAndSocialStatsOrderByDate(userId, current, size);
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(postVos)
                .build();
    }

    @PostMapping("/publishPost")
    public HttpResponse<String> publishPost(@RequestBody PostRequest postRequest) {
        HttpResponse.HttpResponseBuilder<String> builder = HttpResponse.builder();
        if (!StringUtils.hasText(postRequest.getTitle())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("标题不能为空")
                    .build();
        }

        if (!StringUtils.hasText(postRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("登录过期")
                    .build();
        }

        String publishRes = postService.publishPost(postRequest.getDate(), postRequest.getTitle(),
                postRequest.getText(), postRequest.getUserId());
        if (StringUtils.hasText(publishRes)) {
            return builder
                    .code(HttpResponseStatusCodeSet.OK.getValue())
                    .msg("发布成功")
                    .data(publishRes)
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.InternalServerError.getValue())
                .msg("服务端错误")
                .build();
    }

    @PostMapping("/uploadPostFile")
    public HttpResponse<Boolean> uploadPostFile(@RequestParam("file") MultipartFile file, String postId, Integer idx) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(postId)) {
            builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少postId参数")
                    .data(false)
                    .build();
        }
        if (null == idx) {
            builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少idx参数")
                    .data(false)
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("上传成功")
                .data(postService.uploadPostFile(file, postId, idx))
                .build();
    }

    @GetMapping("/selectPostByUserId")
    public HttpResponse<List<PostVo>> selectPostByUserId(@RequestParam("userId") String userId) {
        HttpResponse.HttpResponseBuilder<List<PostVo>> builder = HttpResponse.builder();
        if (!StringUtils.hasText(userId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(postService.selectPostByUserId(userId))
                .build();
    }

    @DeleteMapping("/deletePostByPostId")
    public HttpResponse<Boolean> deletePostByPostId(@RequestBody String postId) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(postId)) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少postId参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("删帖成功")
                .data(postService.deletePostByPostId(postId))
                .build();
    }

    @PostMapping("/incrementBrowser")
    public HttpResponse<Boolean> incrementBrowser(@RequestBody String postId) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (StringUtils.hasText(postId)) {
            builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少postId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("OK")
                .data(postService.incrementBrowser(postId))
                .build();
    }

    @PostMapping("/incrementPostLike")
    public HttpResponse<Boolean> incrementPostLike(@RequestBody PostRequest postRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();

        if (!StringUtils.hasText(postRequest.getPostId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少postId参数")
                    .build();
        }
        if (!StringUtils.hasText(postRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("点赞成功")
                .data(postService.incrementPostLike(postRequest.getPostId(), postRequest.getUserId()))
                .build();
    }

    @DeleteMapping("/decrementPostLike")
    public HttpResponse<Boolean> decrementPostLike(@RequestBody PostRequest postRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();

        if (!StringUtils.hasText(postRequest.getPostId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少postId参数")
                    .build();
        }
        if (!StringUtils.hasText(postRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("已取消点赞")
                .data(postService.decrementPostLike(postRequest.getPostId(), postRequest.getUserId()))
                .build();
    }

    @PostMapping("/incrementPostBookmark")
    public HttpResponse<Boolean> incrementPostBookmark(@RequestBody PostRequest postRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();

        if (!StringUtils.hasText(postRequest.getPostId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少postId参数")
                    .build();
        }
        if (!StringUtils.hasText(postRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("收藏成功")
                .data(postService.incrementPostBookmark(postRequest.getPostId(), postRequest.getUserId()))
                .build();
    }

    @DeleteMapping("/decrementPostBookmark")
    public HttpResponse<Boolean> decrementPostBookmark(@RequestBody PostRequest postRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();

        if (!StringUtils.hasText(postRequest.getPostId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少postId参数")
                    .build();
        }
        if (!StringUtils.hasText(postRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("已取消收藏")
                .data(postService.decrementPostBookmark(postRequest.getPostId(), postRequest.getUserId()))
                .build();
    }

    @GetMapping("/selectPostLkeCountByUserId")
    public HttpResponse<Long> selectPostLkeCountByUserId(String userId) {
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
                .data(postService.selectPostLikeCountByUserId(userId))
                .build();
    }

    @GetMapping("/selectPostBookmarkCountByUserId")
    public HttpResponse<Long> selectPostBookmarkCountByUserId(String userId) {
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
                .data(postService.selectPostBookmarkCountByUserId(userId))
                .build();
    }
}
