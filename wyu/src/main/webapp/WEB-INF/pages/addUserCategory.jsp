<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<div class="panel panel-info">
    <div class="panel-heading">
        添加子用户类别
    </div>
    <div class="panel-body">
        <form class="form-inline" id="createUserCategoryForm" role="form"
              action="${basePath}/user/addUserCategory.html">
            <div class="form-group">
                <label for="categoryName" class="control-label">用户所属类别</label>
                <input type="text" class="form-control" id="categoryName" name="categoryName" required
                       placeholder="请类别名称">
            </div>
            <br/>
            <br/>
            <div class="form-group">
                <button type="button" class="btn btn-default" onclick="onAddUserCategoryCancel();">
                    取消
                </button>
                <button type="button" class="btn btn-warning" onclick="$.load.reset('createUserCategoryForm');">重置</button>
                <button type="submit" class="btn btn-success">提交</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    $.validator.setDefaults({
        submitHandler: function () {
            $.post($("#createUserCategoryForm").attr("action"), $("#createUserCategoryForm").serialize(), function (result) {
                onAddUserCategorySuccess();
            });
        }
    });
    $().ready(function () {
        $("#createUserCategoryForm").validate();
    });
</script>


