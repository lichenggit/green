<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>物品联系方式</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-info">
        <div class="panel-heading">联系方式</div>
        <div class="panel-body">
            <div class="media">
                <div class="media-body">
                    <h4 class="media-heading">订单（已支付） <fmt:formatDate value="${order.createTime}"
                                                                      pattern="yyyy-MM-dd HH:mm"></fmt:formatDate>
                    </h4>
                </div>
                <c:forEach items="${order.orderDetailsList}" var="orderDetails">
                    <div class="media">
                        <a class="pull-left" href="#">
                            <img class="media-object" src="${basePath}/static/images/default.jpg"
                                 alt="共享物品图片" style="max-width: 100px;max-height: 100px">
                        </a>
                        <div class="media-body">
                            <h4 class="media-heading">${orderDetails.article.articleName}</h4>
                                ${orderDetails.article.description}
                        </div>
                            ${orderDetails.article.user.username}|
                            ${orderDetails.article.user.phoneNumber}|
                            ${orderDetails.article.user.email}|
                            ${orderDetails.article.user.address}
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
</script>
</html>