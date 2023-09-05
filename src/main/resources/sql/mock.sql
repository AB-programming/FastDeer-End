use fast_deer;

INSERT INTO user (id, nick_name, avatar_url, gender, place, birth, school, major, qualification, graduation_date, role)
VALUES ('1', '麋鹿', 'http://192.168.128.139:8081/static/avatar/1.jpg', '女', '台湾省-台北市-板桥市', '1999-07-15',
        '电子科技大学',
        '计算机科学与技术', '硕士', '2024-06-11', 'ROLE_user');

insert into user(id, nick_name, avatar_url, gender, place, birth, school, major, qualification, graduation_date, role)
VALUES ('2', '张三', 'http://192.168.128.139:8081/static/logo.jpg', '男', '辽宁省-大连市-西岗区', '2003-05-20',
        '南京大学',
        '应用数学', '本科', '2025-09-05', 'ROLE_user');

INSERT INTO user (id, nick_name, avatar_url, gender, place, birth, school, major, qualification, graduation_date, role)
VALUES ('3', '李四', 'http://192.168.128.139:8081/static/logo.jpg', '女', '台湾省-台北市-板桥市', '1999-07-15',
        '电子科技大学',
        '计算机科学与技术', '硕士', '2024-06-11', 'ROLE_user');



INSERT INTO user (id, nick_name, avatar_url, gender, place, birth, school, major, qualification, graduation_date, role)

VALUES ('4', '王五', 'http://192.168.128.139:8081/static/logo.jpg', '男', '江苏省-南京市-玄武区', '2007-10-13',
        '华中科技大学',
        '土木工程', '本科', '2026-02-28', 'ROLE_user');



INSERT INTO user (id, nick_name, avatar_url, gender, place, birth, school, major, qualification, graduation_date, role)

VALUES ('5', '赵六', 'http://192.168.128.139:8081/static/logo.jpg', '女', '香港特别行政区-香港市-中西区', '2005-03-02',
        '同济大学',
        '新闻传播学', '博士研究生', '2024-12-09', 'ROLE_user');



INSERT INTO user (id, nick_name, avatar_url, gender, place, birth, school, major, qualification, graduation_date, role)

VALUES ('6', '孙七', 'http://192.168.128.139:8081/static/logo.jpg', '男', '四川省-成都市-武侯区', '2006-11-18',
        '大连理工大学',
        '金融学', '本科', '2026-04-25', 'ROLE_user');

# post
insert into post(post_id, date, urls, text, title, browser_count, user_id)
values ('1', '2000-05-11 10:30:07',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-1.jpg',
        '黄河之水天上来，奔流到海不复回', '将进酒', 3, '1');

INSERT INTO post (post_id, date, urls, text, title, browser_count, user_id)
VALUES ('2', '2023-08-16 15:45:22',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-2.jpg',
        '青山依旧在，几度夕阳红', '归去来兮', 5, '2');

INSERT INTO post (post_id, date, urls, text, title, browser_count, user_id)
VALUES ('3', '2023-08-15 09:12:56',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-3.jpg',
        '明月几时有，把酒问青天', '静夜思', 8, '3');

INSERT INTO post (post_id, date, urls, text, title, browser_count, user_id)
VALUES ('4', '2023-08-14 14:30:10',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-4.jpg',
        '床前明月光，疑是地上霜', '青玉案·元夕', 10, '4');

INSERT INTO post (post_id, date, urls, text, title, browser_count, user_id)
VALUES ('5', '2023-08-13 19:08:42',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-5.jpg',
        '风萧萧兮易水寒，壮士一去兮不复还', '出塞', 7, '5');

INSERT INTO post (post_id, date, urls, text, title, browser_count, user_id)
VALUES ('6', '2023-08-12 08:56:33',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-6.jpg',
        '千山鸟飞绝，万径人踪灭', '秋夕', 6, '6');

INSERT INTO post (post_id, date, urls, text, title, browser_count, user_id)
VALUES ('7', '2023-08-11 12:20:18',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-7.jpg',
        '问君能有几多愁，恰似一江春水向东流', '长恨歌', 9, '1');

INSERT INTO post (post_id, date, urls, text, title, browser_count, user_id)
VALUES ('8', '2023-08-10 17:05:09',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-8.jpg',
        '举杯邀明月，对影成三人', '青阑干·明月几时有', 11, '2');

