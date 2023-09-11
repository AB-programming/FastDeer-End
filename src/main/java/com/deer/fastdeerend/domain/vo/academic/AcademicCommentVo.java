package com.deer.fastdeerend.domain.vo.academic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicCommentVo {
    private String academicCommentId;
    private String userId;
    private String name;
    private String avatar;
    private String date;
    private String content;
}
