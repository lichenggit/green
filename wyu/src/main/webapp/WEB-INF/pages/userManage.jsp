<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<div class="panel panel-info">
    <div class="panel-heading">
        <button type="button" class="btn btn-info" onclick="$.load.a('${basePath}/user/userDetails.html');">我的信息</button>
        <button type="button" class="btn btn-primary" onclick="$.load.a('${basePath}/user/addUser.html');">添加用户</button>
    </div>
    <div class="panel-body">
        <div class="table-responsive">
            <table class="table table-hover table-striped" id="attrs-table">
                <thead>
                <tr>
                    <th>角色</th>
                    <th>类别</th>
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>手机号码</th>
                    <th>邮箱</th>
                    <th>地址</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="item">
                    <tr>
                        <td>
                            <c:forEach items="${item.roles}" var="role" varStatus="index">
                                <span class="label label-info">${role.roleRemark}</span>
                                <c:if test="${index.last==false}">
                                    |
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>${item.userCategory.categoryName}</td>
                        <td>${item.username}</td>
                        <td>${item.chineseName}</td>
                        <td>${item.phoneNumber}</td>
                        <td>${item.email}</td>
                        <td>${item.address}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
