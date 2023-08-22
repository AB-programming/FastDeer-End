package com.deer.fastdeerend.domain.vo.reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubReply implements Serializable {
    @Serial
    private static final long serialVersionUID = -535754457224929980L;

    private String name;
    private String contentStr;
}
