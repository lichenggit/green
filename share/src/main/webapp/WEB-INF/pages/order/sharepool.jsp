<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<c:forEach items="${sharepools}" var="item">
    ${item.article.articleName}
</c:forEach>
