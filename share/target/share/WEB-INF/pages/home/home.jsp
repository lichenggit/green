<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<div class="row">
    <div class="col-sm-6 col-md-6">
        <div class="jumbotron" href="#">
            <h3>共享物品资源区</h3>
            <a href="javascript:;$.load.a('${basePath}/list/article.html')" class="thumbnail">
                <img src="${basePath}/static/images/default.jpg"
                     alt="通用的占位符缩略图">
            </a>
        </div>
    </div>
    <div class="col-sm-6 col-md-6">
        <div class="jumbotron" href="#">
            <h3>物品申请区</h3>
            <a href="javascript:;$.load.a('${basePath}/list/need.html')" class="thumbnail">
                <img src="${basePath}/static/images/default.jpg"
                     alt="通用的占位符缩略图">
            </a>
        </div>
    </div>
</div>

