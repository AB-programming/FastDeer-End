package com.deer.fastdeerend.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 990660852655734130L;

    private String code;
    private String msg;
    private T data;
}
