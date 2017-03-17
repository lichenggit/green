<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

<div class="panel panel-info">
    <div class="panel-heading">
        <button type="button" class="btn btn-success" onclick="$.load.a('${basePath}/user/listAllUser.html');">用户管理
        </button>
    </div>
    <div class="panel-body">
        <div class="table-responsive">
            <table class="table table-hover table-striped" id="attrs-table">
                <thead>
                <tr>
                    <th>角色</th>
                    <th>拥有的权限</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${roles}" var="item">
                    <tr>
                        <td>${item.roleRemark}</td>
                        <td>
                            <c:forEach items="${item.permissions}" var="permission" varStatus="index">
                                <span class="label label-info">${permission.perName}</span>
                                <c:if test="${index.last==false}">
                                    |
                                </c:if>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
