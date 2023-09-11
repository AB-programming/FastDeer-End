package com.deer.fastdeerend.domain.dto.academic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicCommentRequest {
    private String userId;
    private String academicId;
    private String date;
    private String content;
}
