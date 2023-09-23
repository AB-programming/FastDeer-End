package com.deer.fastdeerend.domain.vo.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventVo {
    private String eventId;
    private String userId;
    private String name;
    private String avatar;
    private String title;
    private String cover;
    private String date;
}
