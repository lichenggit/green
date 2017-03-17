<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<div class="panel panel-info">
    <div class="panel-body">
        <form id="conditionForm" class="form-horizontal" role="form" action="${basePath}/article/index.html">
            <div class="form-group">
                <label for="categoryId" class="col-sm-2 control-label">物品类别</label>
                <div class="col-sm-10">
                    <select class="form-control" id="categoryId" name="categoryId"></select>
                </div>
            </div>
            <div class="form-group">
                <label for="articleDegree" class="col-sm-2 control-label">新旧</label>
                <div class="col-sm-10">
                    <select class="form-control" id="articleDegree" name="articleDegree">
                        <option value="">不限</option>
                        <option value="1">1成新</option>
                        <option value="2">2成新</option>
                        <option value="3">3成新</option>
                        <option value="4">4成新</option>
                        <option value="5">5成新</option>
                        <option value="6">6成新</option>
                        <option value="7">7成新</option>
                        <option value="8">8成新</option>
                        <option value="9">9成新</option>
                        <option value="10">10成新</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="priceLow" class="col-sm-2 control-label">价格</label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" name="priceLow" id="priceLow" value="${condition.priceLow}"
                           placeholder="价格下限">
                </div>
                <div class="col-sm-2">
                    <input type="text" class="form-control" name="priceHigh" id="priceHigh"
                           value="${condition.priceHigh}" placeholder="价格上限">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">交易方式</label>
                <div class="col-sm-10">
                    <label class="checkbox-inline">
                        <input type="checkbox" ${condition.sendable eq 'Y'?"checked":""} value="Y"
                               name="sendable">可送
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" ${condition.loanable eq 'Y'?"checked":""} value="Y"
                               name="loanable">可借
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" ${condition.tenantable eq 'Y'?"checked":""} value="Y"
                               name="tenantable">可租
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" ${condition.markrtable eq 'Y'?"checked":""} value="Y"
                               name="markrtable">可卖
                    </label>
                </div>
            </div>
        </form>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-success" onclick="searchInfo();">开始检索</button>
            </div>
        </div>
    </div>
</div>
<div class="panel panel-info">
    <div class="panel-body">
        <div class="row">
            <c:forEach items="${list}" var="item" varStatus="index">
                <div class="col-sm-6 col-md-3">
                    <div class="thumbnail">
                        <a href="${basePath}/article/details.html?articleId=${item.articleId}" target="_blank"
                           target="_blank"><img src="${basePath}/static/images/default.jpg"
                                                alt="通用的占位符缩略图"></a>
                        <div class="caption">
                            <h4>
                                <c:choose>
                                    <c:when test="${item.price eq null}">
                                        价格面议
                                    </c:when>
                                    <c:otherwise>￥${item.price}</c:otherwise>
                                </c:choose>
                            </h4>
                            <a href="${basePath}/article/details.html?articleId=${item.articleId}"
                               target="_blank">${item.articleName}:${item.description}</a>
                            <h4>
                                <span class="label label-info">${item.articleDegree}成新</span>
                                <c:choose>
                                    <c:when test="${item.sendable eq 'Y'}">
                                        <span class="label label-warning">送</span>
                                    </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${item.loanable eq 'Y'}">
                                        <span class="label label-warning">借</span>
                                    </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${item.tenantable eq 'Y'}">
                                        <span class="label label-warning">租</span>
                                    </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${item.markrtable eq 'Y'}">
                                        <span class="label label-warning">卖</span>
                                    </c:when>
                                </c:choose>
                                    <%--<span class="label label-warning">${item.accessEnable}</span>--%></h4>
                            <div class="btn-group">
                                <button id="${item.articleId}" type="button" data-toggle="tooltip" title=""
                                        class="btn btn-default btn-attention">关注
                                </button>
                                <button type="button" class="btn btn-default btn_gain"
                                        path='<c:url value="/article/gainContacts.html?articleId=${item.articleId}&userId=${item.userId}"/> '>
                                    一键获取
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<script type="text/javascript">
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

    $(document).ready(function () {
        //初始化类别
        $.ajax({
            type: "GET",
            url: "${basePath}/article/getCategorys.json",
            dataType: "json",
            success: function (result) {
                if (result.code == 0) {
                    var obj = {};
                    obj.categoryId = '';
                    obj.categoryName = '不限';
                    result.data.unshift(obj);
                    $.each(result.data, function (index, item) {
                        $('#categoryId').append($("<option/>", {
                            value: item.categoryId,
                            text: item.categoryName
                        }));
                    });
                    //设置当前选项
                    $('#categoryId').val('${condition.categoryId}');
                }
            }
        });
        //初始化新旧程度
        $('#articleDegree').val('${condition.articleDegree}');
        //初始化关注按钮
        $('.btn-attention').each(function (i) {
            this.onclick = function () {
                addAttention(this, this.id);
            };
            setAttentionStatus(this, this.id);
        });
        //初始化购买按钮
        $('.btn_gain').each(function (i) {
            this.onclick = function () {
                var url = $(this).attr("path");
                window.open(url);
            };
        });

        //模态框
        $("#myModal").on("hidden.bs.modal", function () {
            $(this).removeData("bs.modal");
        });
    });
    //查询
    function searchInfo() {
        $.load.form("conditionForm")
    }
    //关注
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
