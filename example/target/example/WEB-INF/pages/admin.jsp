<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>我的主页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#example-navbar-collapse">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">我的管理后台</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a><shiro:principal/></a></li>
                <li><a href="${basePath}/user/logout.html"><span class="glyphicon glyphicon-log-out"></span> 退出登录</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <div class=" collapse navbar-collapse" id="example-navbar-collapse">
                <div class="panel-group table-responsive" role="tablist">
                    <div class="panel panel-primary leftMenu">
                        <!-- 利用data-target指定要折叠的分组列表 -->
                        <div class="panel-heading" id="collapseListGroupHeading1" data-toggle="collapse"
                             data-target="#collapseListGroup1" role="tab">
                            <h4 class="panel-title">
                                账户中心
                                <span class="glyphicon glyphicon-chevron-up right"></span>
                            </h4>
                        </div>
                        <!-- .panel-collapse和.collapse标明折叠元素 .in表示要显示出来 -->
                        <div id="collapseListGroup1" class="panel-collapse collapse in" role="tabpanel"
                             aria-labelledby="collapseListGroupHeading1">
                            <ul class="nav">
                                <li>
                                    <a href="javascript:;" path='<c:url value="/topic/index.html" />'> 总览</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="panel panel-primary leftMenu">
                        <div class="panel-heading" id="collapseListGroupHeading2" data-toggle="collapse"
                             data-target="#collapseListGroup2" role="tab">
                            <h4 class="panel-title">
                                共享物品中心
                                <span class="glyphicon glyphicon-chevron-down right"></span>
                            </h4>
                        </div>
                        <div id="collapseListGroup2" class="panel-collapse collapse in" role="tabpanel"
                             aria-labelledby="collapseListGroupHeading2">
                            <ul class="nav">
                                <li>
                                    <a href="javascript:;" path='<c:url value="/article/adminArticles.html"/> '>我的物品</a>
                                </li>
                                <li>
                                    <a href="${basePath}/article/addArticle.html" target="_blank">发布物品</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="panel panel-primary leftMenu">
                        <!-- 利用data-target指定要折叠的分组列表 -->
                        <div class="panel-heading" id="collapseListGroupHeading3" data-toggle="collapse"
                             data-target="#collapseListGroup3" role="tab">
                            <h4 class="panel-title">
                                需求物品中心
                                <span class="glyphicon glyphicon-chevron-up right"></span>
                            </h4>
                        </div>
                        <!-- .panel-collapse和.collapse标明折叠元素 .in表示要显示出来 -->
                        <div id="collapseListGroup3" class="panel-collapse collapse in" role="tabpanel"
                             aria-labelledby="collapseListGroupHeading3">
                            <ul class="nav">
                                <li>
                                    <a href="javascript:;" path='<c:url value="/need/adminNeed.html"/>'>我的物品申请</a>
                                </li>
                                <li>
                                    <a href="${basePath}/need/addNeed.html" target="_blank">发布申请</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="panel panel-primary leftMenu">
                        <!-- 利用data-target指定要折叠的分组列表 -->
                        <div class="panel-heading" id="collapseListGroupHeading4" data-toggle="collapse"
                             data-target="#collapseListGroup4" role="tab">
                            <h4 class="panel-title">
                                关注中心
                                <span class="glyphicon glyphicon-chevron-up right"></span>
                            </h4>
                        </div>
                        <!-- .panel-collapse和.collapse标明折叠元素 .in表示要显示出来 -->
                        <div id="collapseListGroup4" class="panel-collapse collapse in" role="tabpanel"
                             aria-labelledby="collapseListGroupHeading4">
                            <ul class="nav">
                                <li>
                                    <a href="javascript:;"
                                       path='<c:url value="/attention/myAttentions.html"/> '>关注的商品</a>
                                </li>
                                <li>
                                    <a href="javascript:;">浏览历史</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div id="page-wrapper">
                <img src='<c:url value="/static/images/loading.gif" />' style="display:block;margin:auto;"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${basePath}/static/js/validate.js"></script>
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
        //var url = $('.leftMenu .panel-collapse li a:first').attr("path");
        var url = "${basePath}/article/adminArticles.html";
        $('#page-wrapper').load(url);

        //点击菜单  显示图片加载页面
        $('.leftMenu .panel-collapse li a').click(function () {
            if (typeof($(this).attr("path")) != "undefined") {
                $('#page-wrapper').html('');
                var imgObj = document.createElement("img");
                imgObj.src = '<c:url value="/static/images/loading.gif" />';
                imgObj.style.display = 'block';
                imgObj.style.margin = 'auto';
                $('#page-wrapper').append(imgObj);
                var url = $(this).attr("path");
                $('#page-wrapper').load(url);
            }
        });
    });
</script>
