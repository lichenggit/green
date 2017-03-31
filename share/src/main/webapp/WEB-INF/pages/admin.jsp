<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>我的主页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <link href="${basePath}/static/css/sb-admin.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"
                        onclick="collapse();">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="javascript:;">我的管理后台</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li><a><shiro:principal/></a></li>
                <li><a href="${basePath}/user/logout.html"><span class="glyphicon glyphicon-log-out"></span> 退出登录</a></li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="javascript:;" path='<c:url value="/article/adminArticles.html"/> '><span
                                class="glyphicon glyphicon-apple"></span>我的物品</a>
                    </li>
                    <li>
                        <a href="${basePath}/article/addArticle.html" target="_blank"><span
                                class="glyphicon glyphicon-apple"></span>发布物品</a>
                    </li>
                    <li>
                        <a href="javascript:;" path='<c:url value="/need/adminNeed.html"/>'><span
                                class="glyphicon glyphicon-apple"></span>我的物品申请</a>
                    </li>
                    <li>
                        <a href="${basePath}/need/addNeed.html" target="_blank"><span
                                class="glyphicon glyphicon-apple"></span>发布申请</a>
                    </li>
                    <li>
                        <a href="javascript:;"
                           path='<c:url value="/attention/adminAttentions.html"/> '><span
                                class="glyphicon glyphicon-apple"></span>关注的商品</a>
                    </li>
                    <li>
                        <a href="javascript:;" path='<c:url value="/attention/adminScan.html"/> '><span
                                class="glyphicon glyphicon-apple"></span>浏览历史（前10）</a>
                    </li>
                    <li>
                        <a href="javascript:;" path='<c:url value="/order/adminsharepools.html"/> '><span
                                class="glyphicon glyphicon-apple"></span>共享池</a>
                    </li>
                    <li>
                        <a href="javascript:;" path='<c:url value="/order/adminshareorders.html"/> '><span
                                class="glyphicon glyphicon-apple"></span>订单</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
    </nav>
    <input type="hidden" id="url" value="${url}">
    <div id="page-wrapper">
    </div>
</div>
</body>
<%--动态加载管理系统右边内容--%>
<script type="text/javascript">
    $(function () {
        var url = $("#url").val();
        if(url==null||url==""){
            url = $('.navbar-ex1-collapse li a:first').attr("path");
        }else {
            url="${basePath}"+url;
        }
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
</script>
</html>

