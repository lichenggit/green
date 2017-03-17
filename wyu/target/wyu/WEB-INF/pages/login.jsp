<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="${basePath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<div class="col-sm-11 col-md-4 col-lg-4 col-lg-offset-4 col-md-offset-4"
     style="margin-top: 10px ;position: absolute">
    <div class="panel panel-info">
        <div class="panel-body">
            <form class="form-horizontal" role="form" action="${basePath}/user/login.html"
                  id="my_login_form" method="post">
                <div class="form-group">
                    <label for="username" class="col-sm-3 col-xs-3 control-label">账&nbsp;&nbsp;&nbsp;号</label>
                    <div class="col-sm-9 col-xs-9">
                        <input type="text" class="form-control" value="${user.username}" id="username" name="username"
                               required placeholder="请输入账号">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-3 col-xs-3 control-label">密&nbsp;&nbsp;&nbsp;码</label>
                    <div class="col-sm-9 col-xs-9">
                        <input type="password" class="form-control" value="${user.password}" id="password"
                               name="password" required placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="verifyCode" class="col-sm-3 col-xs-3 control-label">验证码</label>
                    <div class="col-sm-6 col-xs-6">
                        <input type="text" class="form-control" id="verifyCode" name="verifyCode" required
                               rangelength="[4,4]" remote="${basePath}/user/checkVerifyCode.json"
                               placeholder="请输入验证码">
                    </div>
                    <img class="col-sm-3 col-xs-3" style="height: 34px" id="captcha_img" onclick="reloadCode();"
                         alt="点击更换"
                         title="点击更换" src="${basePath}/user/verifyCode.pic">
                </div>
                <%--<div class="form-group">--%>
                <%--<div class="col-sm-offset-1 col-sm-10">--%>
                <%--<div class="checkbox">--%>
                <%--<label>--%>
                <%--<input type="checkbox" name="rememberMe">请记住我--%>
                <%--</label>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <div class="form-group" style="text-align: center">
                    <button type="submit"
                            class="btn btn-primary">&nbsp;登&nbsp;&nbsp;录&nbsp; </button>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="resetPsw.html" target="_blank">忘记密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
                        href="register.html">注册</a>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 col-xs-3 control-label"></label>
                    <span id="errMsg" class="help-block" style="color: red">${errMsg}</span>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

<%--jQuery--%>
<script src="${basePath}/static/js/jquery-3.0.0.min.js"></script>
<%--表单校验--%>
<script src="${basePath}/static/jquery-validation-1.14.0/js/jquery.validate.min.js"></script>
<script src="${basePath}/static/jquery-validation-1.14.0/js/messages_zh.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${basePath}/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    var count = 0;
    $.validator.setDefaults({
        submitHandler: function (form) {
            form.submit();
        }
    });
    $().ready(function () {
        $("#my_login_form").validate({
            rules: {},
            messages: {
                username: "用户名不能为空",
                password: "密码不能为空",
                verifyCode: {
                    required:"验证码不能为空",
                    rangelength: "验证码为四位",
                    remote: "验证码错误"
                }
            }
        });
    });
    function reloadCode() {
        var time = new Date().getTime();
        document.getElementById("captcha_img").src = "${basePath}/user/verifyCode.pic?time=" + time;
    }
    ;

</script>