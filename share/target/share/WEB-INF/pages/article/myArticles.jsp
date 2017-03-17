<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<div class="row">
    <div class="form-group col-md-3">
        <select class="form-control select-status">
            <option value="">全部</option>
            <option value="NORMAL">正在出售</option>
            <option value="FINISHED">已售出</option>
            <option value="EXPIRED">已下架</option>
        </select>
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
                                        class="btn btn-default"><c:choose>
                                    <c:when test="${item.status eq 'NORMAL'}">一键下架</c:when>
                                    <c:when test="${item.status eq 'FINISHED'}">再次发布</c:when>
                                    <c:when test="${item.status eq 'EXPIRED'}">重新下架</c:when>
                                </c:choose>
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
    $(document).ready(function () {
        $('.select-status').val('${status}');
        $('.select-status').change(function () {
            var status = $(this).children('option:selected').val();//这就是selected的值
            var url = '${basePath}/article/adminArticles.html?status=' + status;
            $.load.a(url);
        })
    });
</script>

