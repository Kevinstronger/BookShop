<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp"%>

    <h2 align="center">图书列表</h2>
    <table border="1" align="center" width="50%">
        <tr>
            <th>书名</th>
            <th>作者</th>
            <th>描述</th>
            <th>所属分类</th>
            <th>图片</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${page.records}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.description}</td>
                <td>${cid:getCategoryId(book.category_id)}</td>
                <td><a href="${pageContext.request.contextPath}/files/${book.image}">查看图片</a></td>
                <td>
                    <a href="${pageContext.request.contextPath}/ManagerController?operation=editUI&bookId=${book.id}">编辑</a>
                    <a href="${pageContext.request.contextPath}/ManagerController?operation=deleteBook&bookId=${book.id}">删除</a>
                </td>

            </tr>
        </c:forEach>


    </table>
    <%@include file="/commons/page.jsp"%>
    ${message}
</body>
</html>
