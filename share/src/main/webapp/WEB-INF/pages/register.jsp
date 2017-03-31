<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
</head>
<body>
<div class="col-sm-11 col-md-4 col-lg-4 col-lg-offset-4 col-md-offset-4"
     style="margin-top: 10px ;position: absolute">
    <div class="panel panel-info">
        <div class="panel-body">
            <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/user/register.html"
                  id="login_form"
                  method="post">
                <div class="form-group">
                    <label for="username" class="col-sm-3 col-xs-3 control-label">账&nbsp;&nbsp;&nbsp;号</label>
                    <div class="col-sm-9 col-xs-9">
                        <input type="text" class="form-control" id="username" name="username"
                               value="${user.username}"
                               placeholder="请输入账号">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-3 col-xs-3 control-label">密&nbsp;&nbsp;&nbsp;码</label>
                    <div class="col-sm-9 col-xs-9">
                        <input type="password" class="form-control" id="password" name="password"
                               value="${user.password}"
                               placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="passwordConfirm" class="col-sm-3 col-xs-3 control-label">确认密码</label>
                    <div class="col-sm-9 col-xs-9">
                        <input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm"
                               placeholder="确认密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="phoneNumber" class="col-sm-3 col-xs-3 control-label">手机号码</label>
                    <div class="col-sm-9 col-xs-9">
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber"
                               value="${user.phoneNumber}"
                               placeholder="请输入手机号码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-3 col-xs-3 control-label">邮&nbsp;&nbsp;&nbsp;箱</label>
                    <div class="col-sm-9 col-xs-9">
                        <input type="text" class="form-control" id="email" name="email"
                               value="${user.email}"
                               placeholder="请输入邮箱">
                    </div>
                </div>
                <div class="form-group">
                    <label for="verifyCode" class="col-sm-3 col-xs-3 control-label">验证码</label>
                    <div class="col-sm-6 col-xs-6">
                        <input type="text" class="form-control" id="verifyCode" name="verifyCode"
                               placeholder="请输入验证码">
                    </div>
                    <img class="col-sm-3 col-xs-3" style="height: 34px" id="captcha_img" onclick="reloadCode();"
                         alt="点击更换"
                         title="点击更换" src="${pageContext.request.contextPath}/user/verifyCode.pic">
                </div>
                <div class="form-group" style="text-align: center">
                    <button type="button" onclick="toValidate();"
                            class="btn btn-primary">&nbsp;注&nbsp;&nbsp;册&nbsp; </button>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="login.html">登录</a>
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
<script type="text/javascript">
    function reloadCode() {
        var time = new Date().getTime();
        document.getElementById("captcha_img").src = "${pageContext.request.contextPath}/user/verifyCode.pic?time=" + time;

    }
    function toValidate() {
        var val = new validate({
            rules: {
                username: "notEmpty",
                password: "password",
                passwordConfirm: "password",
                phoneNumber: "mobile",
                email: "email",
                verifyCode: "notEmpty",
            },
            submitFun: function () {
                var password = $('#password').val();
                var passwordConfirm = $('#passwordConfirm').val();
                //校验密码一致
                if (password != passwordConfirm) {
                    alert("两次密码不一致");
                    return;
                }
                //校验验证码
                var verifyCode = $('#verifyCode').val();
                $.post("${basePath}/user/checkVerifyCode.json", {"verifyCode": verifyCode}, function (result) {
                    if (result.code == 1) {
                        alert(result.desc);
                        reloadCode();
                    } else {
                        $('#login_form').submit();
                    }
                });
            }
        })
    }
</script>