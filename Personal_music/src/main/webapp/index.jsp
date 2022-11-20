<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
</head>
<body class="background">
<h3 class="index">欢迎${User.username}访问个人音乐库</h3>
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
                <li class="active">
                    <a href = "index.jsp">
                        <span class="glyphicon glyphicon-home"></span> 首页
                    </a>
                </li>
                <li>
                    <a href="music.jsp">
                        <span class="glyphicon glyphicon-music"></span> 音乐
                    </a>
                </li>
            </ul>

            <c:if test="${empty User.username}">
                <p class="navbar-text navbar-right">
                    <a href="login.jsp" class="navbar-link">Signed in</a>
                </p>
            </c:if>
            <c:if test="${!empty User.username}">
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
    <script src="bootstrap-3.3.7/js/jquery-1.12.4.min.js"></script>
    <script src="bootstrap-3.3.7/js/bootstrap.min.js"></script>
</body>
</html>