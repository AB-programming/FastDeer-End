package com.deer.fastdeerend.domain.entity.job;

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
public class Job implements Serializable {
    @Serial
    private static final long serialVersionUID = 8533650101598996224L;

    @TableId
    private String jobId;
    private String jobName;
    @TableField("user_id")
    private String userId;
    private String degree;
    private String salary;
    private String description;
    private String company;
    private String date;
    private String deadline;
    private String contact;
}
