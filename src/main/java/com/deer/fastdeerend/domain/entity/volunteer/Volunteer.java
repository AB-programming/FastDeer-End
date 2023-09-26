package com.deer.fastdeerend.domain.entity.volunteer;

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
public class Volunteer implements Serializable {
    @Serial
    private static final long serialVersionUID = 5462105672927639517L;
    @TableId
    private String volunteerId;
    @TableField("user_id")
    private String userId;
    private String title;
    private String description;
    private String date;
    private String deadline;
}
