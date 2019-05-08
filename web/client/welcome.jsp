<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
    <h1 align="center">欢迎光临</h1>
    <!--显示分类数据，不分类-->
    <table align="center">
        <tr align="center">
            <c:forEach items="${page.records}" var="book">
                <td>
                    <table align="center">
                        <tr style="alignment: center;">
                            <td><img src="${pageContext.request.contextPath}/files/${book.image}"></td>
                        </tr>
                        <tr>
                            <td>
                                书名： ${book.name}<br/>
                                作者： ${book.author}<br/>
                                售价： ${book.price}
                            </td>
                        </tr>
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/ClientController?operation=buyBook&bookId=${book.id}">购买</a></td>
                        </tr>
                    </table>
                </td>
            </c:forEach>
        </tr>
        <tr><td><%@include file="/commons/page.jsp"%></td></tr>

    </table>

</body>
</html>
