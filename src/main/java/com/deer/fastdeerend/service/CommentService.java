package com.deer.fastdeerend.service;

import com.deer.fastdeerend.domain.bo.CommentBo;
import com.deer.fastdeerend.domain.vo.comment.CommentVo;

import java.util.List;

public interface CommentService {
    public List<CommentVo> selectAllCommentByPostId(String postId, String userId);

    public CommentBo insertComment(String date, String contentText, String userId, String postId);

    public Boolean incrementCommentLike(String commentId, String userId);

    public Boolean decrementCommentLike(String commentId, String userId);

    public Long selectCommentCountByUserId(String userId);
}
