<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<div class="panel panel-info">
    <div class="panel-body">

        <div class="table-responsive">
            <table class="table table-hover table-striped" id="attrs-table">
                <thead>
                <tr>
                    <th>表格名称</th>
                    <th>说明备注</th>
                    <th>有限时间</th>
                    <th>创建时间</th>
                    <th>填写链接</th>
                    <th>填写情况</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${myTables}" var="item" varStatus="index">
                    <tr>
                        <td>${item.tableNameCn}</td>
                        <td>${item.remark}</td>
                        <td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd"/> - <fmt:formatDate
                                value="${item.endTime}" pattern="yyyy-MM-dd"/></td>
                        <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></td>
                        <td><a href="${basePath}/table/enterTableInfo/${item.id}" target="_blank">http://www.192.168.0.100:8080/wyu/table/enterTableInfo/${item.id}</a>
                        </td>
                        <td><a href="${basePath}/table/tableExecution/${item.id}" target="_blank">查看</a></td>
                        <td>
                            <button type="button" class="btn btn-success"
                                    onclick="location='${basePath}/table/export/${item.id}'">
                                <span class="glyphicon glyphicon-download-alt"></span> 导出
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
