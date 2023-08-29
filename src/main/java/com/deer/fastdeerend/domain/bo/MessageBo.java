package com.deer.fastdeerend.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务消息类实体
 *
 * @author AB-style
 * @date 2023/08/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageBo {
    private String sender;
    private String receiver;
    private String content;
}
