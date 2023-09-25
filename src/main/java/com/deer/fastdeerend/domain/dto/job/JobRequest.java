package com.deer.fastdeerend.domain.dto.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobRequest {
    private String jobName;
    private String userId;
    private String degree;
    private String salary;
    private String description;
    private String company;
    private String date;
    private String deadline;
    private String contact;
}
