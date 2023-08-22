package com.deer.fastdeerend.domain.entity.user;

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
public class UserRelate implements Serializable {
    @Serial
    private static final long serialVersionUID = -7123914275039478865L;

    private String userRelateId;
    private String userId;
    private String target;
}
