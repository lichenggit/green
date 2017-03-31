<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/tag.jsp" %>

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
                <c:forEach items="${list}" var="item" varStatus="index">
                    <tr>
                        <td>${item.categoryName}</td>
                        <td>${item.articleName}</td>
                        <td>${item.description}</td>
                        <td>${item.remark}</td>
                        <td><fmt:formatDate value="${item.createTime}" pattern="yyyy年MM月dd日"/></td>
                        <td><a href="javascript:;haveNeed('${item.needId}','${item.articleName}','${item.userId}');">我有此物</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    function haveNeed(needId,articleName,neederId) {
        $.post("${basePath}/need/addNeedHave.json",{"needId":needId,"needName":articleName,"neederId":neederId},function (data) {
            if(data.code==0){
                swal("我们会将此消息报告需要的人，感谢您的分享！","","success");
            }else {
                swal("操作失败","","error");
            }
        });
    }
</script> 