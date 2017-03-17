<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>详情页</title>
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
<div class="container">
    <div class="panel panel-info">
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-6 col-md-6">
                    <a href="#" class="thumbnail">
                        <img src="${basePath}/static/images/default.jpg"
                             alt="通用的占位符缩略图">
                    </a>
                </div>
                <div class="col-sm-6 col-md-6">
                    <div class="jumbotron">
                        <h3>名称：${item.articleName}</h3>
                        <p>细节：${item.description}</p>
                        <p>新旧：${item.articleDegree}成新</p>
                        <p>价格：${item.price}</p>
                    </div>
                    <p>
                        <a onclick="addAttention('${item.articleId}');" class="btn btn-warning btn-lg btn-attention" role="button">
                            关注
                        </a>
                        <a href="${basePath}/article/gainContacts.html?articleId=${item.articleId}&userId=${item.userId}"
                           target="_blank" class="btn btn-success btn-lg" role="button">
                            一键获取
                        </a>
                    </p>
                </div>
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
<script src="http://cdn.bootcss.com/bootstrap-hover-dropdown/2.0.10/bootstrap-hover-dropdown.min.js"></script>
<script src="${basePath}/static/js/main.js"></script>
<script type="text/javascript">
    //加关注
    function addAttention(articleId) {
        $.ajax({
            type: "POST",
            url: "${basePath}/attention/addAttention.json",
            data: {"articleId": articleId},
            dataType: "json",
            success: function (result) {
                if (result.code == 0) {
                    $('.btn-attention').text('已关注');
                }
            },
            error: function () {
                alert("请登录");
            }
        });
    }
</script>
