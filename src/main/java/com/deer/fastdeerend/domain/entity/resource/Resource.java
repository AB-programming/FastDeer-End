package com.deer.fastdeerend.domain.entity.resource;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource implements Serializable {
    @Serial
    private static final long serialVersionUID = -4122800250842775168L;
    @TableId
    private String resourceId;
    @TableField("user_id")
    private String userId;
    private String date;
    private String description;
    private String url;
    private String fileName;
    private String extension;
}
