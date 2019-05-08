<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp"%>
<h2 align="center">编辑图书</h2>
<form action="${pageContext.request.contextPath}/ManagerController?operation=modifyBook&bookId=${book.id}" method="post" enctype="multipart/form-data">
    <table width="50%" align="center" border="0">
        <tr>
            <td><label hidden="hidden">id</label></td>
            <td><input type="hidden" name="id" value="${book.id}"></td>
        </tr>
        <tr>
            <td>书名</td>
            <td><input type="text" name="name" value="${book.name}"/></td>
        </tr>
        <tr>
            <td>作者</td>
            <td><input type="text" name="author" value="${book.author}"/></td>
        </tr>
        <tr>
            <td>售价</td>
            <td><input type="text" name="price" value="${book.price}"/></td>
        </tr>
        <tr>
            <td>图片</td>
            <td><input type="file" name="img" value="${pageContext.request.contextPath}/files/${book.image}"></td>
        </tr>
        <tr>
            <td>描述</td>
            <td>
                <textarea rows="3" cols="30" name="description">${book.description}</textarea>
            </td>
        </tr>
        <tr>
            <td>所属分类</td>
            <td>
                <select name="category_id">
                    <c:forEach items="${cs}" var="c">
                        <option value="${c.id}" <c:if test="${book.category_id == c.id}">selected</c:if>>${c.name}</option>>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="保存"/></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
        </tr>
    </table>
</form>
${message}
</body>
</html>