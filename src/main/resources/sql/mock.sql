use fast_deer;

INSERT INTO user (id, nick_name, avatar_url, gender, place, birth, school, major, qualification, graduation_date, role)
VALUES ('1', '麋鹿', 'http://192.168.128.139:8080/static/avatar/1.jpg', '女', '台湾省-台北市-板桥市', '1999-07-15',
        '电子科技大学',
        '计算机科学与技术', '硕士', '2024-06-11', 'ROLE_user');

insert into user(id, nick_name, avatar_url, gender, place, birth, school, major, qualification, graduation_date, role)
VALUES ('2', '张三', 'http://192.168.128.139:8080/static/logo.jpg', '男', '辽宁省-大连市-西岗区', '2003-05-20',
        '南京大学',
        '应用数学', '本科', '2025-09-05', 'ROLE_user');

INSERT INTO user (id, nick_name, avatar_url, gender, place, birth, school, major, qualification, graduation_date, role)
VALUES ('3', '李四', 'http://192.168.128.139:8080/static/logo.jpg', '女', '台湾省-台北市-板桥市', '1999-07-15',
        '电子科技大学',
        '计算机科学与技术', '硕士', '2024-06-11', 'ROLE_user');



INSERT INTO user (id, nick_name, avatar_url, gender, place, birth, school, major, qualification, graduation_date, role)

VALUES ('4', '王五', 'http://192.168.128.139:8080/static/logo.jpg', '男', '江苏省-南京市-玄武区', '2007-10-13',
        '华中科技大学',
        '土木工程', '本科', '2026-02-28', 'ROLE_user');



INSERT INTO user (id, nick_name, avatar_url, gender, place, birth, school, major, qualification, graduation_date, role)

VALUES ('5', '赵六', 'http://192.168.128.139:8080/static/logo.jpg', '女', '香港特别行政区-香港市-中西区', '2005-03-02',
        '同济大学',
        '新闻传播学', '博士研究生', '2024-12-09', 'ROLE_user');



INSERT INTO user (id, nick_name, avatar_url, gender, place, birth, school, major, qualification, graduation_date, role)

VALUES ('6', '孙七', 'http://192.168.128.139:8080/static/logo.jpg', '男', '四川省-成都市-武侯区', '2006-11-18',
        '大连理工大学',
        '金融学', '本科', '2026-04-25', 'ROLE_user');

INSERT INTO user (id, nick_name, avatar_url, gender, place, birth, school, major, qualification, graduation_date, role)

VALUES ('100', '山东交通学院', 'http://47.110.229.138:8080/static/avatar/100.jpg', '男', '', '',
        '',
        '', '', '', 'ROLE_school');

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

# academic
INSERT INTO fast_deer.academic (academic_id, user_id, date, title, content, cover)
VALUES ('1', '1', '2023-09-10 14:32:39', '"生活哲学：艺术与科学，缺一不可！"', '<p><strong>哲学：探索真理，追求智慧！</strong></p>

<p>哲学是人类对世界和生活的深入思考和探索，它旨在揭示真理和智慧，为人类提供意义和价值。从古至今，哲学一直在人类社会中占据着重要的地位。</p>

<p>哲学的领域广泛，包括形而上学、伦理学、政治哲学、认识论等。这些领域不断提出深刻的问题和思考，为人类认识自我、世界和社会提供了许多启示和洞见。</p>

<p>形而上学是研究存在和本质的哲学，它探索世界的本源和意义。伦理学则是研究道德价值和人类行为的哲学，它为人类提供了判断善恶和是非的标准。</p>

<p>政治哲学则是研究社会政治制度和价值观的哲学，它为建立公正、平等和和谐的社会提供了理论基础。</p>

<p>认识论是研究人类知识和认识的哲学，它探索人类认识的来源、可靠性和局限性。</p>

<p>哲学不仅仅是学术领域的学科，它还深刻影响着人类的思想和行为。通过深入思考和探讨，哲学帮助人类认识自己的本质和价值，为人类提供精神寄托和智慧指引。</p>

<p>尽管哲学提出了许多深刻的问题和挑战，但它也给予人类希望和力量。通过不断追求真理和智慧，哲学让我们认识到自己存在于一个有意义和价值的世界中。</p>

<p>让我们一起走近哲学，探索真理，追求智慧，为自己的人生找到更多的意义和价值！</p>',
        'https://fast-deer.oss-cn-beijing.aliyuncs.com/academic/3a3ea601-fdf8-41f6-961c-b367c65203dc.png');
