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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Academic implements Serializable {
    @Serial
    private static final long serialVersionUID = 5347055075828586341L;
    @TableId
    private String academicId;
    @TableField("user_id")
    private String userId;
    private String date;
    private String title;
    private String content;
    private String cover;
}
