package com.deer.fastdeerend.service.impl;

import com.deer.fastdeerend.dao.feedback.FeedbackMapper;
import com.deer.fastdeerend.domain.entity.feedback.Feedback;
import com.deer.fastdeerend.service.FeedbackService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Resource
    private FeedbackMapper feedbackMapper;

    @Override
    public Boolean submitFeedback(Feedback feedback) {
        return feedbackMapper.insert(Feedback.builder()
                .feedbackId(UUID.randomUUID().toString())
                .userId(feedback.getUserId())
                .date(feedback.getDate())
                .tag(feedback.getTag())
                .rate(feedback.getRate())
                .content(feedback.getContent())
                .phone(feedback.getPhone())
                .build()) > 0;
    }
}
