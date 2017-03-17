<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<h3>共享物品</h3>
<div class="row">
    <c:forEach items="${articles}" var="item" varStatus="index">
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <a href="${basePath}/article/details.html?articleId=${item.articleId}" target="_blank"><img
                        src="${basePath}/static/images/default.jpg"
                        alt="通用的占位符缩略图"></a>
                <div class="caption">
                    <h3>￥${item.price}</h3>
                    <a href="${basePath}/article/details.html?articleId=${item.articleId}"
                       target="_blank">${item.articleName}:${item.description}</a>
                    <h4>
                        <span class="label label-danger">${item.articleDegree}成新</span>
                        <span class="label label-danger">自营</span>
                    </h4>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<h3>需求申请</h3>
<div class="panel panel-info">
    <div class="panel-body">
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>物品类别</th>
                    <th>物品名称</th>
                    <th>描述</th>
                    <th>备注</th>
                    <th>发起时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${needs}" var="item" varStatus="index">
                    <tr>
                        <td>${item.categoryName}</td>
                        <td>${item.articleName}</td>
                        <td>${item.description}</td>
                        <td>${item.remark}</td>
                        <td><fmt:formatDate value="${item.createTime}" pattern="yyyy年MM月dd日"/></td>
                        <td><a href="javascript:;">我有此物</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
