package com.deer.fastdeerend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.comment.CommentLikeMapper;
import com.deer.fastdeerend.dao.comment.CommentMapper;
import com.deer.fastdeerend.dao.reply.ReplyLikeMapper;
import com.deer.fastdeerend.dao.reply.ReplyMapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.domain.bo.CommentBo;
import com.deer.fastdeerend.domain.entity.comment.Comment;
import com.deer.fastdeerend.domain.entity.comment.CommentLike;
import com.deer.fastdeerend.domain.entity.reply.Reply;
import com.deer.fastdeerend.domain.entity.reply.ReplyLike;
import com.deer.fastdeerend.domain.entity.reply.ReplyType;
import com.deer.fastdeerend.domain.vo.comment.CommentVo;
import com.deer.fastdeerend.domain.vo.reply.ReplyVo;
import com.deer.fastdeerend.domain.vo.reply.SubReply;
import com.deer.fastdeerend.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private CommentLikeMapper commentLikeMapper;

    @Resource
    private ReplyMapper replyMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ReplyLikeMapper replyLikeMapper;

    @Override
    public List<CommentVo> selectAllCommentByPostId(String postId, String userId) {
        List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>()
                .eq("post_id", postId));

        List<CommentVo> res = new ArrayList<>();

        comments.forEach(comment -> {
            CommentVo.CommentVoBuilder builder = CommentVo.builder();

            Long commentLikeNum = commentLikeMapper.selectCount(new QueryWrapper<CommentLike>()
                    .eq("comment_id", comment.getCommentId()));

            boolean commentIsLike = false;
            if (null != userId) {
                commentIsLike = commentLikeMapper.exists(new QueryWrapper<CommentLike>()
                        .eq("comment_id", comment.getCommentId())
                        .and(query -> query.eq("user_id", userId)));
            }

            List<ReplyVo> replyList = new ArrayList<>();

            List<Reply> replies = replyMapper.selectList(new QueryWrapper<Reply>()
                    .eq("reply_type", ReplyType.COMMENT)
                    .and(query -> query.eq("comment_id", comment.getCommentId())));
            replies.forEach(reply -> {
                ReplyVo.ReplyVoBuilder replyVoBuilder = ReplyVo.builder();

                Long replyLikeNum = replyLikeMapper.selectCount(new QueryWrapper<ReplyLike>()
                        .eq("reply_id", reply.getReplyId()));

                boolean isLike = false;
                if (null != userId) {
                    isLike = replyLikeMapper.exists(new QueryWrapper<ReplyLike>()
                            .eq("user_id", userId)
                            .and(query -> query.eq("reply_id", reply.getReplyId())));
                }

                replyList.add(replyVoBuilder
                        .replyId(reply.getReplyId())
                        .name(userMapper.selectById(reply.getUserId()).getNickName())
                        .date(reply.getDate())
                        .contentText(reply.getContentText())
                        .url(userMapper.selectById(reply.getUserId()).getAvatarUrl())
                        .likeNum(replyLikeNum)
                        .isLike(isLike)
                        .reply(SubReply.builder()
                                .name(userMapper.selectById(comment.getUserId()).getNickName())
                                .contentStr(comment.getContentText())
                                .build())
                        .build());

                Stack<String> loopReplyIds = new Stack<>();
                loopReplyIds.push(reply.getReplyId());
                while (!loopReplyIds.isEmpty() &&
                        replyMapper.exists(new QueryWrapper<Reply>()
                                .eq("reply_type", ReplyType.REPLY)
                                .and(query -> query.eq("target_id", loopReplyIds.peek())))) {
                    String currentReplyId = loopReplyIds.pop();
                    List<Reply> loopReplies = replyMapper.selectList(new QueryWrapper<Reply>()
                            .eq("reply_type", ReplyType.REPLY)
                            .and(query -> query.eq("target_id", currentReplyId)));

                    loopReplies.forEach(loopReply -> {
                        loopReplyIds.push(loopReply.getReplyId());

                        boolean loopIsLike = false;
                        if (null != userId) {
                            loopIsLike = replyLikeMapper.exists(new QueryWrapper<ReplyLike>()
                                    .eq("user_id", userId)
                                    .and(query -> query.eq("reply_id", loopReply.getReplyId())));
                        }

                        replyList.add(replyVoBuilder
                                .replyId(loopReply.getReplyId())
                                .name(userMapper.selectById(loopReply.getUserId()).getNickName())
                                .date(loopReply.getDate())
                                .contentText(loopReply.getContentText())
                                .url(userMapper.selectById(loopReply.getUserId()).getAvatarUrl())
                                .likeNum(replyLikeMapper.selectCount(new QueryWrapper<ReplyLike>()
                                        .eq("reply_id", loopReply.getReplyId())))
                                .isLike(loopIsLike)
                                .reply(SubReply.builder()
                                        .name(userMapper
                                                .selectById(replyMapper
                                                        .selectById(currentReplyId)
                                                        .getUserId())
                                                .getNickName())
                                        .contentStr(replyMapper.selectById(currentReplyId).getContentText())
                                        .build())
                                .build());
                    });
                }
            });

            res.add(builder
                    .commentId(comment.getCommentId())
                    .name(userMapper.selectById(comment.getUserId()).getNickName())
                    .date(comment.getDate())
                    .contentText(comment.getContentText())
                    .url(userMapper.selectById(comment.getUserId()).getAvatarUrl())
                    .likeNum(commentLikeNum)
                    .isLike(commentIsLike)
                    .replyList(replyList)
                    .build());
        });
        return res;
    }

    @Override
    public CommentBo insertComment(String date, String contentText, String userId, String postId) {
        String commentId = UUID.randomUUID().toString();
        return CommentBo.builder()
                .status(commentMapper.insert(Comment.builder()
                        .commentId(commentId)
                        .date(date)
                        .contentText(contentText)
                        .userId(userId)
                        .postId(postId)
                        .build()) > 0)
                .commentId(commentId)
                .build();
    }

    @Override
    public Boolean incrementCommentLike(String commentId, String userId) {
        return commentLikeMapper.insert(CommentLike.builder()
                .commentLikeId(UUID.randomUUID().toString())
                .commentId(commentId)
                .userId(userId)
                .build()) > 0;
    }

    @Override
    public Boolean decrementCommentLike(String commentId, String userId) {
        return commentLikeMapper.delete(new QueryWrapper<CommentLike>()
                .eq("comment_id", commentId)
                .and(query -> query.eq("user_id", userId))) > 0;
    }

    @Override
    public Long selectCommentCountByUserId(String userId) {
        return commentMapper.selectCount(new QueryWrapper<Comment>()
                .eq("user_id", userId));
    }
}
