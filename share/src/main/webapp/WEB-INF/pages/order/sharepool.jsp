<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<div class="panel panel-info">
    <div class="panel-heading">共享池</div>
    <div class="panel-body">
        <ul class="list-group">
            <c:forEach items="${sharepools}" var="item">
                <li class="list-group-item">
                    <div class="media">
                        <input type="checkbox" class="pull-right article-checkbox"  value="${item.article.articleId}">
                        <a class="pull-left" href="#">
                            <img class="media-object" src="${basePath}/static/images/default.jpg"
                                 alt="共享物品图片" style="max-width: 100px;max-height: 100px">
                        </a>
                        <div class="media-body">
                            <h4 class="media-heading">${item.article.articleName}</h4>
                                ${item.article.description}
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <button type="button" class="btn btn-danger btn-pool-submit pull-right">提交</button>
    </div>
</div>
<script type="text/javascript">
    $('.btn-pool-submit').click(function () {
        var orderDetailsList = [];
        $(".article-checkbox:checked").each(function (index) {
            var orderDetails = {};
            orderDetails.articleCount = 1;
            orderDetails.articleId = $(this).val();
            orderDetailsList.push(orderDetails);
        });
        if(orderDetailsList.length==0){
            swal("还没选择物品");
        }else {
            addorder(orderDetailsList);
        }
    });
    function addorder(orderDetailsList) {
        $.ajax({
            type: "post",
            url: "${basePath}/order/addorder.json",
            data: JSON.stringify(orderDetailsList),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                if (data.code == 2) {
                    onNoLogin();
                } else if (data.code == 0) {
                    $.load.a("${basePath}/order/adminshareorders.html");
                    window.open("${basePath}/order/readypayorder.html?shareOrderId=" + data.desc);
                }else {
                    swal(data.desc);
                }
            }
        })
    }

</script>