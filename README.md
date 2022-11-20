# Java Web personal music

此项目为Java Web 课程大作业，主要实现了个人本地音乐库，使得用户能自己快速维护和查询自己的音乐收藏信息。

- 本项目课程作业前端使用了 Bootstrap 3 框架
- 前端使用 toastr 非阻塞通知
- 后端则采用 MySQL + jsp + Servlet + Dbcp数据库连接池 + Apache DbUtils

## 依赖

- commons-dbcp2-2.1.1.jar
- commons-dbutils-1.7.jar
- commons-logging-1.2.jar
- commons-pool2-2.4.2.jar
- jstl.jar
- mysql-connector-java-8.0.27.jar
- standard.jar

## 注意事项

项目是通过 idea 创建，请最好使用 Tomcat 9，不然 session、pageContext 在 idea 里识别不了，会很难受。

数据库相关
```
数据库 userdb 中有两个表：
    tb_user  存放用户注册登录的信息
    tb_music 音乐信息

数据库账号密码：
username = "root"
password = ""
登录页面登录账号密码均相同：admin、admin2、admin3

详细可查看 output.sql 文件
```

## 项目构造

采用分层思想，bean + dao + factory + Filter + util + servlet

```
.bean 数据
    User
    Music

.dao 接口
    IUserDao
    .impl 实现接口类
        DbUtilsUserDaoImpl  同理

    IMusicDao
    .impl 实现接口类
        DbUtilsMusicDaoImpl

.factory 工厂类 new方法
    DaoFactory
        IUserDao getUserDaoInstance
            new DbUtilsUserDaoImpl
        IMusicDao getMusicDaoInstance
            new DbUtilsMusicDaoImpl

.Filter
    AEncodingFilter 设置编码

    LoginAccessFilter
        放行
            index.jsp
            login.jsp
            register.jsp
            /js
            /image
            /css
        封锁-必须登录
            music.jsp

.util
    DbcpPool.java 数据库连接池，
    在 .dao 中 DbUtilsUserDaoImpl 去使用
    在 .dao 中 DbUtilsMusicDaoImpl 去使用

.servlet
    UserLoginServlet 处理用户登录功能
        /loginForm
    UserLogoutServlet 处理用户退出功能
        /logout
    UserRegisterServlet 处理创建账号
        /registerForm

    MusicAddServlet 处理用户插入音乐数据
        /addMusic
    MusicDelServlet 处理用户删除音乐数据
        /delMusic
    MusicUpdateServlet 处理用户更新音乐数据
        /update_music
```

## 效果

![登录.png](/img/登录.png)
![index.png](/img/index.png)
![index2.png](/img/index2.png)
![music.png](/img/music.png)
![music2.png](/img/music2.png)
![效果01.png](/img/效果01.png)
![效果02.png](/img/效果02.png)
![效果03.png](/img/效果03.png)

- [ ] 如果你有兴趣，你可以在此基础上编程，为音乐添加 Tag 信息，从而能直接通过 Tag 标签直接统计和查找。
