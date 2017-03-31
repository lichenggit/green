<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>我要发布</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
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
            <form class="form-horizontal" id="addArticleform" role="form" action="${basePath}/article/addArticle.html" method="post">
                <div class="form-group">
                    <label for="categoryId" class="col-sm-2 control-label">物品类别</label>
                    <div class="col-sm-10">
                        <select class="form-control" required id="categoryId" name="categoryId"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="articleName" class="col-sm-2 control-label">物品名称</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" required id="articleName" name="articleName" placeholder="物品名称">
                    </div>
                </div>
                <div class="form-group">
                    <label for="articleDegree" class="col-sm-2 control-label">新旧程度</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" required digits="true" min="1" max="10" id="articleDegree" name="articleDegree"
                               placeholder="几成新">
                    </div>
                </div>
                <div class="form-group">
                    <label for="price" class="col-sm-2 control-label">价格</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="price"  number="true" name="price" placeholder="价格">
                    </div>
                </div>
                <%--<div class="form-group">--%>
                    <%--<label for="contactMan" class="col-sm-2 control-label">联系人</label>--%>
                    <%--<div class="col-sm-10">--%>
                        <%--<input type="text" class="form-control" id="contactMan" name="contactMan" placeholder="联系人">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<label for="contactWay" class="col-sm-2 control-label">联系方式</label>--%>
                    <%--<div class="col-sm-10">--%>
                        <%--<input type="text" class="form-control" id="contactWay" name="contactWay" placeholder="联系方式">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <label for="description" class="col-sm-2 control-label">物品简介</label>
                    <div class="col-sm-10">
                        <textarea type="text" class="form-control" required id="description" name="description"
                                  placeholder="物品简介"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="accessEnable" class="col-sm-2 control-label">获取资格</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="accessEnable" name="accessEnable">
                            <option value="NONE">无限制</option>
                            <option value="AUTHC">已认证的用户</option>
                            <option value="GREAT">信誉良好的用户</option>
                            <option value="SHARER">已分享过的用户</option>
                            <option value="REQUEST">要我同意</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">交易方式</label>
                    <div class="col-sm-10">
                        <label class="checkbox-inline">
                            <input type="checkbox" checked="checked" value="Y" name="sendable">可送
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" checked="checked" value="Y" name="loanable">可借
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" checked="checked" value="Y" name="tenantable">可租
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" checked="checked" value="Y" name="markrtable">可卖
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success">提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $.validator.setDefaults({
        submitHandler: function(form) {
            form.submit();
        }
    });
    $().ready(function() {
        $("#addArticleform").validate();
    });
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
