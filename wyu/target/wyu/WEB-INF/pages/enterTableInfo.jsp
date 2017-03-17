<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>填写表格-${tableInfo.tableNameCn}</title>
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
        <div class="panel-heading">
            ${tableInfo.tableNameCn} - ${tableInfo.remark}
        </div>
        <div class="panel-body">
            <form class="form-horizontal" role="form" action="${basePath}/submitTable.json"
                  id="table_form" method="post">
                <input type="hidden" name="tableNameEn" value="${tableInfo.tableNameEn}">
                <input type="hidden" disabled id="creator" name="creator" value="${tableInfo.creator}">
                <c:forEach items="${tableInfo.columnInfoList}" var="item" varStatus="index">
                    <div class="form-group">
                        <label for="${item.columnNameEn}"
                               class="col-sm-3 col-xs-3 control-label">${item.columnNameCn}</label>
                        <div class="col-sm-9 col-xs-9">
                            <c:choose>
                                <c:when test="${item.columnConstraint == 'INTEGER'}">
                                    <input type="text" class="form-control" id="${item.columnNameEn}"
                                           name="${item.columnNameEn}" digits="true"
                                           <c:if test="${item.isNull == 'N'}">required</c:if>
                                           placeholder="${item.remark}">
                                </c:when>
                                <c:when test="${item.columnConstraint == 'DOUBLE'}">
                                    <input type="text" class="form-control" id="${item.columnNameEn}"
                                           name="${item.columnNameEn}" number="true"
                                           <c:if test="${item.isNull == 'N'}">required</c:if>
                                           placeholder="${item.remark}">
                                </c:when>
                                <c:when test="${item.columnConstraint == 'EMAIL'}">
                                    <input type="email" class="form-control" id="${item.columnNameEn}"
                                           name="${item.columnNameEn}" email="true"
                                           <c:if test="${item.isNull == 'N'}">required</c:if>
                                           placeholder="${item.remark}">
                                </c:when>
                                <c:when test="${item.columnConstraint == 'IDCARD'}">
                                    <input type="text" class="form-control" id="${item.columnNameEn}"
                                           name="${item.columnNameEn}" idCard="true"
                                           <c:if test="${item.isNull == 'N'}">required</c:if>
                                           placeholder="${item.remark}">
                                </c:when>
                                <c:when test="${item.columnConstraint == 'MOBILE'}">
                                    <input type="text" class="form-control" id="${item.columnNameEn}"
                                           name="columnMap[${item.columnNameEn}]" mobile="true"
                                           <c:if test="${item.isNull == 'N'}">required</c:if>
                                           placeholder="${item.remark}">
                                </c:when>
                                <c:when test="${item.columnConstraint == 'TEXT'}">
                                    <textarea class="form-control" rows="3" id="${item.columnNameEn}"
                                              name="columnMap[${item.columnNameEn}]"
                                              <c:if test="${item.isNull == 'N'}">required</c:if>
                                              placeholder="${item.remark}"></textarea>
                                </c:when>
                                <c:when test="${item.columnConstraint == 'TIMESTAMP'}">
                                    <input type="text" class="form-control form_date" id="${item.columnNameEn}"
                                           name="columnMap[${item.columnNameEn}]"
                                           <c:if test="${item.isNull == 'N'}">required</c:if> readonly
                                           placeholder="${item.remark}">
                                </c:when>
                                <c:otherwise>
                                    <input type="text" class="form-control" id="${item.columnNameEn}"
                                           name="${item.columnNameEn}"
                                           <c:if test="${item.isNull == 'N'}">required</c:if>
                                           placeholder="${item.remark}">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
                <div class="form-group">
                    <label for="verifyCode" class="col-sm-3 col-xs-3 control-label">验证码</label>
                    <div class="col-sm-6 col-xs-6">
                        <input type="text" class="form-control" id="verifyCode" name="verifyCode" required
                               rangelength="[4,4]" remote="${basePath}/user/checkVerifyCode.json"
                               placeholder="请输入验证码">
                    </div>
                    <img class="col-sm-3 col-xs-3" style="height: 34px" id="captcha_img" onclick="reloadCode();"
                         alt="点击更换"
                         title="点击更换" src="${pageContext.request.contextPath}/user/verifyCode.pic">
                </div>
                <div class="form-group" style="text-align: center">
                    <button type="submit"
                            class="btn btn-primary">&nbsp;提&nbsp;&nbsp;交&nbsp; </button>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" target="_blank">提交不成功？</a>
                    <div class="form-group">
                        <label class="col-sm-3 col-xs-3 control-label"></label>
                        <span id="errMsg" class="help-block error">${errMsg}</span>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script src="${basePath}/static/js/jquery-3.0.0.min.js"></script>
<%--表单校验--%>
<script src="${basePath}/static/jquery-validation-1.14.0/js/jquery.validate.min.js"></script>
<script src="${basePath}/static/jquery-validation-1.14.0/js/additional-methods.min.js"></script>
<script src="${basePath}/static/jquery-validation-1.14.0/js/messages_zh.min.js"></script>
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
            $.ajax({
                url: $("#table_form").attr("action"),
                type: "POST",
                dataType: "json",

                data: $("#table_form").serialize(),
                success: function (result) {
                    if (result.code == 0) {
                        swal({
                                    title: "提交成功",
                                    type: "success",
                                    confirmButtonText: "确定",
                                    closeOnConfirm: false,
                                },
                                function (isConfirm) {
                                    window.location.href =
                                            "${basePath}/table/admin.html";
                                });
                    } else {
                        swal("提交失败", "请以附件方式发送到班长邮箱",
                                "error");
                    }
                },
                error: function (result) {
                    swal("提交失败", "请以附件方式发送到班长邮箱",
                            "error");
                }
            });
        }
    });

    $().ready(function () {
        $("#table_form").validate({
            onfocusout: false,
            onkeyup: false,
            onclick: false,
            rules: {
                studentId: {
                    remote: {
                        url: "${basePath}/student/checkStudentId.json",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json",           //接受数据格式
                        data: {                     //要传递的数据
                            name: function () {
                                return $("#name").val();
                            },
                            creator: function () {
                                return $("#creator").val();
                            }
                        }
                    }
                }
            },
            messages: {
                verifyCode: {
                    required: "验证码不能为空",
                    rangelength: "验证码为四位",
                    remote: "验证码错误"
                },
                studentId: {
                    required: "学号不能为空",
                    remote: "学号和姓名不匹配"
                },
                name: {
                    required: "姓名不能为空"
                }
            }
        });
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
        document.getElementById("captcha_img").src = "${basePath}/user/verifyCode.pic?time=" + time;

    }
</script>