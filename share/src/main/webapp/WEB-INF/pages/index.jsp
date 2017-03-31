<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
</head>
<body  style="padding-top: 60px">
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
            <a class="navbar-brand" href="javascript:;test();">校园共享平台</a>
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
                <li><a href="javascript:;$.load.a('${basePath}/article/index.html');" >共享资源区</a></li>
                <li><a href="javascript:;$.load.a('${basePath}/need/index.html');" >物品申请区</a></li>
                <li><a href="${basePath}/admin.html?type=sharepools" target="_blank">共享池</a></li>
                <li><a href="${basePath}/article/addArticle.html" target="_blank">发布</a></li>
                <%--<shiro:guest>--%>
                    <%--<li>--%>
                        <%--<a href="${basePath}/user/login.html" target="_blank"><span--%>
                                <%--class="glyphicon glyphicon-log-in"></span> 登录</a>--%>
                    <%--</li>--%>
                <%--</shiro:guest>--%>
                <%--<shiro:authenticated>--%>
                    <%--<li class="dropdown">--%>
                        <%--<a href="${basePath}/admin.html"  class="dropdown-toggle"--%>
                           <%--data-toggle="dropdown" data-hover="dropdown">--%>
                            <%--<shiro:principal/>--%>
                            <%--<span class="caret"></span></a>--%>
                        <%--<ul class="dropdown-menu" role="menu">--%>
                            <%--<li><a href="${basePath}/admin.html" target="_blank">我的管理后台</a></li>--%>
                            <%--<li class="divider"></li>--%>
                            <%--<li><a href="${basePath}/user/logout.html">退出</a></li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                <%--</shiro:authenticated>--%>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container" id="page-wrapper">
</div>
</body>
<%--动态加载管理系统右边内容--%>
<script type="text/javascript">
    $(function () {
        var url ="${basePath}/article/index.html";
        $('#page-wrapper').load(url);
    });
    $(function () {
        $('#exchange').click(function () {
            var text = $(this).text();
            if(text.indexOf("申请")>=0){
                $.load.a('${basePath}/need/index.html');
                $(this).text("共享资源区");
            }else {
                $.load.a('${basePath}/article/index.html');
                $(this).text("物品申请区");
            }
        });
    });
</script>
<script type="text/javascript">
    var  wsServer = "ws://localhost:8080/share/websocket";
    var  websocket;
    function test() {
        websocket = new WebSocket(wsServer);
        websocket.onopen = function (evt) { onOpen(evt) };
        websocket.onclose = function (evt) { onClose(evt) };
        websocket.onmessage = function (evt) { onMessage(evt) };
        websocket.onerror = function (evt) { onError(evt) };
    }

    function onOpen(evt) {
        console.log("Connected to WebSocket server.");
    }
    function onClose(evt) {
        console.log("Disconnected");
    }
    function onMessage(evt) {
        console.log('Retrieved data from server: ' + evt.data);
    }
    function onError(evt) {
        console.log('Error occured: ' + evt.data);
    }
</script>
</html>