INSERT INTO post (post_id, date, urls, text, title, browser_count, user_id)
VALUES ('9', '2023-08-09 09:40:31',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-9.jpg',
        '桃花扇底江南水，翠梧鸭头夜夜愁', '花影怨', 6, '3');

INSERT INTO post (post_id, date, urls, text, title, browser_count, user_id)
VALUES ('10', '2023-08-08 20:15:04',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-10.jpg',
        '身无彩凤双飞翼，心有灵犀一点通', '无题·相见时难别亦难', 8, '4');

INSERT INTO post (post_id, date, urls, text, title, browser_count, user_id)
VALUES ('11', '2023-08-07 14:30:56',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-11.jpg',
        '江南好，风景旧曾谙', '浪淘沙·北戴河', 7, '5');

INSERT INTO post (post_id, date, urls, text, title, browser_count, user_id)
VALUES ('12', '2023-08-06 07:55:39',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-12.jpg',
        '生当作人杰，死亦为鬼雄', '滕王阁序', 10, '6');

INSERT INTO post (post_id, date, urls, text, title, browser_count, user_id)
VALUES ('13', '2023-08-05 18:20:22',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-13.jpg',
        '青山遮不住，毕竟东流去', '赠别二首·其一', 5, '1');

INSERT INTO post (post_id, date, urls, text, title, browser_count, user_id)
VALUES ('14', '2023-08-04 11:10:14',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-14.jpg',
        '人生如逆旅，我亦是行人', '再别康桥', 9, '2');

INSERT INTO post (post_id, date, urls, text, title, browser_count, user_id)
VALUES ('15', '2023-08-03 09:40:28',
        'http://192.168.128.139:8080/static/post/7f05f619-e773-4341-b6d3-eb0f93403248-15.jpg',
        '采菊东篱下，悠然见南山', '山行', 6, '3');

# post_like
insert into post_like(post_like_id, user_id, post_id)
VALUES ('1', '1', '1');

-- 插入语句1
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('2', '2', '2');

-- 插入语句2
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('3', '3', '3');

-- 插入语句3
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('4', '4', '4');

-- 插入语句4
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('5', '5', '5');

-- 插入语句5
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('6', '6', '6');

-- 插入语句6
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('7', '1', '7');

-- 插入语句7
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('8', '2', '8');

-- 插入语句8
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('9', '3', '9');

-- 插入语句9
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('10', '4', '10');

-- 插入语句10
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('11', '5', '1');

-- 插入语句11
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('12', '6', '2');

-- 插入语句12
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('13', '1', '3');

-- 插入语句13
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('14', '2', '4');

-- 插入语句14
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('15', '3', '5');

-- 插入语句15
INSERT INTO post_like(post_like_id, user_id, post_id)
VALUES ('16', '4', '6');


#relation
insert into user_relate(user_relate_id, user_id, target)
values ('1', '1', '2');

-- 插入语句1
INSERT INTO user_relate(user_relate_id, user_id, target)
VALUES ('2', '4', '3');

-- 插入语句2
INSERT INTO user_relate(user_relate_id, user_id, target)
VALUES ('3', '6', '5');

-- 插入语句3
INSERT INTO user_relate(user_relate_id, user_id, target)
VALUES ('4', '2', '1');

-- 插入语句4
INSERT INTO user_relate(user_relate_id, user_id, target)
VALUES ('5', '3', '2');

-- 插入语句5
INSERT INTO user_relate(user_relate_id, user_id, target)
VALUES ('6', '5', '4');

-- 插入语句6
INSERT INTO user_relate(user_relate_id, user_id, target)
VALUES ('7', '1', '6');

-- 插入语句7
INSERT INTO user_relate(user_relate_id, user_id, target)
VALUES ('8', '4', '1');

-- 插入语句8
INSERT INTO user_relate(user_relate_id, user_id, target)
VALUES ('9', '6', '3');

-- 插入语句9
INSERT INTO user_relate(user_relate_id, user_id, target)
VALUES ('10', '2', '5');

-- 插入语句10
INSERT INTO user_relate(user_relate_id, user_id, target)
VALUES ('11', '3', '1');

# post_bookmark

insert into post_bookmark(post_bookmark_id, user_id, post_id)
values ('1', '1', '1');

-- 插入语句1
INSERT INTO post_bookmark(post_bookmark_id, user_id, post_id)
VALUES ('2', '2', '2');

-- 插入语句2
INSERT INTO post_bookmark(post_bookmark_id, user_id, post_id)
VALUES ('3', '3', '3');

