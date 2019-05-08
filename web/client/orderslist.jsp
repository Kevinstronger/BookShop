<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
    <h2 align="center">以下是您近期的订单</h2>
    <table border="1" width="50%" align="center">
        <tr>
            <th>订单号</th>
            <th>金额</th>
            <th>订单状态</th>
            <th>明细</th>
        </tr>
        <c:forEach items="${os}" var="o">
            <tr align="center">
                <td>${o.ordernum}</td>
                <td>${o.price}元</td>
                <td>${o.state==0?'未处理':'已处理'}</td>
                <td><a href="${pageContext.request.contextPath}/ClientController?operation=showOrdersDetails&ordersId=${o.id}">订单详情</a></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
