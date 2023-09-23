package com.deer.fastdeerend.util.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchoolLoginResult {
    private boolean status;
    private String data;
}
