package com.deer.fastdeerend.domain.entity.feedback;

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
public class Feedback implements Serializable {
    @Serial
    private static final long serialVersionUID = 1595545371231866164L;

    @TableId
    private String feedbackId;
    private String userId;
    private String date;
    private String tag;
    private String rate;
    private String content;
    private String phone;
}
