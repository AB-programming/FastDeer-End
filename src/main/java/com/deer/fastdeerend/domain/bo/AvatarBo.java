package com.deer.fastdeerend.domain.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvatarBo {
    boolean status;
    String avatarUrl;
}
