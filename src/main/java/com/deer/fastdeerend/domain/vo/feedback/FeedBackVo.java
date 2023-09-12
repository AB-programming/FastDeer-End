package com.deer.fastdeerend.domain.vo.feedback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedBackVo {
    private String feedbackId;
    private String userId;
    private String name;
    private String avatar;
    private String role;
    private String date;
    private String tag;
    private String rate;
    private String content;
    private String phone;
}
