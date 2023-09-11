package com.deer.fastdeerend.domain.entity.academic;

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
public class AcademicComment {
    @TableId
    private String academicCommentId;
    @TableField("user_id")
    private String userId;
    @TableField("academic_id")
    private String academicId;
    private String date;
    private String content;
}
