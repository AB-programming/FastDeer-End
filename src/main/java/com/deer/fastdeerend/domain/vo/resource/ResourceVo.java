package com.deer.fastdeerend.domain.vo.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceVo {
    private String resourceId;
    private String userId;
    private String name;
    private String avatar;
    private String date;
    private String description;
    private String url;
    private String fileName;
}
