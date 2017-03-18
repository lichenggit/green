<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>物品所有者的联系方式</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
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
