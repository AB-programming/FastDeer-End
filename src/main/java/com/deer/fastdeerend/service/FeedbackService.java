package com.deer.fastdeerend.service;

import com.deer.fastdeerend.domain.entity.feedback.Feedback;
import com.deer.fastdeerend.domain.vo.feedback.FeedBackVo;

import java.util.List;

public interface FeedbackService {
    public Boolean submitFeedback(Feedback feedback);

    public List<FeedBackVo> selectFeedbackList();
}
