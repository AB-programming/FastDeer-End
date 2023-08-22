package com.deer.fastdeerend.service;

import com.deer.fastdeerend.domain.vo.post.PostVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    public List<PostVo> selectPostWithUserAndSocialStatsOrderByDate(String userId, Long current, Long size);

    public String publishPost(String date, String title, String text, String userId);

    public Boolean uploadPostFile(MultipartFile file, String postId, Integer idx);

    public List<PostVo> selectPostByUserId(String userId);

    public Boolean deletePostByPostId(String postId);

    public Boolean incrementBrowser(String postId);

    public Boolean incrementPostLike(String postId, String userId);

    public Boolean decrementPostLike(String postId, String userId);

    public Boolean incrementPostBookmark(String postId, String userId);

    public Boolean decrementPostBookmark(String postId, String userId);

    public Long selectPostLikeCountByUserId(String userId);

    public Long selectPostBookmarkCountByUserId(String userId);
}
