<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>支付</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-info">
        <div class="panel-heading">支付</div>
        <div class="panel-body">
            <a type="button" class="btn btn-success" href="${basePath}/order/payorderForNoFree.html?shareOrderId=${shareOrderId}">支付</a>
        </div>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="studentAuthModal" tabindex="-1" role="dialog" aria-labelledby="myauthModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myauthModalLabel">学生认证</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="studentAuthForm" action="${basePath}/user/addStudentAuthc.json">
                    <div class="form-group">
                        <label for="schoolName" class="col-sm-2 control-label">学校</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="schoolName" name="schoolName" placeholder="请输入学校">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="studentName" class="col-sm-2 control-label">名字</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="studentName" name="studentName" placeholder="请输入名字">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="studentNumber" class="col-sm-2 control-label">学号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="studentNumber" name="studentNumber" placeholder="请输入学号">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button" class="btn btn-default" onclick="onStudentAuth();">登录</button>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
<script type="text/javascript">
    function onStudentAuthShow() {
        $('#studentAuthModal').modal('show')
    }
    function onStudentAuth() {
        $.post($('#studentAuthModal').attr("action"),$('#studentAuthModal').serialize(),function (data) {
            if(data.code==2){
                onNoLogin();
            }else if(data.code==0){
                $('#studentAuthModal').modal('hide')
                swal("认证成功","","success")
            }else {
                swal("认证失败","","error")
            }
        });
    }
</script>
</html>