-- 插入语句3
INSERT INTO post_bookmark(post_bookmark_id, user_id, post_id)
VALUES ('4', '4', '4');

-- 插入语句4
INSERT INTO post_bookmark(post_bookmark_id, user_id, post_id)
VALUES ('5', '5', '5');

-- 插入语句5
INSERT INTO post_bookmark(post_bookmark_id, user_id, post_id)
VALUES ('6', '6', '6');

-- 插入语句6
INSERT INTO post_bookmark(post_bookmark_id, user_id, post_id)
VALUES ('7', '1', '7');

-- 插入语句7
INSERT INTO post_bookmark(post_bookmark_id, user_id, post_id)
VALUES ('8', '2', '8');

-- 插入语句8
INSERT INTO post_bookmark(post_bookmark_id, user_id, post_id)
VALUES ('9', '3', '9');

-- 插入语句9
INSERT INTO post_bookmark(post_bookmark_id, user_id, post_id)
VALUES ('10', '4', '10');

-- 插入语句10
INSERT INTO post_bookmark(post_bookmark_id, user_id, post_id)
VALUES ('11', '5', '1');

-- 插入语句11
INSERT INTO post_bookmark(post_bookmark_id, user_id, post_id)
VALUES ('12', '6', '2');

-- 插入语句12
INSERT INTO post_bookmark(post_bookmark_id, user_id, post_id)
VALUES ('13', '1', '3');

-- 插入语句13
INSERT INTO post_bookmark(post_bookmark_id, user_id, post_id)
VALUES ('14', '2', '4');

-- 插入语句14
INSERT INTO post_bookmark(post_bookmark_id, user_id, post_id)
VALUES ('15', '3', '5');

# comment
insert into comment(comment_id, date, content_text, user_id, post_id)
VALUES ('1', '2003-03-29', '世界之大，无奇不有', '1', '2');

-- 插入语句1
INSERT INTO comment(comment_id, date, content_text, user_id, post_id)
VALUES ('2', '2010-07-15', '有意思的见解', '2', '3');

-- 插入语句2
INSERT INTO comment(comment_id, date, content_text, user_id, post_id)
VALUES ('3', '1977-09-11', '非常有思想，赞一个', '3', '4');

-- 插入语句3
INSERT INTO comment(comment_id, date, content_text, user_id, post_id)
VALUES ('4', '1995-04-08', '很有道理', '4', '5');

-- 插入语句4
INSERT INTO comment(comment_id, date, content_text, user_id, post_id)
VALUES ('5', '2003-09-06', '高见！', '5', '6');

-- 插入语句5
INSERT INTO comment(comment_id, date, content_text, user_id, post_id)
VALUES ('6', '2014-11-24', '读来受益匪浅', '6', '1');

-- 插入语句6
INSERT INTO comment(comment_id, date, content_text, user_id, post_id)
VALUES ('7', '1983-02-03', '顶！', '1', '2');

-- 插入语句8
INSERT INTO comment(comment_id, date, content_text, user_id, post_id)
VALUES ('9', '2012-03-14', '很独到的观点，喜欢！', '2', '4');

-- 插入语句10
INSERT INTO comment(comment_id, date, content_text, user_id, post_id)
VALUES ('11', '1978-05-18', '非常有见地，支持！', '3', '5');

-- 插入语句12
INSERT INTO comment(comment_id, date, content_text, user_id, post_id)
VALUES ('13', '2002-08-17', '转了，说得太对了！', '4', '6');

-- 插入语句14
INSERT INTO comment(comment_id, date, content_text, user_id, post_id)
VALUES ('15', '1993-06-23', '同意楼上观点！', '5', '1');

# reply

insert into reply(reply_id, date, content_text, comment_id, target_id, user_id, reply_type)
VALUES ('1', '2022-09-08', '你说的不对', '1', null, '1', 'COMMENT');

-- 插入语句1
INSERT INTO reply(reply_id, date, content_text, comment_id, target_id, user_id, reply_type)
VALUES ('2', '2021-05-13', '同意你的观点', '2', null, '2', 'COMMENT');

-- 插入语句3
INSERT INTO reply(reply_id, date, content_text, comment_id, target_id, user_id, reply_type)
VALUES ('3', '2018-03-24', '我觉得你说的对', '3', null, '3', 'COMMENT');

-- 插入语句5
INSERT INTO reply(reply_id, date, content_text, comment_id, target_id, user_id, reply_type)
VALUES ('4', '2001-12-04', '你说的都对', '4', null, '4', 'COMMENT');

