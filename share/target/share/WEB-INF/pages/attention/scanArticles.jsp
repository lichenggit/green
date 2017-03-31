<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<div class="panel panel-info">
    <div class="panel-body">
        <div class="row">
            <c:forEach items="${list}" var="item" varStatus="index">
                <div class="col-sm-6 col-md-3">
                    <div class="thumbnail">
                        <a href="${basePath}/article/details.html?articleId=${item.article.articleId}"
                           target="_blank"
                           target="_blank"><img src="${basePath}/static/images/default.jpg"
                                                alt="通用的占位符缩略图"></a>
                        <div class="caption">
                            <h4>
                                <c:choose>
                                    <c:when test="${item.article.price eq null}">
                                        价格面议
                                    </c:when>
                                    <c:otherwise>￥${item.article.price}</c:otherwise>
                                </c:choose>
                            </h4>
                            <a href="${basePath}/article/details.html?articleId=${item.article.articleId}"
                               target="_blank">${item.article.articleName}:${item.article.description}</a>
                            <h4>
                                <span class="label label-info">${item.article.articleDegree}成新</span>
                                <c:choose>
                                    <c:when test="${item.article.sendable eq 'Y'}">
                                        <span class="label label-warning">送</span>
                                    </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${item.article.loanable eq 'Y'}">
                                        <span class="label label-warning">借</span>
                                    </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${item.article.tenantable eq 'Y'}">
                                        <span class="label label-warning">租</span>
                                    </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${item.article.markrtable eq 'Y'}">
                                        <span class="label label-warning">卖</span>
                                    </c:when>
                                </c:choose>
                            </h4>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>