INSERT INTO fast_deer.academic (academic_id, user_id, date, title, content, cover)
VALUES ('2', '2', '2023-09-10 14:32:29', '"技术奇迹：科学也有浪漫！"', '<p><strong>技术：改变世界，创新无限！</strong></p>

<p>技术是推动人类社会不断进步的关键因素，它不断创新和改变着我们的生活和工作方式。从古代的简单工具到现代的高科技设备，技术一直都在不断发展演变。</p>

<p>现代技术涵盖了许多领域，包括计算机科学、人工智能、机器人技术、通信技术等。这些领域的技术不断取得突破性进展，为人类带来了许多前所未有的应用和产品。</p>

<p>计算机科学是现代技术的重要支柱，它为我们提供了强大的计算机系统和应用软件。人工智能则模仿人类的智能和思维，为人类带来更加智能化和高效率的工作和生活体验。</p>

<p>机器人技术是另一个迅速发展的领域，它通过模仿人类的行为和动作，为人类在生产、医疗和其他领域提供更高效和精确的服务。</p>

<p>通信技术则将人们紧密联系在一起，通过互联网、移动电话和其他通信工具，人们可以随时随地保持联系和分享信息。</p>

<p>技术不仅改变了我们的生活方式，还为人类带来了许多经济和社会效益。它为各行各业提供了更高效、更智能和更精准的技术支持，推动了人类社会的科技进步。</p>

<p>随着科技的不断发展，我们相信未来会有更多令人惊奇的技术和创新出现，让我们一起期待！</p>',
        'https://fast-deer.oss-cn-beijing.aliyuncs.com/academic/4741d1f4-46b1-4e88-a090-5dbca1131e9b.png');
INSERT INTO fast_deer.academic (academic_id, user_id, date, title, content, cover)
VALUES ('3', '3', '2023-09-10 14:32:12', '"幽默研究：笑是最佳的脑运动！"', '<p><strong>幽默：笑看人生，快乐无忧！</strong></p>

<p>幽默是一种令人愉悦的力量，它让我们能够笑看人生，快乐无忧。从古至今，幽默一直被人们所喜爱，因为它能够带给我们无穷的快乐和乐趣。</p>

<p>幽默可以分为不同类型，例如讽刺、反讽、黑色幽默等。无论哪种类型的幽默，都能够让人忍不住捧腹大笑。幽默的特点就是能够让人在轻松的氛围中释放压力，享受快乐。</p>

<p>我们身边不乏幽默的例子。例如，有些人的性格天生就充满了幽默感，他们的言行举止总是让人忍不住发笑。还有一些人则擅长运用幽默来化解紧张气氛，让人们在轻松愉快的氛围中享受美好时光。</p>

<p>幽默不仅能够带给我们快乐，还有很多积极的作用。它可以促进人们的交流和沟通，增强人际关系。在社交场合，幽默还可以化解尴尬气氛，减轻人们的压力。最重要的是，幽默可以帮助我们更好地看待人生，让我们更加乐观向上。</p>

<p>总之，幽默是一种不可或缺的力量。让我们在繁忙的生活中多一些幽默感，享受轻松愉快的时光！</p>',
        'https://fast-deer.oss-cn-beijing.aliyuncs.com/academic/48c2b0f3-ea66-4d3f-af04-9787fd79dd78.png');
INSERT INTO fast_deer.academic (academic_id, user_id, date, title, content, cover)
VALUES ('5', '4', '2023-09-10 14:31:37', '"艺术：大胆创意，让生活更美好！"', '<p><strong>艺术：灵魂的食粮，创造力的展现！</strong></p>

<p>艺术是人类情感、想法和创造力的独特表现，它激发着我们的灵魂，并为我们的生活带来丰富的色彩。从古至今，艺术一直在人类社会中占据着举足轻重的地位。</p>

<p>艺术的形式多种多样，包括绘画、音乐、舞蹈、戏剧、电影等等。艺术家们通过他们的作品表达着情感、传递着信息，并引发观众的共鸣和思考。这些作品或美丽或震撼，让人们感受到艺术家们内心深处的情感和创造力。</p>

<p>绘画是艺术的一种重要形式，艺术家们用画笔和颜料在画布上展示他们的创意。从古代的壁画到现代的抽象艺术，绘画作品以其独特的视觉效果和情感吸引力而著称。音乐则是另一种广泛表达人类情感的艺术形式，通过旋律、节奏和歌词，音乐家们传达着他们的激情和情感。</p>

<p>舞蹈是身体与音乐完美融合的艺术形式，它以优雅和力量并存的方式表达着舞者的情感和故事。戏剧则是通过演员的表演来展示人物、情感和冲突，它让观众深入体验人物的内心世界。</p>

<p>艺术不仅给我们带来视觉和听觉的享受，还启发了我们的思维和想象力。它让我们感受到人类情感和创造力的无穷无尽，同时也让我们认识到不同文化背景下的艺术价值。无论是在博物馆、画廊、剧院还是街头，艺术都在以它独特的方式感染着我们，让我们领略到人类文明的多样性和丰富性。</p>

<p>让我们一起沉浸在艺术的世界中，感受它的魅力和力量，体验它带给我们的愉悦和启迪！</p>',
        'https://fast-deer.oss-cn-beijing.aliyuncs.com/academic/9934f0af-73c0-41b4-9258-828b85287685.png');
