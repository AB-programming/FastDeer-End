package com.deer.fastdeerend.domain.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRelateRequest {
    private String userId;
    private String targetId;
}
