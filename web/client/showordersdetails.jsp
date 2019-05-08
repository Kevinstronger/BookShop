<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
    <h1>订单详情</h1>
     订单号：${orders.id} 总价： ${orders.price}元 明细如下：<br/>
    <table align="center" border="1" width="50%">
        <tr>
            <th>书名</th>
            <th>作者</th>
            <th>单价</th>
            <th>数量</th>
            <th>小计</th>
        </tr>
        <c:forEach items="${orders.items}" var="o">
            <tr align="center">
                <td>${o.book.name}</td>
                <td>${o.book.author}</td>
                <td>${o.book.price}</td>
                <td>${o.num}</td>
                <td>${o.price}元</td>
            </tr>
        </c:forEach>
    </table>
    收货信息：<br/>
    地址：${orders.user.address}<br/>
    电话：${orders.user.cellPhone}<br/>


</body>
</html>
