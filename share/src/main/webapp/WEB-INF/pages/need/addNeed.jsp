<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>发布需求</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-info">
        <div class="page-header">
            <div class="center-block" style="width: 100px;">
                <h3>我要申请</h3>
            </div>
        </div>
        <div class="panel-body">
            <form class="form-horizontal" role="form" action="${basePath}/need/addNeed.html" method="post">
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
                    <label for="description" class="col-sm-2 control-label">物品描述</label>
                    <div class="col-sm-10">
                        <textarea type="text" class="form-control" id="description" name="description" placeholder="物品描述"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="remark" class="col-sm-2 control-label">备注</label>
                    <div class="col-sm-10">
                        <textarea type="text" class="form-control" id="remark" name="remark" placeholder="备注"></textarea>
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
