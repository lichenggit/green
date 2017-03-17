<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<style>
    .error {
        color: red;
    }
</style>
<form class="form" role="form" id="tableForm" method="post" action="${basePath}/table/createTable.html">
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="row">
                <div class="form-group col-sm-4">
                    <label for="tableNameCn">表格的名称:</label>
                    <input type="text" class="form-control" id="tableNameCn" name="tableNameCn" required
                           placeholder="请输入表格的名称">
                </div>
                <div class="form-group col-sm-4">
                    <label for="remark">说明备注:</label>
                    <input type="text" class="form-control" id="remark" name="remark" required placeholder="请输入表格的备注">
                </div>
            </div>
            <div class="row">
                <div class="form-group col-sm-4">
                    <label for="startTimeStr">有效时限:</label>
                    <input type="text" class="form-control form_datetime" id="startTimeStr" required readonly
                           name="startTimeStr"
                           placeholder="起始时间">
                </div>
                <div class="form-group col-sm-4">
                    <label for="endTimeStr">&nbsp;</label>
                    <input type="text" class="form-control form_datetime" id="endTimeStr" required readonly
                           name="endTimeStr"
                           placeholder="截止时间">
                </div>
            </div>
        </div>
        <br/>
        <div class="table-responsive">
            <table class="table" id="attrs-table">
                <thead>
                <tr>
                    <th>列的名称</th>
                    <th>约束条件</th>
                    <th>说明备注</th>
                    <th>是否一定要填</th>
                </tr>
                </thead>
                <tbody>
                <tr number="0">
                    <td>
                        <input type="text" class="form-control" readonly name="columnInfoList[0].columnNameCn"
                               value="学号" required>
                    </td>
                    <td>
                        <select class="form-control" readonly name="columnInfoList[0].columnConstraint">
                            <option value="STUDENT_ID">学号</option>
                        </select>
                    </td>
                    <td>
                        <input type="text" class="form-control" readonly name="columnInfoList[0].remark" value="请填写学号"
                               required>
                    </td>
                    <td>
                        <input type="checkbox" value="N" checked onclick="return false" name="columnInfoList[0].isNull">
                    </td>
                </tr>
                <tr number="1">
                    <td>
                        <input type="text" class="form-control" readonly name="columnInfoList[1].columnNameCn"
                               value="姓名" required>
                    </td>
                    <td>
                        <select class="form-control" readonly name="columnInfoList[1].columnConstraint">
                            <option value="STUDENT_NAME">姓名</option>
                        </select>
                    </td>
                    <td>
                        <input type="text" class="form-control" readonly name="columnInfoList[1].remark" value="请填写姓名"
                               required>
                    </td>
                    <td>
                        <input type="checkbox" value="N" checked onclick="return false" name="columnInfoList[1].isNull">
                    </td>
                </tr>
                <tr number="2">
                    <td>
                        <input type="text" class="form-control" name="columnInfoList[2].columnNameCn" required
                               placeholder="列的名称">
                    </td>
                    <td>
                        <select class="form-control" name="columnInfoList[2].columnConstraint">
                            <option value="STRING">普通文本</option>
                            <option value="TEXT">大段篇章</option>
                            <option value="IDCARD">身份证</option>
                            <option value="MOBILE">手机号码</option>
                            <option value="EMAIL">电子邮箱</option>
                            <option value="INTEGER">整数</option>
                            <option value="DOUBLE">小数</option>
                            <option value="TIMESTAMP">时间</option>
                        </select>
                    </td>
                    <td>
                        <input type="text" class="form-control" name="columnInfoList[2].remark" required
                               placeholder="填写说明">
                    </td>
                    <td>
                        <input type="checkbox" value="N" name="columnInfoList[2].isNull">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <button type="button" class="btn btn-info" onclick="addNewLine();">
            <span class="glyphicon glyphicon-plus"></span>
            添加一个列
        </button>
        <button type="submit" class="btn btn-success">提交</button>
    </div>
</form>

<script type="text/javascript">
    /*时间选择*/
    $(".form_datetime").datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        format: 'yyyy-mm-dd hh:ii'
    });

    /*表单校验*/
    $.validator.setDefaults({
        submitHandler: function () {
            $.load.form('tableForm', '创建成功');
        }
    });
    $().ready(function () {
        // 在键盘按下并释放及提交后验证提交表单
        $("#tableForm").validate({
            rules: {
            },
            messages: {
                tableNameCn: "表格名称不能为空",
                remark: "表格备注不能为空",
                startTimeStr: "表格有效起始时间不能为空",
                endTimeStr: "表格有效截止时间不能为空"
            }
        });
    });


    var names = ['columnNameCn', 'columnConstraint', 'remark', 'isNull'];
    /*添加一行*/
    function addNewLine() {
        var row = $('#attrs-table tbody>tr:last').clone(false);
        var num = parseInt(row.attr("number")) + 1;
        row.attr("number", num);
        $.each(row.find("td"), function (index) {
            var name = "columnInfoList[" + num + "]." + names[index];
            $(this).children().attr("name", name);
        });
        $("td input:text", row).val("");
        row.insertAfter('#attrs-table tbody>tr:last');
    }
</script>

