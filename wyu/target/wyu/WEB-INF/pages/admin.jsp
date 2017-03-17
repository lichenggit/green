<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>我的主页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="${basePath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/static/css/sb-admin.css" rel="stylesheet">
    <link href="${basePath}/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet"
          media="screen">
    <!-- 弹框 CSS-->
    <link href="${basePath}/static/sweet-alert/css/sweet-alert.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse" onclick="collapse();">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="javascript:;">自动表格管理系统</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li><a href="javascript:;" onclick="$.load.a('${basePath}/user/userDetails.html');">${chineseName}</a>
                </li>
                <li>
                    <a href="${basePath}/user/logout.html"><span class="glyphicon glyphicon-log-out"></span> 退出</a>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <c:forEach items="${menuItems}" var="item">
                        <li>
                            <a href="javascript:;" path='<c:url value="${item.url}"/>'><span
                                    class="glyphicon ${item.icon}"></span> ${item.name}</a>
                        </li>
                    </c:forEach>
                    <%--<li>--%>
                    <%--<a href="javascript:;" path='<c:url value="/user/rolePermission.html"/> '><span class="glyphicon glyphicon-lock"></span> 权限管理</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                    <%--<a href="javascript:;" data-toggle="collapse" data-target="#demo"><span--%>
                    <%--class="glyphicon glyphicon-log-in"></span> 下拉列表 </a>--%>
                    <%--<ul id="demo" class="collapse">--%>
                    <%--<li>--%>
                    <%--<a href="#">Dropdown Item</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                    <%--<a href="#">Dropdown Item</a>--%>
                    <%--</li>--%>
                    <%--</ul>--%>
                    <%--</li>--%>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
    </nav>
    <div id="page-wrapper">
    </div>
</div>

</body>
</html>
<%--jQuery--%>
<script src="${basePath}/static/js/jquery-3.0.0.min.js"></script>
<%--表单校验--%>
<script src="${basePath}/static/jquery-validation-1.14.0/js/jquery.validate.min.js"></script>
<script src="${basePath}/static/jquery-validation-1.14.0/js/additional-methods.min.js"></script>
<script src="${basePath}/static/jquery-validation-1.14.0/js/messages_zh.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${basePath}/static/bootstrap/js/bootstrap.min.js"></script>
<%--时间选择--%>
<script src="${basePath}/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="${basePath}/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- 弹框 JS-->
<script src="${basePath}/static/sweet-alert/js/sweet-alert.js"></script>
<%--自定义--%>
<script src="${basePath}/static/js/main.js"></script>
<%--动态加载管理系统右边内容--%>
<script type="text/javascript">
    $(function () {
        $(".panel-heading").click(function (e) {
            /*切换折叠指示图标*/
            $(this).find("span").toggleClass("glyphicon-chevron-down");
            $(this).find("span").toggleClass("glyphicon-chevron-up");
        });
    });

    $(function () {
        var url = $('.navbar-ex1-collapse li a:last').attr("path");
        $('#page-wrapper').load(url);
        //点击菜单  显示图片加载页面
        $('.navbar-ex1-collapse li a').click(function () {
            if (typeof($(this).attr("path")) != "undefined") {
                $('#page-wrapper').html('');
                var url = $(this).attr("path");
                $('#page-wrapper').load(url);
            }
        });
    });
    function collapse() {
        $('.navbar-ex1-collapse').collapse("to");
    }
</script>
