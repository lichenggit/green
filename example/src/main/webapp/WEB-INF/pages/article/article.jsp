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
        </form>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" onclick="searchInfo();">提交</button>
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
                            <h3>￥${item.price}</h3>
                            <a href="${basePath}/article/details.html?articleId=${item.articleId}"
                               target="_blank">${item.articleName}:${item.description}</a>
                            <h4>
                                <span class="label label-danger">${item.articleDegree}成新</span>
                                <span class="label label-danger">自营</span>
                            </h4>
                            <div class="btn-group">
                                <button id="${item.articleId}" type="button"
                                        class="btn btn-default btn-attention">关注
                                </button>
                                <button type="button" class="btn btn-default btn_gain"
                                        path='<c:url value="/article/gainContacts.html?articleId=${item.articleId}&userId=${item.userId}"/> '>
                                    点击购买
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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
                    domEle.innerHTML = '已关注';
                } else {
                    domEle.innerHTML = '关注';
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
        $.ajax({
            type: "POST",
            url: "${basePath}/attention/addAttention.json",
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
