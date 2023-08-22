package com.deer.fastdeerend.domain.vo.post.content;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content implements Serializable {
    @Serial
    private static final long serialVersionUID = 9071029234716309668L;

    private List<String> urls;
    private String text;
    private String title;
}