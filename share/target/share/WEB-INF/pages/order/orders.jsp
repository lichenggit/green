<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<div class="panel panel-info">
    <div class="panel-heading">共享订单</div>
    <div class="panel-body">
        <ul class="list-group">
            <c:forEach items="${orders}" var="item">
                <li class="list-group-item">
                    <div class="media">
                        <div class="media-body">
                            <h4 class="media-heading">订单  <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate>
                                <c:if test="${item.status == 'HAS_PAY'}">
                                    <button type="button" class="btn btn-success pull-right btn-finish-order" data-shareOrderId="${item.id}">已完成</button>
                                </c:if>
                                <c:if test="${item.status == 'READY_PAY'}">
                                    <button type="button" class="btn btn-warning pull-right btn-ready-pay" data-shareOrderId="${item.id}">待支付</button>
                                </c:if>
                            </h4>
                        </div>
                        <c:forEach items="${item.orderDetailsList}" var="orderDetails">
                            <div class="media">
                                <a class="pull-left" href="#">
                                    <img class="media-object" src="${basePath}/static/images/default.jpg"
                                         alt="共享物品图片" style="max-width: 100px;max-height: 100px">
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading">${orderDetails.article.articleName}</h4>
                                        ${orderDetails.article.description}
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('.btn-ready-pay').click(function () {
            window.location.href="${basePath}/order/readypayorder.html?shareOrderId="+$(this).attr('data-shareOrderId');
        });
        $('.btn-finish-order').click(function () {
            window.open("${basePath}/order/gainContacts.html?shareOrderId="+$(this).attr('data-shareOrderId'));
        });
    });
</script>