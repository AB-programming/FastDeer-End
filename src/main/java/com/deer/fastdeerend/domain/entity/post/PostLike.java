package com.deer.fastdeerend.domain.entity.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostLike implements Serializable {
    @Serial
    private static final long serialVersionUID = 4300600576694574280L;

    private String postLikeId;
    private String userId;
    private String postId;
}
