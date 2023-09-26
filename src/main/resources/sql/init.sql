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
    `post_id`       varchar(255) primary key,
    `date`          char(50)     not null,
    `urls`          varchar(255),
    `text`          varchar(255),
    `title`         varchar(255) not null,
    `browser_count` bigint DEFAULT 0,
    `user_id`       varchar(255) not null,
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
    `post_id`      varchar(255) not null,
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
    `date`         char(50)                  not null,
    `content_text` varchar(255)              not null,
    `comment_id`   varchar(255),
    `target_id`    varchar(255),
    `user_id`      varchar(255),
    `reply_type`   enum ('COMMENT', 'REPLY') not null,
    CONSTRAINT `fk_reply_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS post_like;

CREATE TABLE `post_like`
(
    `post_like_id` varchar(255) primary key,
    `user_id`      varchar(255) not null,
    `post_id`      varchar(255) not null,
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
    `post_id`          varchar(255) not null,
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
    `comment_id`      varchar(255) not null,
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
    `reply_id`      varchar(255) not null,
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

DROP TABLE IF EXISTS academic;

CREATE TABLE `academic`
(
    `academic_id` varchar(255) primary key,
    `user_id`     varchar(255) not null,
    `date`        char(50)     not null,
    `title`       varchar(255) not null,
    `content`     TEXT,
    `cover`       varchar(255),
    CONSTRAINT `fk_academic_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS academic_comment;

CREATE TABLE `academic_comment`
(
    `academic_comment_id` varchar(255) primary key,
    `user_id`             varchar(255) not null,
    `academic_id`         varchar(255) not null,
    `date`                char(50)     not null,
    `content`             varchar(255) not null,
    CONSTRAINT `fk_academic_comment_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_academic_comment_academic` FOREIGN KEY (academic_id) REFERENCES academic (academic_id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS feedback;

CREATE TABLE `feedback`
(
    `feedback_id` varchar(255) primary key,
    `user_id`     varchar(255) not null,
    `date`        char(50)     not null,
    `tag`         char(4)      not null,
    `rate`        char(2)      not null,
    `content`     varchar(255),
    `phone`       char(20)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS resource;

CREATE TABLE `resource`
(
    `resource_id` varchar(255) primary key,
    `user_id`     varchar(255) not null,
    `date`        char(50)     not null,
    `description` varchar(255),
    `url`         varchar(255) not null,
    `file_name`   char(100)    not null,
    `extension`   char(10)     not null,
    CONSTRAINT `fk_resource_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS school;

CREATE TABLE `school`
(
    `school_id` varchar(255) primary key,
    `user_id`   varchar(255) not null,
    `password`  varchar(255) not null,
    CONSTRAINT `fk_school_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS event;

CREATE TABLE `event`
(
    `event_id` varchar(255) primary key,
    `user_id`  varchar(255) not null,
    `date`     char(50)     not null,
    `title`    varchar(255) not null,
    `url`      varchar(255) not null,
    `cover`    varchar(255) not null,
    CONSTRAINT `fk_event_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS job;

CREATE TABLE `job`
(
    `job_id`      varchar(255) primary key,
    `job_name`    varchar(255) not null,
    `user_id`     varchar(255) not null,
    `degree`      char(50),
    `salary`      char(50),
    `description` text,
    `company`     char(64),
    `date`        char(50)     not null,
    `deadline`    char(50),
    `contact`     char(64),
    CONSTRAINT `fk_job_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS volunteer;

CREATE TABLE `volunteer`
(
    `volunteer_id` varchar(255) primary key,
    `user_id`      varchar(255) not null,
    `title`        varchar(255) not null,
    `description`  text,
    `date`         char(50)     not null,
    `deadline`     char(50),
    CONSTRAINT `fk_volunteer_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS volunteer_registration;

CREATE TABLE `volunteer_registration`
(
    `volunteer_registration_id` varchar(255) primary key,
    `user_id`                   varchar(255) not null,
    `volunteer_id`              varchar(255) not null,
    CONSTRAINT `fk_volunteer_registration_user` FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_volunteer_registration_volunteer` FOREIGN KEY (volunteer_id) REFERENCES volunteer(volunteer_id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;