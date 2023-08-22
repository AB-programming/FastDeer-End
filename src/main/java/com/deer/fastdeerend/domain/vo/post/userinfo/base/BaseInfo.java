package com.deer.fastdeerend.domain.vo.post.userinfo.base;

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
public class BaseInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = -4032683974736451968L;

    private String gender;
    private String address;
}
