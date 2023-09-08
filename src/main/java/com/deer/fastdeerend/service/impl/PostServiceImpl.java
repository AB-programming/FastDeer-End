package com.deer.fastdeerend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deer.fastdeerend.dao.comment.CommentMapper;
import com.deer.fastdeerend.dao.post.PostBookmarkMapper;
import com.deer.fastdeerend.dao.post.PostLikeMapper;
import com.deer.fastdeerend.dao.post.PostMapper;
import com.deer.fastdeerend.dao.user.UserMapper;
import com.deer.fastdeerend.dao.user.UserRelateMapper;
import com.deer.fastdeerend.domain.entity.comment.Comment;
import com.deer.fastdeerend.domain.entity.post.Post;
import com.deer.fastdeerend.domain.entity.post.PostBookmark;
import com.deer.fastdeerend.domain.entity.post.PostLike;
import com.deer.fastdeerend.domain.entity.user.User;
import com.deer.fastdeerend.domain.entity.user.UserRelate;
import com.deer.fastdeerend.domain.vo.post.PostVo;
import com.deer.fastdeerend.domain.vo.post.content.Content;
import com.deer.fastdeerend.domain.vo.post.social.SocialStats;
import com.deer.fastdeerend.domain.vo.post.userinfo.UserInfo;
import com.deer.fastdeerend.domain.vo.post.userinfo.base.BaseInfo;
import com.deer.fastdeerend.domain.vo.post.userinfo.school.SchoolInfo;
import com.deer.fastdeerend.domain.vo.post.userinfo.social.SocialInfo;
import com.deer.fastdeerend.service.PostService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 帖子模块业务层处理
 *
 * @author AB-style
 * @date 2023/08/12
 */
@Service
public class PostServiceImpl implements PostService {
    @Value("${dev.postUpload.uploadLocation}")
    private String uploadLocation;

    @Value("${dev.postUpload.postUrl}")
    private String postUrl;

    @Resource
    private PostMapper postMapper;

    @Resource
    private PostLikeMapper postLikeMapper;

    @Resource
    private PostBookmarkMapper postBookmarkMapper;

    @Resource
    private UserRelateMapper userRelateMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<PostVo> selectPostWithUserAndSocialStatsOrderByDate(String userId, Long current, Long size) {
        List<Post> posts = postMapper.selectPostWithUserPageOrderByDate(new Page<>(current, size));
        List<PostVo> res = new ArrayList<>();
        posts.forEach(post -> {
            PostVo.PostVoBuilder builder = PostVo.builder();
            User user = userMapper.selectById(post.getUserId());
            Long likeCount = postLikeMapper.selectCount(new QueryWrapper<PostLike>()
                    .eq("post_id", post.getPostId()));
            Long bookmarkCount = postBookmarkMapper.selectCount(new QueryWrapper<PostBookmark>()
                    .eq("post_id", post.getPostId()));
            boolean isLike = false;
            boolean isBookmark = false;
            if (null != userId) {
                isLike = postLikeMapper.exists(new QueryWrapper<PostLike>()
                        .eq("user_id", userId)
                        .and(query -> query.eq("post_id", post.getPostId())));
                isBookmark = postBookmarkMapper.exists(new QueryWrapper<PostBookmark>()
                        .eq("user_id", userId)
                        .and(query -> query.eq("post_id", post.getPostId())));
            }

            Long fans = userRelateMapper.selectCount(new QueryWrapper<UserRelate>()
                    .eq("target", post.getUserId()));

            Long following = userRelateMapper.selectCount(new QueryWrapper<UserRelate>()
                    .eq("user_id", post.getUserId()));

            List<Post> userPosts = postMapper.selectList(new QueryWrapper<Post>()
                    .eq("user_id", user.getId()));
            AtomicReference<Long> likes = new AtomicReference<>(0L);
            userPosts.forEach(userPost -> {
                likes.updateAndGet(v -> v + postLikeMapper.selectCount(new QueryWrapper<PostLike>()
                        .eq("post_id", userPost.getPostId())));
            });

            Content content = Content.builder().urls(Arrays.asList(post.getUrls().split(";")))
                    .text(post.getText())
                    .title(post.getTitle()).build();

            UserInfo userInfo = UserInfo.builder()
                    .userId(user.getId())
                    .name(user.getNickName())
                    .avatar(user.getAvatarUrl())
                    .role(user.getRole())
                    .socialInfo(SocialInfo.builder()
                            .likes(likes.get())
                            .fans(fans)
                            .following(following)
                            .build())
                    .baseInfo(BaseInfo.builder()
                            .gender(user.getGender())
                            .address(user.getPlace())
                            .build())
                    .schoolInfo(SchoolInfo.builder()
                            .schoolName(user.getSchool())
                            .major(user.getMajor())
                            .degree(user.getQualification())
                            .graduation(user.getGraduationDate())
                            .build())
                    .build();

            PostVo postVo = builder.postId(post.getPostId())
                    .date(post.getDate())
                    .content(content)
                    .userInfo(userInfo)
                    .socialStats(SocialStats.builder()
                            .browserCount(post.getBrowserCount())
                            .likeCount(likeCount)
                            .bookmarkCount(bookmarkCount)
                            .isLike(isLike)
                            .isBookmark(isBookmark)
                            .build())
                    .commentCount(commentMapper.selectCount(new QueryWrapper<Comment>()
                            .eq("post_id", post.getPostId())))
                    .build();
            res.add(postVo);
        });
        return res;
    }

