<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>填写情况-${tableInfo.tableNameCn}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="${basePath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet"
          media="screen">
    <!-- 弹框 CSS-->
    <link href="${basePath}/static/sweet-alert/css/sweet-alert.css" rel="stylesheet" type="text/css">

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
        <div class="panel-heading">
            ${tableInfo.tableNameCn} - ${tableInfo.remark}
        </div>
        <div class="table-responsive">
            <table class="table table-hover table-striped" id="attrs-table">
                <thead>
                <tr>
                    <c:forEach items="${tableInfo.columnInfoList}" var="item" varStatus="index">
                        <th>${item.columnNameCn}</th>
                    </c:forEach>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${rows}" var="item" varStatus="index">
                    <tr>
                        <c:forEach items="${item}" var="entry">
                            <c:if test="${entry.key != 'ID'}">
                                <td>${entry.value}</td>
                            </c:if>
                        </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="panel-body">
            <button type="button" class="btn btn-success" onclick="location='${basePath}/table/export/${tableInfo.id}'"><span
                    class="glyphicon glyphicon-download-alt"></span> 导出
            </button>
        </div>
    </div>
</div>
</body>
</html>
<%--表单校验--%>
<script src="https://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="${basePath}/static/jquery-validation-1.14.0/js/jquery.validate.min.js"></script>
<script src="${basePath}/static/jquery-validation-1.14.0/js/additional-methods.min.js"></script>
<script src="${basePath}/static/jquery-validation-1.14.0/js/messages_zh.min.js"></script>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<%--<script src="${basePath}/static/js/jquery-3.0.0.min.js"></script>--%>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${basePath}/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="${basePath}/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
        src="${basePath}/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- 弹框 JS-->
<script src="${basePath}/static/sweet-alert/js/sweet-alert.js"></script>
<script type="text/javascript">
    $.validator.setDefaults({
        submitHandler: function () {
//            $("#table_form").submit();
            $.ajax({
                url: $("#table_form").attr("action"),
                type: "POST",
                dataType: "json",
                data: $("#table_form").serializeArray(),
                success: function (result) {
                    if (result.code == 0) {
                        swal({
                                    title: "提交成功",
                                    type: "success",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: false,
                                },
                                function (isConfirm) {
                                    window.location.href = "${basePath}/table/admin.html";
                                });
                    } else {
                        swal("提交失败", "请以附件方式发送到班长邮箱", "error");
                    }
                },
                error: function (result) {
                    swal("提交失败", "请以附件方式发送到班长邮箱", "error");
                }
            });
        }
    });
    $().ready(function () {
        $("#table_form").validate();
    });

    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: 'yyyy-mm-dd hh:ii'
    });
    function reloadCode() {
        var time = new Date().getTime();
        document.getElementById("captcha_img").src = "${pageContext.request.contextPath}/user/verifyCode.pic?time=" + time;

    }
</script>