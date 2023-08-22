package com.deer.fastdeerend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deer.fastdeerend.dao.reply.ReplyLikeMapper;
import com.deer.fastdeerend.dao.reply.ReplyMapper;
import com.deer.fastdeerend.domain.bo.ReplyBo;
import com.deer.fastdeerend.domain.entity.reply.Reply;
import com.deer.fastdeerend.domain.entity.reply.ReplyLike;
import com.deer.fastdeerend.domain.entity.reply.ReplyType;
import com.deer.fastdeerend.service.ReplyService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Resource
    private ReplyMapper replyMapper;

    @Resource
    private ReplyLikeMapper replyLikeMapper;

    @Override
    public ReplyBo insertReply(String date, String contentText, String target, String userId, ReplyType replyType) {
        String replyId = UUID.randomUUID().toString();
        return ReplyBo.builder()
                .replyId(replyId)
                .status(ReplyType.COMMENT == replyType ?
                        replyMapper.insert(Reply.builder()
                                .replyId(replyId)
                                .date(date)
                                .contentText(contentText)
                                .commentId(target)
                                .userId(userId)
                                .replyType(replyType)
                                .build()) > 0 :
                        replyMapper.insert(Reply.builder()
                                .replyId(replyId)
                                .date(date)
                                .contentText(contentText)
                                .targetId(target)
                                .userId(userId)
                                .replyType(replyType)
                                .build()) > 0)
                .build();
    }

    @Override
    public Boolean incrementReplyLike(String replyId, String userId) {
        return replyLikeMapper.insert(ReplyLike.builder()
                .replyLikeId(UUID.randomUUID().toString())
                .replyId(replyId)
                .userId(userId)
                .build()) > 0;
    }

    @Override
    public Boolean decrementReplyLike(String replyId, String userId) {
        return replyLikeMapper.delete(new QueryWrapper<ReplyLike>()
                .eq("reply_id", replyId)
                .and(query -> query.eq("user_id", userId))) > 0;
    }
}
