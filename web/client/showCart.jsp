<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1 align="center">已选商品：</h1>
<c:if test="${empty sessionScope.cart.items}">
    您尚未购买任何商品！
</c:if>
<c:if test="${!empty sessionScope.cart.items}">
    <table align="center" width="50%" border="1">
        <tr align="center">
            <th>商品信息</th>
            <th>单价</th>
            <th>数量</th>
            <th>金额</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${sessionScope.cart.items}" var="me">
            <tr align="center">
                <td style="display:flex;justify-content: center; line-height: 80px;">
                    <img src="${pageContext.request.contextPath}/files/${me.value.book.image}" height="80" width="80">&nbsp;&nbsp;
                    ${me.value.book.name}
                </td>
                <td>${me.value.book.price}</td>
                <td>${me.value.quantity}</td>
                <td>${me.value.price}</td>
                <td><a href="#">删除</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="5" align="right">
                总数量： ${sessionScope.cart.amount}&nbsp;&nbsp;
                付款金额：${sessionScope.cart.totalPrice}&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/ClientController?operation=genOrders">生成订单</a>
            </td>
        </tr>

    </table>
</c:if>

</body>
</html>
