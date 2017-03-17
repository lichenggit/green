<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<div class="panel panel-info">
    <div class="panel-heading">
        <label  class="control-label">我的角色是:</label>
        <c:forEach items="${roles}" var="role" varStatus="index">
            <span class="label label-danger">${role.roleRemark}</span>
            <c:if test="${index.last==false}">
                |
            </c:if>
        </c:forEach>
    </div>
    <div class="panel-body">
        <form:form class="form" id="userForm" role="form" method="post" action="" commandName="user">
            <div class="form-group col-sm-4">
                <label for="username" class="control-label">用户名</label>
                <form:input type="text" readonly="true" disabled="true" class="form-control" id="username" path="username"/>
            </div>
            <div class="form-group col-sm-4">
                <label for="categoryName" class="control-label">类别</label>
                <form:input type="text" readonly="true" disabled="true" class="form-control" id="categoryName" path="userCategory.categoryName" />
            </div>
            <div class="form-group col-sm-4">
                <label for="chineseName" class="control-label">姓名</label>
                <form:input type="text" readonly="true" class="form-control" id="chineseName" path="chineseName"/>
            </div>
            <div class="form-group col-sm-4">
                <label for="phoneNumber" class="control-label">手机</label>
                <form:input type="text" readonly="true" class="form-control" id="phoneNumber" path="phoneNumber"/>
            </div>
            <div class="form-group col-sm-4">
                <label for="email" class="control-label">邮箱</label>
                <form:input type="text" readonly="true" class="form-control" id="email" path="email"/>
            </div>
            <div class="form-group col-sm-4">
                <label for="address" class="control-label">地址</label>
                <form:input type="text" readonly="true" class="form-control" id="address" path="address"/>
            </div>
            <div class="form-group  col-sm-12">
                <button type="button" class="btn btn-default" onclick="$.load.a('${basePath}/user/listAllUser.html')">返回</button>
                <%--<button type="button" class="btn btn-success">修改</button>--%>
            </div>
        </form:form>
    </div>
</div>


