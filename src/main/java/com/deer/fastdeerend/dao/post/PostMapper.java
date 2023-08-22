package com.deer.fastdeerend.dao.post;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deer.fastdeerend.domain.entity.post.Post;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface PostMapper extends BaseMapper<Post> {

    @Results(id = "postMap", value = {
            @Result(column = "user_id", property = "userId"),
            @Result(column = "user_id", property = "user",
            one = @One(select = "com.deer.fastdeerend.dao.user.UserMapper.selectById", fetchType = FetchType.LAZY))
    })

    @Select("SELECT post_id, date, urls, text, title, browser_count, user_id FROM post WHERE post_id = #{id}")
    Post selectPostById(String id);

    @Select("SELECT post_id, date, urls, text, title, browser_count, user_id FROM post ORDER BY date DESC")
    @ResultMap("postMap")
    List<Post> selectAllPostWithUserAndOrderByDate();

    @Select("SELECT post_id, date, urls, text, title, browser_count, user_id FROM post ORDER BY date DESC")
    @ResultMap("postMap")
    List<Post> selectPostWithUserPageOrderByDate(Page<Post> pagination);
}
