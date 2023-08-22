package com.deer.fastdeerend.service;

import com.deer.fastdeerend.domain.bo.ReplyBo;
import com.deer.fastdeerend.domain.entity.reply.ReplyType;

public interface ReplyService {
    public ReplyBo insertReply(String date, String contentText, String target, String userId, ReplyType replyType);

    public Boolean incrementReplyLike(String replyId, String userId);

    public Boolean decrementReplyLike(String replyId, String userId);
}
