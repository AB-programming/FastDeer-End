package com.deer.fastdeerend.util.model;

import lombok.Builder;
import lombok.Data;

/**
 * Token校验结果
 *
 * @author AB-style
 * @date 2023/07/01
 */
@Data
@Builder
public class VerifyTokenResult {
    private boolean status;
    private String data;
}