    @Override
    public String publishPost(String date, String title, String text, String userId) {
        String postId = UUID.randomUUID().toString();
        Post.PostBuilder builder = Post.builder();
        Post post = builder.postId(postId)
                .userId(userId)
                .browserCount(0L)
                .date(date)
                .urls("")
                .title(title)
                .text(text)
                .build();
        return postMapper.insert(post) > 0 ? postId : "";
    }

    @Override
    @Transactional
    public Boolean uploadPostFile(MultipartFile file, String postId, Integer idx) {
        String fileType = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String loadPosition = uploadLocation + postId + '-' + idx + '.' + fileType;

        Post post = postMapper.selectById(postId);

        if (StringUtils.hasText(post.getUrls())) {
            post.setUrls(post.getUrls() + ';');
        }

        post.setUrls(post.getUrls() + postUrl + postId + '-' + idx + '.' + fileType);

        try {
            file.transferTo(new File(loadPosition));
        } catch (IOException e) {
            return false;
        }

        return postMapper.updateById(post) > 0;
    }

    @Override
    public List<PostVo> selectPostByUserId(String userId) {
        List<Post> posts = postMapper.selectList(new QueryWrapper<Post>()
                .eq("user_id", userId));

        List<PostVo> res = new ArrayList<>();
        User user = userMapper.selectById(userId);

        UserInfo.UserInfoBuilder userInfoBuilder = UserInfo.builder();
        Long fans = userRelateMapper.selectCount(new QueryWrapper<UserRelate>()
                .eq("target", userId));

        Long following = userRelateMapper.selectCount(new QueryWrapper<UserRelate>()
                .eq("user_id", userId));

        List<Post> userPosts = postMapper.selectList(new QueryWrapper<Post>()
                .eq("user_id", userId));
        AtomicReference<Long> likes = new AtomicReference<>(0L);
        userPosts.forEach(userPost -> {
            likes.updateAndGet(v -> v + postLikeMapper.selectCount(new QueryWrapper<PostLike>()
                    .eq("post_id", userPost.getPostId())));
        });

        UserInfo userInfo = userInfoBuilder
                .userId(userId)
                .name(user.getNickName())
                .avatar(user.getAvatarUrl())
                .role(user.getRole())
                .baseInfo(BaseInfo.builder()
                        .gender(user.getGender())
                        .address(user.getPlace())
                        .build())
                .schoolInfo(SchoolInfo.builder()
                        .schoolName(user.getSchool())
                        .major(user.getMajor())
                        .degree(user.getQualification())
                        .graduation(user.getGraduationDate())
                        .build())
                .socialInfo(SocialInfo.builder()
                        .likes(likes.get())
                        .fans(fans)
                        .following(following)
                        .build())
                .build();
        posts.forEach(post -> {
            PostVo.PostVoBuilder builder = PostVo.builder();
            Long likeCount = postLikeMapper.selectCount(new QueryWrapper<PostLike>()
                    .eq("post_id", post.getPostId()));
            Long bookmarkCount = postBookmarkMapper.selectCount(new QueryWrapper<PostBookmark>()
                    .eq("post_id", post.getPostId()));
            boolean isLike = postLikeMapper.exists(new QueryWrapper<PostLike>()
                    .eq("user_id", userId)
                    .and(query -> query.eq("post_id", post.getPostId())));
            boolean isBookmark = postBookmarkMapper.exists(new QueryWrapper<PostBookmark>()
                    .eq("user_id", userId)
                    .and(query -> query.eq("post_id", post.getPostId())));

            res.add(builder.postId(post.getPostId())
                    .date(post.getDate())
                    .content(Content.builder().urls(Arrays.asList(post.getUrls().split(";")))
                            .text(post.getText())
                            .title(post.getTitle()).build())
                    .userInfo(userInfo)
                    .socialStats(SocialStats.builder()
                            .browserCount(post.getBrowserCount())
                            .likeCount(likeCount)
                            .bookmarkCount(bookmarkCount)
                            .isLike(isLike)
                            .isBookmark(isBookmark)
                            .build())
                    .build());
        });
        return res;
    }

