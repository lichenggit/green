<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<div class="panel panel-info">
    <div class="panel-heading">
        添加用户
    </div>
    <div class="panel-body">
        <form class="form-inline" id="createUserForm" role="form" method="post" action="${basePath}/user/addUser.html">
            <div class="form-group">
                <label for="username" class="control-label">用户名</label>
                <input type="text" class="form-control" id="username" name="username" required chrnum="true"
                       remote="${basePath}/user/isExistUsername.json"
                       placeholder="请输入用户名">
            </div>
            <br/>
            <br/>
            <div class="form-group">
                <label for="password" class="control-label">密 码</label>
                <input type="password" class="form-control" id="password" name="password" required placeholder="请输入用户名">
            </div>
            <br/>
            <br/>
            <div class="form-group">
                <label for="passwordConfirm" class="control-label">密码确认</label>
                <input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm" required equalTo="#password"
                       placeholder="请确认密码">
            </div>
            <br/>
            <br/>
            <div class="form-group">
                <label for="chineseName" class="control-label">姓 名</label>
                <input type="text" class="form-control" id="chineseName" name="chineseName" required
                       placeholder="请输入名字">
            </div>
            <br/>
            <br/>
            <div class="form-group">
                <label for="categoryId" class="control-label">类别</label>
                <select id="categoryId" name="categoryId" class="form-control" required>
                </select>
                <label class="control-label"><a data-toggle="modal" data-remote="${basePath}/user/addUserCategory.html"
                                                data-target="#myModal">点击添加类型</a></label>
            </div>
            <br/>
            <br/>
            <div class="form-group">
                <button type="button" class="btn btn-default" onclick="$.load.a('${basePath}/user/listAllUser.html')">
                    取消
                </button>
                <button type="button" class="btn btn-warning" onclick="$.load.reset('createUserForm');">重置</button>
                <button type="submit" class="btn btn-success">提交</button>
            </div>
        </form>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body"></div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script type="text/javascript">
    $.validator.setDefaults({
        submitHandler: function () {
            $.load.form("createUserForm", "添加用户成功");
        }
    });
    $().ready(function () {
        $("#createUserForm").validate({
            rules: {},
            messages: {
                username: {
                    required: "用户名不能为空",
                    remote: "用户名已存在"
                },
                password: "密码不能为空",
                passwordConfirm: {
                    required: "请确认密码",
                    equalTo: "两次密码不一致"
                },
                chineseName: "名字不能为空"
            }
        });
        setUserCategory();
    });
    function onAddUserCategoryCancel() {
        $("#myModal").modal('hide');
    }
    function onAddUserCategorySuccess() {
        $("#myModal").modal('hide');
        setUserCategory();
    }
    function setUserCategory() {
        $.get("${basePath}/user/getSubCategories.html", null, function (result) {
            $('#categoryId').empty();
            var categories = result.data;
            for (var i = 0; i < categories.length; i++) {
                var value = categories[i].categoryId;
                var name = categories[i].categoryName;
                $("#categoryId").append("<option value=" + value + ">" + name + "</option>"); //为Select追加一个Option(下拉项)
            }
        }, "json");
    }

</script>


