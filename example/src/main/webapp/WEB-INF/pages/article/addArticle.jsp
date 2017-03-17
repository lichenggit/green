<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>我要发布</title>
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
        <div class="page-header">
            <div class="center-block" style="width: 100px;">
                <h3>我要发布</h3>
            </div>
        </div>
        <div class="panel-body">
            <form class="form-horizontal" role="form" action="${basePath}/article/addArticle.html" method="post">
                <div class="form-group">
                    <label for="categoryId" class="col-sm-2 control-label">物品类别</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="categoryId" name="categoryId"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="articleName" class="col-sm-2 control-label">物品名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="articleName" name="articleName" placeholder="物品名称">
                    </div>
                </div>
                <div class="form-group">
                    <label for="articleDegree" class="col-sm-2 control-label">新旧程度</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="articleDegree" name="articleDegree"
                               placeholder="新旧程度">
                    </div>
                </div>
                <div class="form-group">
                    <label for="price" class="col-sm-2 control-label">价格</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="price" name="price" placeholder="价格">
                    </div>
                </div>
                <div class="form-group">
                    <label for="contactMan" class="col-sm-2 control-label">联系人</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="contactMan" name="contactMan" placeholder="联系人">
                    </div>
                </div>
                <div class="form-group">
                    <label for="contactWay" class="col-sm-2 control-label">联系方式</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="contactWay" name="contactWay" placeholder="联系方式">
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-2 control-label">物品简介</label>
                    <div class="col-sm-10">
                        <textarea type="text" class="form-control" id="description" name="description" placeholder="物品简介"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $.ajax({
            type: "GET",
            url: "${basePath}/article/getCategorys.json",
            dataType: "json",
            success: function (result) {
                if (result.code == 0) {
                    var obj = {};
                    obj.categoryId = '';
                    obj.categoryName = '请选择';
                    result.data.unshift(obj);
                    $.each(result.data, function (index, item) {
                        $('#categoryId').append($("<option/>", {
                            value: item.categoryId,
                            text: item.categoryName
                        }));
                    });
                }
            }
        });
    });
</script>
