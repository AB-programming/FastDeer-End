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
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerRegistration implements Serializable {
    @Serial
    private static final long serialVersionUID = 2401611873676133950L;

    @TableId
    private String volunteerRegistrationId;
    @TableField("user_id")
    private String userId;
    @TableField("volunteer_id")
    private String volunteerId;
}
