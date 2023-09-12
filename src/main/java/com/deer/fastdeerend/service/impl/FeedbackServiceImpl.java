package com.deer.fastdeerend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.feedback.FeedbackMapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.entity.feedback.Feedback;
import com.deer.fastdeerend.domain.vo.feedback.FeedBackVo;
import com.deer.fastdeerend.service.FeedbackService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Resource
    private FeedbackMapper feedbackMapper;

    @Resource
    private UserMapper userMapper;

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

    @Override
    public List<FeedBackVo> selectFeedbackList() {
        List<Feedback> feedbacks = feedbackMapper.selectList(new QueryWrapper<>());
        return feedbacks.parallelStream()
                .map(feedback -> FeedBackVo.builder()
                        .feedbackId(feedback.getFeedbackId())
                        .userId(feedback.getUserId())
                        .name(userMapper.selectById(feedback.getUserId()).getNickName())
                        .avatar(userMapper.selectById(feedback.getUserId()).getAvatarUrl())
                        .role(userMapper.selectById(feedback.getUserId()).getRole())
                        .date(feedback.getDate())
                        .tag(feedback.getTag())
                        .rate(feedback.getRate())
                        .content(feedback.getContent())
                        .phone(feedback.getPhone())
                        .build())
                .collect(Collectors.toList());
    }
}
