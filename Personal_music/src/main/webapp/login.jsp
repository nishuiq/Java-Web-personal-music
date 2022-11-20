<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户登录</title>
    <!--加载bootstrap-->
    <link rel="stylesheet" href="bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/markdown.css">
    <link rel="stylesheet" href="css/base.css">
</head>
<body class="background">

<div class="loginForm">
    <form action="loginForm" method="post">
        <div class="form-group">
            <label>用户名</label>
            <input type="text" class="form-control" name="username" placeholder="请输入用户名">
        </div>
        <div class="form-group">
            <label>密码</label>
            <input type="password" class="form-control" name="password" placeholder="请输入密码">
        </div>

        <c:if test="${!empty msg}">
            <div class="alert alert-danger" role="alert">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <span class="sr-only">Error:</span>
                ${msg}
            </div>
        </c:if>
        <div style="text-align: center;">
            <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
            <h4 class="text-center"><a href="register.jsp">创建账号</a></h4>
        </div>

    </form>
</div>
</body>
</html>