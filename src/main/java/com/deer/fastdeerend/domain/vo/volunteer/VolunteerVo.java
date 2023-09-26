package com.deer.fastdeerend.domain.vo.volunteer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerVo {
    private String volunteerId;
    private String userId;
    private String name;
    private String avatar;
    private String title;
    private String description;
    private String date;
    private String deadline;
    private List<VolunteerApplicant> members;
}
