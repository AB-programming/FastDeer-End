package com.deer.fastdeerend.domain.vo.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobVo {
    private String jobId;
    private String jobName;
    private String userId;
    private String nickName;
    private String avatar;
    private String degree;
    private String salary;
    private String description;
    private String company;
    private String date;
    private String deadline;
    private String contact;
}
