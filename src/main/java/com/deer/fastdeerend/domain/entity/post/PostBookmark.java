package com.deer.fastdeerend.domain.entity.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostBookmark implements Serializable {
    @Serial
    private static final long serialVersionUID = 7476466055607284501L;

    private String PostBookmarkId;
    private String userId;
    private String postId;
}
