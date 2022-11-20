<%@ page import="com.xxx.bean.User" %>
<%@ page import="com.xxx.bean.Music" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xxx.factory.DaoFactory" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人本地音乐播放器</title>
    <!--加载bootstrap-->
    <link rel="stylesheet" href="bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/markdown.css">
    <link rel="stylesheet" href="css/base.css">
    <link href="css/toastr.min.css" rel="stylesheet">

</head>
<body class="background">
<div class="navbar navbar-inverse" role="navigation"><!--导航条 navbar-inverse:反色-->
    <div class="container-fluid"><!--填满整个页面-->
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button><!--响应式布局-->
            <a class="navbar-brand" href="index.jsp">本地音乐库
            </a>
        </div>
        <div id="navbar-collapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav"><!--列表-->
                <li>
                    <a href = "index.jsp">
                        <span class="glyphicon glyphicon-home"></span> 首页
                    </a>
                </li>
                <li class="active">
                    <a href="music.jsp">
                        <span class="glyphicon glyphicon-music"></span> 音乐
                    </a>
                </li>
            </ul>

            <c:if test="${empty User}">
                <p class="navbar-text navbar-right">
                    <a href="login.jsp" class="navbar-link">Signed in</a>
                </p>
            </c:if>
            <c:if test="${!empty User}">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                ${User.username} <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="logout">注销</a></li>
                        </ul>
                    </li>
                </ul>
            </c:if>

        </div>
    </div><!-- /.container-fluid -->
</div>

<%
    if (request.getSession().getAttribute("Music") == null ||
            (Boolean) request.getSession().getAttribute("Refresh") == true) {  // 只跑一次
        User user = (User) request.getSession().getAttribute("User");
        if (user != null) {
            List<Music> music = DaoFactory.getMusicDaoInstance().findAll(user.getId());
            request.getSession().setAttribute("Music", music);
            request.getSession().setAttribute("Refresh", false);
        }
    }
%>


<div class="container">
    <div class="row">
        <div class="col-md-4">
            <div class="input-group">
                <span class="input-group-addon glyphicon glyphicon-search" aria-hidden="true"></span>
                <input type="text" class="form-control" id="input_music" placeholder="搜索..." oninput="search_music()">
            </div><!-- /input-group -->

        </div><!-- /.col-lg-6 -->

        <!-- Button trigger modal -->
        <button id="addButtonTrigger" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
            点击添加数据
        </button>

    </div>

    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">音乐库</div>

        <!-- Table -->
        <table class="table table-bordered table-hover" id = "music_table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>标题</th>
                    <th>时长</th>
                    <th>作者</th>
                    <th>专辑名</th>
                    <th>链接</th>
                </tr>
            </thead>

            <tbody>
                <c:if test="${!empty Music}">
                    <c:forEach items="${Music}" var="item" varStatus="itemStatus">
                        <tr>
                            <th scope="row">${itemStatus.count}</th>
                            <td><a data-toggle="modal" data-target="#exampleModal">${item.title}</a></td>
                            <td>${item.time}</td>
                            <td>${item.author}</td>
                            <td>${item.album_name}</td>
                            <td>
                                <a href="https://music.163.com/#/song?id=${item.wid}" target="_blank">${item.wid}</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
    </div>
</div>



<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">添加数据</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <form action="addMusic" id="add_music">
                        <input type="text" class="form-control" id="title" name="title" placeholder="标题 如 A Little Story">
                        <input type="text" class="form-control" id="time" name="time" placeholder="时长 如 03:24">
                        <input type="text" class="form-control" id="author" name="author" placeholder="作者 如 Valentin">
                        <input type="text" class="form-control" id="album_name" name="album_name" placeholder="专辑名 如 A Little Story">
                        <input type="text" class="form-control" id="wid" name="wid" placeholder="id值 如 857896">
                    </form>

                    <div id="alert" class="alert alert-danger" role="alert" style="display: none">
                        <span id="alert-span" class="glyphicon glyphicon-exclamation-sign" aria-hidden="true">

                        </span>
                        <span class="sr-only"></span>
                    </div>
                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="add_music()" id="addButton">添加数据</button>
                <button type="button" class="btn btn-primary" onclick="update_music()" id="updateButton">修改数据</button>
                <button type="button" class="btn btn-primary" onclick="del_music()" id="delButton">删除数据</button>
            </div>
        </div>
    </div>
</div>



    <script src="bootstrap-3.3.7/js/jquery-1.12.4.min.js"></script>
    <script src="bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script src="js/bootstrap-modal.js"></script>

    <script src="js/toastr.min.js"></script>
    <script type="text/javascript" src="js/music.js"></script>

    <script type="text/javascript">
        $.ajax({
            url: 'test',
            contentType: 'text/plain;charset=utf-8',
            type: 'post',
            dataType: 'json',
            success:function (result) {
                console.log(result);
            }
        })

    </script>
</body>
</html>
