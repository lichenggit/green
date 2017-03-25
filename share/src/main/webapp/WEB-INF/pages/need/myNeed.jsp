<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>


<div class="row">
    <div class="form-group col-md-3">
        <select class="form-control select-status">
            <option value="">全部</option>
            <option value="NORMAL">待解决</option>
            <option value="FINISHED">已解决</option>
        </select>
    </div>
</div>
<button type="button" class="btn btn-success" onclick="$.load.a('${basePath}/need/adminNeedHave.html');">我的需求回应</button>
<div class="panel panel-info">
    <div class="panel-body">

        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>物品类别</th>
                    <th>物品名称</th>
                    <th>描述</th>
                    <th>备注</th>
                    <th>发起时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="item" varStatus="index">
                    <tr>
                        <td>${item.categoryName}</td>
                        <td>${item.articleName}</td>
                        <td>${item.description}</td>
                        <td>${item.remark}</td>
                        <td><fmt:formatDate value="${item.createTime}" pattern="yyyy年MM月dd日"/></td>
                        <td><share:label items="${statusList}" key="${item.status}"/></td>
                        <c:choose>
                            <c:when test="${item.status eq 'NORMAL'}">
                                <td><a href="javascript:;" onclick="$.load.a('<c:url value="/need/updateNeedStatus.html?needId=${item.needId}&status=FINISHED"/> ');">不再需要</a></td>
                            </c:when>
                            <c:when test="${item.status eq 'FINISHED'}">
                                <td><a href="javascript:;" onclick="$.load.a('<c:url value="/need/updateNeedStatus.html?needId=${item.needId}&status=NORMAL"/> ');">再次申请</a></td>
                            </c:when>
                        </c:choose>
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