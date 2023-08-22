package com.deer.fastdeerend.controller;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.bo.ReplyBo;
import com.deer.fastdeerend.domain.dto.reply.ReplyRequest;
import com.deer.fastdeerend.service.ReplyService;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Resource
    private ReplyService replyService;

    @PostMapping("/insertReply")
    public HttpResponse<String> insertReply(@RequestBody ReplyRequest replyRequest) {
        HttpResponse.HttpResponseBuilder<String> builder = HttpResponse.builder();

        if (!StringUtils.hasText(replyRequest.getContentText())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少contentText参数")
                    .build();
        }
        if (!StringUtils.hasText(replyRequest.getTarget())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少target参数")
                    .build();
        }
        if (!StringUtils.hasText(replyRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        if (null == replyRequest.getReplyType()) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少replyType参数")
                    .build();
        }
        ReplyBo replyBo = replyService.insertReply(replyRequest.getDate(), replyRequest.getContentText(),
                replyRequest.getTarget(), replyRequest.getUserId(), replyRequest.getReplyType());

        if (replyBo.isStatus()) {
            return builder
                    .code(HttpResponseStatusCodeSet.OK.getValue())
                    .msg("回复成功")
                    .data(replyBo.getReplyId())
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.InternalServerError.getValue())
                .msg("服务端错误")
                .build();
    }

    @PostMapping("/incrementReplyLike")
    public HttpResponse<Boolean> incrementReplyLike(@RequestBody ReplyRequest replyRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();

        if (!StringUtils.hasText(replyRequest.getReplyId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少replyId参数")
                    .build();
        }
        if (!StringUtils.hasText(replyRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("点赞成功")
                .data(replyService.incrementReplyLike(replyRequest.getReplyId(), replyRequest.getUserId()))
                .build();
    }

    @DeleteMapping("/decrementReplyLike")
    public HttpResponse<Boolean> decrementReplyLike(@RequestBody ReplyRequest replyRequest) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();

        if (!StringUtils.hasText(replyRequest.getReplyId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少replyId参数")
                    .build();
        }
        if (!StringUtils.hasText(replyRequest.getUserId())) {
            return builder
                    .code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }

        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("已取消点赞")
                .data(replyService.decrementReplyLike(replyRequest.getReplyId(), replyRequest.getUserId()))
                .build();
    }
}
