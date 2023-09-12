package com.deer.fastdeerend.controller;

import com.deer.fastdeerend.common.HttpResponse;
import com.deer.fastdeerend.common.HttpResponseStatusCodeSet;
import com.deer.fastdeerend.domain.entity.feedback.Feedback;
import com.deer.fastdeerend.domain.vo.feedback.FeedBackVo;
import com.deer.fastdeerend.service.FeedbackService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Resource
    private FeedbackService feedbackService;

    @PostMapping("/submitFeedback")
    public HttpResponse<Boolean> submitFeedback(@RequestBody Feedback feedback) {
        HttpResponse.HttpResponseBuilder<Boolean> builder = HttpResponse.builder();
        if (!StringUtils.hasText(feedback.getUserId())) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少userId参数")
                    .build();
        }
        if (!StringUtils.hasText(feedback.getDate())) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少date参数")
                    .build();
        }
        if (!StringUtils.hasText(feedback.getTag())) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少tag参数")
                    .build();
        }
        if (!StringUtils.hasText(feedback.getRate())) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少rate参数")
                    .build();
        }
        if (!StringUtils.hasText(feedback.getContent())) {
            return builder.code(HttpResponseStatusCodeSet.BadRequest.getValue())
                    .msg("缺少content参数")
                    .build();
        }
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("提交成功")
                .data(feedbackService.submitFeedback(feedback))
                .build();
    }

    @GetMapping("/selectFeedbackList")
    @PreAuthorize("hasRole('admin')")
    public HttpResponse<List<FeedBackVo>> selectFeedbackList() {
        HttpResponse.HttpResponseBuilder<List<FeedBackVo>> builder = HttpResponse.builder();
        return builder
                .code(HttpResponseStatusCodeSet.OK.getValue())
                .msg("查询成功")
                .data(feedbackService.selectFeedbackList())
                .build();
    }
}
