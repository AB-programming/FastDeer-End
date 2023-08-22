package com.deer.fastdeerend.domain.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
    private String postId;
    private String date;
    private String title;
    private String text;
    private String userId;
}
