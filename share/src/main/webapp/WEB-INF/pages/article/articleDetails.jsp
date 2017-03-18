<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>
<html>
<head>
    <title>详情页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-info">
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-6 col-md-6">
                    <a href="#" class="thumbnail">
                        <img src="${basePath}/static/images/default.jpg"
                             alt="通用的占位符缩略图">
                    </a>
                </div>
                <div class="col-sm-6 col-md-6">
                    <div class="jumbotron">
                        <h3>名称：${item.articleName}</h3>
                        <p>类别：${item.categoryName}</p>
                        <p>细节：${item.description}</p>
                        <p>新旧：${item.articleDegree}成新</p>
                        <p>价格：${item.price}</p>
                        <p>获取权限:
                            <c:choose>
                                <c:when test="${item.accessEnable eq 'NONE'}">
                                    <span class="label label-default">不限</span>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${item.accessEnable eq 'AUTHC'}">
                                    <span class="label label-default">已认证用户</span>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${item.accessEnable eq 'SHARER'}">
                                    <span class="label label-default">已共享过物品的用户</span>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${item.accessEnable eq 'REQUEST'}">
                                    <span class="label label-default">向我申请</span>
                                </c:when>
                            </c:choose>
                        </p>
                        <p><c:choose>
                            <c:when test="${item.sendable eq 'Y'}">
                                <span class="label label-info">可送</span>
                            </c:when>
                        </c:choose>
                            <c:choose>
                                <c:when test="${item.loanable eq 'Y'}">
                                    <span class="label label-info">可借</span>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${item.tenantable eq 'Y'}">
                                    <span class="label label-info">可租</span>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${item.markrtable eq 'Y'}">
                                    <span class="label label-info">可卖</span>
                                </c:when>
                            </c:choose></p>
                    </div>
                    <p>
                        <a id="${item.articleId}" class="btn btn-warning btn-lg btn-attention"
                           role="button">
                            关注
                        </a>
                        <a href="${basePath}/article/gainContacts.html?articleId=${item.articleId}&userId=${item.userId}"
                           target="_blank" class="btn btn-success btn-lg" role="button">
                            一键获取
                        </a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function () {
        //初始化关注按钮
        $('.btn-attention').each(function (i) {
            this.onclick = function () {
                addAttention(this, this.id);
            };
            setAttentionStatus(this, this.id);
        });
    });

    //设置关注按钮状态
    function setAttentionStatus(domEle, articleId) {
        $.ajax({
            type: "GET",
            url: "${basePath}/attention/checkAttention.json",
            data: {"articleId": articleId},
            dataType: "json",
            success: function (result) {
                if (result.code == 0) {
                    $(domEle).text("已关注");
                    $(domEle).attr("title", "点击取消关注");
                    $(domEle).val("1");
                    $(domEle).removeClass("btn-default");
                    $(domEle).addClass("btn-success");
                } else {
                    $(domEle).text("关注");
                    $(domEle).attr("title", "点击关注");
                    $(domEle).val("0");
                    $(domEle).removeClass("btn-success");
                    $(domEle).addClass("btn-default");
                }
            }
        });
    }

    //加关注
    function addAttention(domEle, articleId) {
        var value = $(domEle).val();
        var url = "${basePath}/attention/addAttention.json";//关注
        if (value == 1) {
            url = "${basePath}/attention/removeAttention.json";//取消关注
        }
        $.ajax({
            type: "POST",
            url: url,
            data: {"articleId": articleId},
            dataType: "json",
            success: function (result) {
                if (result.code == 0) {
                    setAttentionStatus(domEle, articleId);
                }
            },
            error: function () {
                alert("请登录");
            }
        });
    }
</script>
