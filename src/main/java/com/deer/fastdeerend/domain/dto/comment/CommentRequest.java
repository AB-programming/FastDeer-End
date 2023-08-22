package com.deer.fastdeerend.domain.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private String commentId;
    private String date;
    private String contentText;
    private String userId;
    private String postId;
}
