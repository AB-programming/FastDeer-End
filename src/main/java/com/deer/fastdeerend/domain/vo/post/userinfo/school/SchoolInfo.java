package com.deer.fastdeerend.domain.vo.post.userinfo.school;

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
public class SchoolInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = -2039625167084295766L;

    private String schoolName;
    private String degree;
    private String major;
    private String graduation;
}
