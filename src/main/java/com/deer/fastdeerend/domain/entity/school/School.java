package com.deer.fastdeerend.domain.entity.school;

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
public class School implements Serializable {
    @Serial
    private static final long serialVersionUID = -7353466208659706380L;
    @TableId
    private String schoolId;
    @TableField("user_id")
    private String userId;
    private String password;
}