    @Override
    public Boolean deletePostByPostId(String postId) {
        String[] urls = postMapper.selectById(postId).getUrls().split(";");
        for (String url : urls) {
            if (StringUtils.hasText(url)) {
                String filename = StringUtils.getFilename(url);
                FileSystemUtils.deleteRecursively(new File(uploadLocation + filename));
            }
        }
        return postMapper.deleteById(postId) > 0;
    }

    @Override
    @Transactional
    public Boolean incrementBrowser(String postId) {
        Post post = postMapper.selectById(postId);
        post.setBrowserCount(post.getBrowserCount() + 1);
        return postMapper.updateById(post) > 0;
    }

    @Override
    public Boolean incrementPostLike(String postId, String userId) {
        return postLikeMapper.insert(PostLike.builder()
                .postLikeId(UUID.randomUUID().toString())
                .postId(postId)
                .userId(userId)
                .build()) > 0;
    }

    @Override
    public Boolean decrementPostLike(String postId, String userId) {
        return postLikeMapper.delete(new QueryWrapper<PostLike>()
                .eq("user_id", userId)
                .and(query -> query.eq("post_id", postId))) > 0;
    }

    @Override
    public Boolean incrementPostBookmark(String postId, String userId) {
        return postBookmarkMapper.insert(PostBookmark.builder()
                .PostBookmarkId(UUID.randomUUID().toString())
                .postId(postId)
                .userId(userId)
                .build()) > 0;
    }

    @Override
    public Boolean decrementPostBookmark(String postId, String userId) {
        return postBookmarkMapper.delete(new QueryWrapper<PostBookmark>()
                .eq("user_id", userId)
                .and(query -> query.eq("post_id", postId))) > 0;
    }

    @Override
    public Long selectPostLikeCountByUserId(String userId) {
        return postLikeMapper.selectCount(new QueryWrapper<PostLike>()
                .eq("user_id", userId));
    }

    @Override
    public Long selectPostBookmarkCountByUserId(String userId) {
        return postBookmarkMapper.selectCount(new QueryWrapper<PostBookmark>()
                .eq("user_id", userId));
    }
}
