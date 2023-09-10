package com.deer.fastdeerend.domain.vo.academic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicDisplayVo {
    private String academicId;
    private String userId;
    private String name;
    private String avatar;
    private String title;
    private String date;
    private String cover;
}
