<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<button type="button" class="btn btn-success" onclick="$.load.a('${basePath}/need/adminNeed.html');">我的需求申请</button>
<div class="panel panel-info">
    <div class="panel-body">

        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>物品名称</th>
                    <th>捐赠者</th>
                    <th>捐赠者手机</th>
                    <th>发起时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="item" varStatus="index">
                    <tr>
                        <td>${item.needName}</td>
                        <td>${item.username}</td>
                        <td>${item.phoneNumber}</td>
                        <td><fmt:formatDate value="${item.createTime}" pattern="yyyy年MM月dd日"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('.select-status').val('${status}');
        $('.select-status').change(function () {
            var status = $(this).children('option:selected').val();//这就是selected的值
            var url = '${basePath}/need/adminNeed.html?status=' + status;
            $.load.a(url);
        })
    });
</script>