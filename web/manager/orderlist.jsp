<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
    <h2>未发货订单</h2>
    <table align="center" width="50%" border="1">
        <tr>
            <th>用户</th>
            <th>订单号</th>
            <th>金额</th>
            <th>订单状态</th>
            <th>订单详情</th>
        </tr>
        <c:forEach items="${os}" var="o">
            <tr align="center">
                <td>${o.user.userName}</td>
                <td>${o.ordernum}</td>
                <td>${o.price}</td>
                <td>${o.state==0?'未处理':'已处理'}</td>
                <td><a href="${pageContext.request.contextPath}/ManagerController?operation=showOrdersDetails&ordersId=${o.id}">订单详情</a></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
