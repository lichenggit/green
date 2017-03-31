<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="${basePath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${basePath}/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet"
      media="screen">
<!-- 弹框 CSS-->
<link href="${basePath}/static/sweet-alert/css/sweet-alert.css" rel="stylesheet" type="text/css">

<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file:// 引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->

<style>
    .error {
        color: red;
    }
</style>

<%--jQuery--%>
<script src="${basePath}/static/js/jquery-3.0.0.min.js"></script>
<%--表单校验--%>
<script src="${basePath}/static/jquery-validation-1.14.0/js/jquery.validate.min.js"></script>
<script src="${basePath}/static/jquery-validation-1.14.0/js/additional-methods.min.js"></script>
<script src="${basePath}/static/jquery-validation-1.14.0/js/messages_zh.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${basePath}/static/bootstrap/js/bootstrap.min.js"></script>
<%--时间选择--%>
<script src="${basePath}/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="${basePath}/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- 弹框 JS-->
<script src="${basePath}/static/sweet-alert/js/sweet-alert.js"></script>
<!-- ellipsis JS-->
<script src="${basePath}/static/js/jquery.ellipsis.min.js"></script>
<%--自定义--%>
<script src="${basePath}/static/js/main.js"></script>
<script src="${basePath}/static/js/validate.js"></script>

<!-- 模态框（Modal） -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">登录</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form"
                      action="${pageContext.request.contextPath}/user/login.json"
                      id="modal_login_form"
                      method="post">
                    <div class="form-group">
                        <label for="username"
                               class="col-sm-3 col-xs-3 control-label">账&nbsp;&nbsp;&nbsp;号</label>
                        <div class="col-sm-9 col-xs-9">
                            <input type="text" class="form-control" id="username" name="username"
                                   placeholder="账号 / 手机号码 / 邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password"
                               class="col-sm-3 col-xs-3 control-label">密&nbsp;&nbsp;&nbsp;码</label>
                        <div class="col-sm-9 col-xs-9">
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="密码">
                        </div>
                    </div>
                    <div class="form-group">
                         <div class="col-sm-offset-3 col-sm-10">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" checked name="rememberMe">请记住我
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group" style="text-align: center">
                        <button type="button" onclick="loginSubmit();"
                                class="btn btn-primary">&nbsp;登&nbsp;&nbsp;录&nbsp; </button>
                        &nbsp;&nbsp;&nbsp;&nbsp;<a
                            href="${basePath}/user/register.html">注册</a>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script type="text/javascript">
    function onNoLogin() {
        $('#loginModal').modal('show')
    }
    function loginSubmit() {
     $.post($('#modal_login_form').attr('action'),$('#modal_login_form').serialize(),function (data) {
         if (data.code==0){
             $('#loginModal').modal('hide')
         }else {
             swal("登录失败","","error");
         }
     });
    };
$(document).ready(function () {
    //初始化下单按钮
    $('.btn_gain').each(function (i) {
        this.onclick = function () {
            var articleId = $(this).attr("data-articleId");
            var url = "${basePath}/order/addorder.json";
            var orderDetails = {};
            orderDetails.articleCount = 1;
            orderDetails.articleId = articleId;
            var orderDetailsList = [];
            orderDetailsList.push(orderDetails);
            $.ajax({
                type: "post",
                url: url,
                data: JSON.stringify(orderDetailsList),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.code == 2) {
                        onNoLogin();
                    } else if (data.code == 0) {
                        window.open("${basePath}/order/readypayorder.html?shareOrderId=" + data.desc);
                    }else if(data.code){
                        swal(data.desc);
                    }
                }
            })
        };
    });
    //初始化加入共享池按钮
    $('.btn_pool').each(function (i) {
        this.onclick = function () {
            var url = "${basePath}/order/addtosharepool.json";
            var articleId = $(this).attr("data-articleId");
            var ownerId = $(this).attr("data-ownerId");
            $.post(url, {"articleId": articleId, "ownerId": ownerId}, function (data) {
                if (data.code == 2) {
                    onNoLogin();
                } else if (data.code == 0) {

                    swal("已经加入了共享池", "", "success");
                }
            }, "json");
        };
    });
});
</script> 