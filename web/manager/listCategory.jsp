<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp"%>


    <h2 align="center">分类列表</h2>
    <c:if test="${empty cs}">
        暂无分类！
    </c:if>
    <c:if test="${!empty cs}">
        <table border="1" align="center" width="50%">
            <tr>
                <th>分类名称</th>
                <th>分类描述</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${cs}" var="c" varStatus="vs">
                <tr bgcolor="${vs.index%2==0?'#7fffd4':'#ffb6c1'}">
                    <td>${c.name}</td>
                    <td>${c.description}</td>
                    <td>
                        <a href="">编辑</a>
                        <a href="">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </c:if>

</body>
</html>
