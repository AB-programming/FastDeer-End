package com.deer.fastdeerend.domain.dto.volunteer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerRequest {
    private String userId;
    private String title;
    private String description;
    private String date;
    private String deadline;
}
