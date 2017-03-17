<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<div class="panel panel-info">
    <div class="panel-heading">
        <button type="button" class="btn btn-danger" onclick="$.load.aShowAlert('${basePath}/student/clearAllStudents.html',null,'确定要删除吗?','删除成功');">
            删除所有
        </button>
        <button type="button" class="btn btn-info" onclick="openModal('N')">追加成员</button>
        <button type="button" class="btn btn-warning" onclick="openModal('Y')">重新上传</button>
    </div>
    <div class="panel-body">
        <div class="table-responsive">
            <table class="table table-hover table-striped" id="attrs-table">
                <caption>班级成员(${fn:length(students)}人)</caption>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>学号</th>
                    <th>姓名</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${students}" var="item" varStatus="index">
                    <tr>
                        <td>${index.count}</td>
                        <td>${item.studentId}</td>
                        <td>${item.studentName}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form" id="userForm" role="form" method="post" action="${basePath}/student/uploadStudentsFile.html"
                  enctype="multipart/form-data">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <input type="hidden" name="uploadType" id="uploadType">
                        <input type="file" name="studentsFile" accept="application/vnd.ms-excel" required="required">
                        <p class="help-block">请选择Excel表格文件(.lsx .xlsx)</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">提交</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script type="text/javascript">
    function openModal(type) {
        $('#uploadType').val(type);
        if (type == 'N') {
            $('#myModalLabel').html('追加班级成员信息');
        } else {
            $('#myModalLabel').html('重新上传班级成员信息');
        }
        $('#myModal').modal('show')
    }
</script>