-- 插入语句7
INSERT INTO reply(reply_id, date, content_text, comment_id, target_id, user_id, reply_type)
VALUES ('5', '1995-10-14', '我反对', '5', null, '5', 'COMMENT');

-- 插入语句9
INSERT INTO reply(reply_id, date, content_text, comment_id, target_id, user_id, reply_type)
VALUES ('6', '1984-06-27', '我也反对', '6', null, '6', 'COMMENT');

-- 插入语句11
INSERT INTO reply(reply_id, date, content_text, comment_id, target_id, user_id, reply_type)
VALUES ('7', '1977-03-17', '支持楼上观点', null, '1', '1', 'REPLY');

-- 插入语句13
INSERT INTO reply(reply_id, date, content_text, comment_id, target_id, user_id, reply_type)
VALUES ('8', '1971-09-25', '我同意这个观点', null, '2', '2', 'REPLY');

-- 插入语句15
INSERT INTO reply(reply_id, date, content_text, comment_id, target_id, user_id, reply_type)
VALUES ('9', '1964-04-23', '顶一个', null, '3', '3', 'REPLY');

-- 插入语句17
INSERT INTO reply(reply_id, date, content_text, comment_id, target_id, user_id, reply_type)
VALUES ('10', '1956-01-12', '楼上说的对', null, '4', '4', 'REPLY');

-- 插入语句19
INSERT INTO reply(reply_id, date, content_text, comment_id, target_id, user_id, reply_type)
VALUES ('11', '1948-10-09', '我也同意楼上的观点！', null, '5', '5', 'REPLY');

-- 插入语句21
INSERT INTO reply(reply_id, date, content_text, comment_id, target_id, user_id, reply_type)
VALUES ('12', '1940-06-06', '我反对楼上的观点，我认为...', null, '6', '6', 'REPLY');

-- 插入语句23
INSERT INTO reply(reply_id, date, content_text, comment_id, target_id, user_id, reply_type)
VALUES ('13', '1928-10-30', '我反对楼上的观点，我认为...', null, '1', '1', 'REPLY');

# comment_like
insert into comment_like(comment_like_id, user_id, comment_id)
VALUES ('1', '1', '2');

-- 插入语句1
INSERT INTO comment_like(comment_like_id, user_id, comment_id)
VALUES ('2', '2', '3');

-- 插入语句2
INSERT INTO comment_like(comment_like_id, user_id, comment_id)
VALUES ('3', '3', '4');

-- 插入语句4
INSERT INTO comment_like(comment_like_id, user_id, comment_id)
VALUES ('4', '4', '5');

-- 插入语句6
INSERT INTO comment_like(comment_like_id, user_id, comment_id)
VALUES ('5', '5', '6');

-- 插入语句8
INSERT INTO comment_like(comment_like_id, user_id, comment_id)
VALUES ('6', '6', '1');

-- 插入语句10
INSERT INTO comment_like(comment_like_id, user_id, comment_id)
VALUES ('7', '1', '2');

-- 插入语句12
INSERT INTO comment_like(comment_like_id, user_id, comment_id)
VALUES ('8', '2', '4');

-- 插入语句14
INSERT INTO comment_like(comment_like_id, user_id, comment_id)
VALUES ('9', '3', '5');

# reply_like
insert into reply_like(reply_like_id, user_id, reply_id)
VALUES ('1', '1', '2');

-- 插入语句1
INSERT INTO reply_like(reply_like_id, user_id, reply_id)
VALUES ('2', '2', '3');

-- 插入语句3
INSERT INTO reply_like(reply_like_id, user_id, reply_id)
VALUES ('3', '3', '4');

-- 插入语句5
INSERT INTO reply_like(reply_like_id, user_id, reply_id)
VALUES ('4', '4', '5');

-- 插入语句7
INSERT INTO reply_like(reply_like_id, user_id, reply_id)
VALUES ('5', '5', '6');

-- 插入语句9
INSERT INTO reply_like(reply_like_id, user_id, reply_id)
VALUES ('6', '6', '1');

-- 插入语句11
INSERT INTO reply_like(reply_like_id, user_id, reply_id)
VALUES ('7', '1', '2');

-- 插入语句13
INSERT INTO reply_like(reply_like_id, user_id, reply_id)
VALUES ('8', '2', '4');

-- 插入语句15
INSERT INTO reply_like(reply_like_id, user_id, reply_id)
VALUES ('9', '3', '5');