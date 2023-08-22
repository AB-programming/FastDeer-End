package com.deer.fastdeerend.domain.vo.post.social;

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
public class SocialStats implements Serializable {
    @Serial
    private static final long serialVersionUID = -6892876935869441257L;

    private Long browserCount;
    private Long likeCount;
    private Long bookmarkCount;
    private Boolean isLike;
    private Boolean isBookmark;
}
