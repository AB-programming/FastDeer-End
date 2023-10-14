# 快鹿后端

> 作者：[AB-programming](https://github.com/AB-programming/)

本仓库为快鹿后端代码，以下是关于快鹿项目的其他地址

客户端：[快鹿客户端](https://github.com/AB-programming/FastDeer-Client)

后台管理：
- 学校后台管理：[学校后台管理](https://github.com/AB-programming/FastDeer-School-Admin) 
- 管理员后台：[管理员后台](https://github.com/AB-programming/FastDeer-Admin)

## 介绍 📦
快鹿（FastDeer）是一款面向青年群体和学生的综合性APP，整合了校园分帖、聊天、资源共享、学术资讯、校园活动发布与接收、校园就业信息发布与接收、志愿者服务信息发布与接收，平台反馈等功能，并配备了一套Web后台管理系统，以及各个校方的一套Web后台管理系统，旨在满足校园大众的多方面需求。

主要功能:
- 论坛帖子： 用户可以创建和浏览论坛帖子，分享信息和经验。
- 聊天功能：用户之间可以进行一对一聊天，便捷的社交交流工具。
- 学术资讯：用户或教师可以发布相关的学术文章内容。
- 校园活动：学校和学生组织可以发布相关活动，用户可以浏览。
- 资源共享：用户可以共享和获取学习资源，如课件、笔记等。
- 校园就业：学生可以获取最新的校园招聘信息，校方可以发布招聘信息。
- 志愿者通道： 提供志愿者服务活动，学生可以报名参加，鼓励学生积极参与社会服务。
- 平台反馈：用户可以向平台发起反馈，平台收到反馈不断改进。

## 技术选型（后端） 🔬
- Spring Boot 3.1.1
- Mybatis-Plus 3.5.3
- Spring Security 6.1.1
- Spring Websocket 3.1.1
- Spring Data Redis 3.1.1
- Java JWT 3.4.0
- MySQL 8
- Redis 5.0.14

## 启动部署说明（后端） 🚀
### 本地启动
所需环境
- JDK17
- Maven，Maven版本尽量大于等于3.3.9
- MySQL，版本推荐为8.0.26
- Redis

然后将本项目Clone到本地，建议使用Intellij IDEA导入此工程

启动前需要修改application.xml中相关配置
```yaml
dev:
  host: http://47.110.229.138:8080
  mysql:
    address: localhost #改成自己的服务器地址，如果用的是云数据库，可以填写相应的host
    username: root #改成自己的数据库用户名
    password: 12345678 #改成自己的数据库密码
  redis:
    host: localhost #改成自己的服务器地址，如果用的是云数据库，可以填写相应的host
    password: 12345678
    database:
      user-db: 0
      message-db: 1
    port: 6379
    maxTotal: 30 # 最大连接数
    maxIdle: 20 # 最大空闲连接数

  staticUrl: file:/usr/local/lib/static/ # 此目录要保存部分文件，请改成自己指定的目录，请注意，static目录需要存在
  maxFileSize: 200
  maxRequestSize: 400
  avatarUpload:
    uploadLocation: /usr/local/lib/static/avatar/ # 此目录要保存部分头像文件，请修改为自己的目录地址
    avatarUrl: ${dev.host}/static/avatar/
  postUpload:
    uploadLocation: /usr/local/lib/static/post/ # 此目录要保存部分的媒体文件，请修改为自己的目录地址
    postUrl: ${dev.host}/static/post/

server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://${dev.mysql.address}:3306/fast_deer?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ${dev.mysql.username}
    password: ${dev.mysql.password}


fastDeer:
  jwt:
    secret: fastDeer8888 # Spring Security 密钥
    expireTime: 120 # token过期时间， 单位-分钟
    issuer: fast-deer # JWT签发者
  admin:
    username: user
    password: 123

# 设计安全考虑，不使用配置文件+accessKeyId和accessKeySecret，使用服务器环境变量。因此服务器需要环境变量中分别暴露：
# export OSS_ACCESS_KEY_ID=
# export OSS_ACCESS_KEY_SECRET=
# 下面是阿里云相关的OSS对象存储配置，请修改为自己的OSS配置，endpoint，bucketName以及OSS的地址请参照云服务厂商提供的文档说明
oss:
  endpoint: https://oss-cn-beijing.aliyuncs.com/
  bucketName: fast-deer
  ossAddress: https://fast-deer.oss-cn-beijing.aliyuncs.com/
```
此项目所用到的外部数据库包括MySQL和Redis，根据本机数据库直接修改配置文件即可，注释中都有相关说明

除此之外，本项目还用到了对象存储，这里还是建议使用阿里云的OSS吧，如果之前没用过好像是可以免费试用3个月，挺不错的，按照它们官方文档走就行。
然后阿里云OSS的文档中是给到了两种配置的方式，一种是直接把key的id和密钥直接硬编码写到代码中，另一种是放置到机器的环境变量中，这里用的是第二种。
如果想更改方式请参照阿里云OSS文档[https://help.aliyun.com/zh/oss/developer-reference/oss-java-configure-access-credentials?spm=a2c4g.11186623.0.0.7b0871bfX6TY8s](https://help.aliyun.com/zh/oss/developer-reference/oss-java-configure-access-credentials?spm=a2c4g.11186623.0.0.7b0871bfX6TY8s)

使用其他地方的OSS也可以吧，具体怎么配置不太清楚，请看相应的文档，我也没用过

然后就是数据相关，数据库用的MySQL，首先在您本机MySQL中建立`fast_deer`数据库，
然后执行/src/main/resources/sql/init.sql。执行完可以执行mock.sql模拟一些假数据（可选）

最后就是导入IDE之后，扫完缩印，按照常规启动Spring Boot的方式启动就好了

## 项目后续说明 🧱
此项目目前还有很多需要改进的地方，比如权限校验、控制器API代码重复、模块拆分等等，后续有时间会完善、拆分成微服务

如果您了解过此项目后发现不足或需要改进之处，还请您可以去发出issue或者pr，非常感谢🙏

## 感谢 🌸
创作不易，本项目目前可能较为简单，若您不嫌弃并且对您有帮助的话，还请您可以帮我点一下star，非常感谢🙏