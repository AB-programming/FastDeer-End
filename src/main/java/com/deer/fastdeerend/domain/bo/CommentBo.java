package com.deer.fastdeerend.domain.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentBo {
    private boolean status;
    private String commentId;
}
