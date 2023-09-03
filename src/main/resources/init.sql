use
fast_deer;

DROP TABLE IF EXISTS user;

CREATE TABLE `user`
(
    `id`              varchar(255) primary key,
    `nick_name`       varchar(255),
    `avatar_url`      varchar(255),
    `gender`          varchar(10),
    `place`           varchar(100),
    `birth`           varchar(30),
    `school`          varchar(30),
    `major`           varchar(40),
    `qualification`   char(20),
    `graduation_date` char(40),
    `role`            varchar(30) not null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into `user`(id, role)
values ('0', 'ROLE_admin');

DROP TABLE IF EXISTS post;

CREATE TABLE `post`
(
    `post_id` varchar(255) primary key,
    `date`    char(50)     not null,
    `urls`    varchar(255),
    #         Pictures of post content,
    not       required
        `text` varchar (255),
    #         Post content
        `title` varchar (255) not null,
    #         Post title
        `browser_count` bigint DEFAULT 0,
    `user_id` varchar(255) not null,
    # foreign key for user table
        CONSTRAINT `fk_post_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE on UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS comment;

CREATE TABLE `comment`
(
    `comment_id`   varchar(255) primary key,
    `date`         char(50)     not null,
    `content_text` varchar(255) not null,
    `user_id`      varchar(255) not null,
    #              commenter
        `post_id` varchar (255) not null,
    CONSTRAINT `fk_comment_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_comment_post` FOREIGN KEY (post_id) REFERENCES post (post_id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS reply;

CREATE TABLE `reply`
(
    `reply_id`     varchar(255) primary key,
    `date`         char(50)     not null,
    `content_text` varchar(255) not null,
    `comment_id`   varchar(255),
    #              Redundant exchange performance
        `target_id` varchar (255),
    `user_id`      varchar(255),
    #              replier
        `reply_type` enum ('COMMENT', 'REPLY') not null,
    CONSTRAINT `fk_reply_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS post_like;

CREATE TABLE `post_like`
(
    `post_like_id` varchar(255) primary key,
    `user_id`      varchar(255) not null,
    #              Liker
        `post_id` varchar (255) not null,
    #              Liked post
        CONSTRAINT `fk_post_like_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_post_like_comment` FOREIGN KEY (post_id) REFERENCES post (post_id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS post_bookmark;

CREATE TABLE `post_bookmark`
(
    `post_bookmark_id` varchar(255) primary key,
    `user_id`          varchar(255) not null,
    #                  BookMarker
        `post_id` varchar (255) not null,
    #                  BookMarked post
        CONSTRAINT `fk_post_bookmark_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_post_bookmark_` FOREIGN KEY (post_id) REFERENCES post (post_id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS comment_like;

CREATE TABLE `comment_like`
(
    `comment_like_id` varchar(255) primary key,
    `user_id`         varchar(255) not null,
    #                 Liker
        `comment_id` varchar (255) not null,
    #                 Liked comment
        CONSTRAINT `fk_comment_like_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_comment_like_comment` FOREIGN KEY (comment_id) REFERENCES comment (comment_id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS reply_like;

CREATE TABLE `reply_like`
(
    `reply_like_id` varchar(255) primary key,
    `user_id`       varchar(255) not null,
    #               Liker
        `reply_id` varchar (255) not null,
    #               Liked reply
        CONSTRAINT `fk_reply_like_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_reply_like_comment` FOREIGN KEY (reply_id) REFERENCES reply (reply_id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS user_relate;

CREATE TABLE `user_relate`
(
    `user_relate_id` varchar(255) primary key,
    `user_id`        varchar(255) not null,
    `target`         varchar(255) not null,
    CONSTRAINT `fk_user_relate_user_from` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_user_relate_user_to` FOREIGN KEY (target) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
