package com.deer.fastdeerend.domain.entity.event;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @TableId
    private String eventId;
    @TableField("user_id")
    private String userId;
    private String date;
    private String title;
    private String url;
    private String cover;
}
