<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>物品所有者的联系方式</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="${basePath}/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="panel panel-info">
        <div class="page-header">
            <h3>物品主人的联系方式</h3>
        </div>
        <div class="panel-body">
            <p>用户名：${user.username}</p>
            <p>手机号码：${user.phoneNumber}</p>
            <p>邮箱：${user.email}</p>
            <p>地址：${user.address}</p>
        </div>
    </div>
</div>
</body>
</html>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${basePath}/static/js/jquery-3.0.0.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${basePath}/static/js/bootstrap.min.js"></script>
