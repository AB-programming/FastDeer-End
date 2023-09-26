package com.deer.fastdeerend.domain.vo.volunteer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  志愿活动报名者Vo类
 *
 * @author AB-style
 * @date 2023/09/26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerApplicant {
    private String userId;
    private String name;
    private String avatar;
    private String gender;
    private String school;
    private String major;
    private String qualification;
}
