<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<c:forEach items="${orders}" var="item">
    ${item.id}
</c:forEach>
