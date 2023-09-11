package com.deer.fastdeerend.domain.entity.academic;

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
public class AcademicComment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1034831081355717545L;
    @TableId
    private String academicCommentId;
    @TableField("user_id")
    private String userId;
    @TableField("academic_id")
    private String academicId;
    private String date;
    private String content;
}
