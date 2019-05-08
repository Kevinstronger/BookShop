<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
    <h1 align="center">用户注册</h1>
    <form action="${pageContext.request.contextPath}/ClientController?operation=regist" method="post">
        <table border="0" align="center">
            <tr>
                <td>姓名: </td>
                <td><input type="text" name="userName"></td>
            </tr>
            <tr>
                <td>密码: </td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>电话: </td>
                <td><input type="text" name="tellPhone"></td>
            </tr>
            <tr>
                <td>地址: </td>
                <td><input type="text" name="address"></td>
            </tr>
            <tr>
                <td>邮箱: </td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="注册"></td>
            </tr>
        </table>
    </form>
    ${message}
</body>
</html>
