package com.deer.fastdeerend.domain.entity.academic;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Academic {
    @TableId
    private String academicId;
    @TableField("user_id")
    private String userId;
    private String date;
    private String title;
    private String content;
    private String cover;
}
