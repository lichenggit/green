<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>温馨提示</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-info col-sm-5">
        <div class="page-header">
            <h3>由于该物品所有者设置了他的联系方式的查看权限，只有
                <c:choose>
                    <c:when test="${accessEnable eq 'AUTHC'}">
                        <span class="label label-info">已认证的用户</span>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${accessEnable eq 'SHARER'}">
                        <span class="label label-info">已共享过物品的用户</span>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${accessEnable eq 'REQUEST'}">
                        <span class="label label-info">向我申请</span>
                    </c:when>
                </c:choose>才能查看</h3>
        </div>
        <div class="panel-body">
            ${gainContactsResultMsg}
        </div>
    </div>

</div>
</body>
</html>
