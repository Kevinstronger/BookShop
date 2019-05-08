<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
    <h1 align="center">用户登录</h1>
    <form action="${pageContext.request.contextPath}/ClientController?operation=login" method="post">
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
                <td colspan="2"><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>

</body>
</html>
