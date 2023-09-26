package com.deer.fastdeerend.domain.dto.volunteer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerRegistrationRequest {
    private String userId;
    private String volunteerId;
}