INSERT INTO fast_deer.academic (academic_id, user_id, date, title, content, cover)
VALUES ('4', '5', '2023-09-10 15:11:11', '"科学：宇宙的秘密，等待你的探索！"', '<p><strong>科学：探索未知，创造未来！</strong></p>

<p>科学是人类不断探索未知领域、创新和进步的源泉。从古至今，科学在人类社会中扮演着至关重要的角色，不断推动着人类文明的进步。</p>

<p>科学涉及多个领域，包括物理学、化学、生物学、天文学和地球科学等。这些学科不断地拓展着人类对自然界和宇宙的认识。科学家们通过不断的研究和创新，为人类带来了许多改变世界的发明和发现。</p>

<p>物理学是研究物质和能量的学科。从牛顿的万有引力定律到爱因斯坦的相对论，物理学的不断发展使人类对自然界有了更深刻的理解。化学是研究物质变化的学科，从分子到复杂的有机化合物，化学不断地为人类揭示自然界中的奇妙变化。</p>

<p>生物学是研究生命的学科。通过生物学的研究，人类不断揭示生物体的奥秘，从细胞到复杂的生态系统，生物学使人类能够更好地了解生命的本质和起源。天文学是研究宇宙的学科，从星系到遥远的行星，天文学为人类揭示了宇宙的浩瀚和壮美。</p>

<p>科学不断地拓展着人类的知识边界，为人类带来许多创新和进步。它不仅改变着人类对自然界和宇宙的认识，还影响着人类社会的各个方面。让我们一起投身科学的研究，共同探索未知的领域，创造美好的未来！</p>',
        'https://fast-deer.oss-cn-beijing.aliyuncs.com/academic/9a989fab-9098-4c5e-8a0f-fc98809060c1.png');

# academic_comment
INSERT INTO academic_comment (academic_comment_id, user_id, academic_id, date, content)
VALUES ('1', '1', '1', '2023-09-11 11:12:03', '这是评论内容1');
INSERT INTO academic_comment (academic_comment_id, user_id, academic_id, date, content)
VALUES ('2', '2', '2', '2023-11-11 11:12:03', '这是评论内容2');
INSERT INTO academic_comment (academic_comment_id, user_id, academic_id, date, content)
VALUES ('3', '3', '3', '2022-07-11 11:12:03', '这是评论内容3');
INSERT INTO academic_comment (academic_comment_id, user_id, academic_id, date, content)
VALUES ('4', '4', '4', '2023-09-14 11:12:03', '这是评论内容4');
INSERT INTO academic_comment (academic_comment_id, user_id, academic_id, date, content)
VALUES ('5', '5', '5', '2022-12-14 11:12:03', '这是评论内容5');
INSERT INTO academic_comment (academic_comment_id, user_id, academic_id, date, content)
VALUES ('6', '6', '1', '2023-08-21 11:12:03', '这是评论内容6');
INSERT INTO academic_comment (academic_comment_id, user_id, academic_id, date, content)
VALUES ('7', '1', '2', '2021-01-29 11:12:03', '这是评论内容7');
INSERT INTO academic_comment (academic_comment_id, user_id, academic_id, date, content)
VALUES ('8', '2', '3', '2023-05-16 11:12:03', '这是评论内容8');
INSERT INTO academic_comment (academic_comment_id, user_id, academic_id, date, content)
VALUES ('9', '3', '4', '2022-07-27 11:12:03', '这是评论内容9');
INSERT INTO academic_comment (academic_comment_id, user_id, academic_id, date, content)
VALUES ('10', '4', '5', '2021-01-01 11:12:03', '这是评论内容10');

INSERT INTO feedback(feedback_id, user_id, date, tag, rate, content, phone)
VALUES ('1', '1', '2021-01-01', '问题', '4', 'Here have a bug', '13888888888');
INSERT INTO feedback(feedback_id, user_id, date, tag, rate, content, phone)
VALUES ('1', '1', '2021-01-01', '建议', '4', 'Very good', '13888888888');

insert into school(school_id, user_id, password)
VALUES ('1', '100', '123');
