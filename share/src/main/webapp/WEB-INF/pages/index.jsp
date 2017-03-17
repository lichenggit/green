<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>首页</title>
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
<body style="padding-top: 60px">
<%--<div class="row" style="background: #F1F1F1;margin-bottom: 40px">--%>
<%--<h5>ds</h5>--%>
<%--</div>--%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${basePath}/index.html">校园共享平台${jrebel}</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav" id="mynav">
                <%--<form class="navbar-form navbar-left" role="search">--%>
                    <%--<div class="form-group">--%>
                        <%--<input type="text" class="form-control" placeholder="我要查找">--%>
                    <%--</div>--%>
                    <%--<button type="submit" class="btn btn-default">搜索</button>--%>
                <%--</form>--%>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="javascript:;" path='<c:url value="/article/addArticle.html"/> '>发布</a></li>
                <shiro:guest>
                    <li>
                        <a href="javascript:;" path='<c:url value="/user/login.html"/> '><span
                                class="glyphicon glyphicon-log-in"></span> 登录</a>
                    </li>
                </shiro:guest>
                <shiro:authenticated>
                    <li class="dropdown">
                        <a href="javascript:;" path='<c:url value="/admin.html"/> ' class="dropdown-toggle"
                           data-toggle="dropdown" data-hover="dropdown">
                            <shiro:principal/>
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="javascript:;" path='<c:url value="/admin.html"/> '>我的管理后台</a></li>
                            <li class="divider"></li>
                            <li><a href="javascript:;" path='<c:url value="/user/logout.html"/> '>退出</a></li>
                        </ul>
                    </li>
                </shiro:authenticated>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container" id="page-wrapper">
</div>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${basePath}/static/js/jquery-3.0.0.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${basePath}/static/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-hover-dropdown/2.0.10/bootstrap-hover-dropdown.min.js"></script>
<script src="${basePath}/static/js/main.js"></script>
<%--动态加载管理系统右边内容--%>
<script type="text/javascript">
    $(function () {
        var url ="${basePath}/list/home.html";
        $('#page-wrapper').load(url);

        //点击菜单  显示图片加载页面
        $('#mynav li a').click(function () {
            $('#page-wrapper').html('');
            var url = $(this).attr("path");
            $('#page-wrapper').load(url);
        });

        $('.navbar-right li a').click(function () {
            var url = $(this).attr("path");
            window.open(url);
        });
    });

</script>
</html>
