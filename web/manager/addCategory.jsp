<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%@ include file="header.jsp"%>


    <h4 align="center">添加新分类</h4>
    <form action="${pageContext.request.contextPath}/ManagerController?operation=addCategory" method="post">
        <table border="0" width="40%" align="center">
            <tr>
                <td>*分类名称</td>
                <td>
                    <input name="name" type="text"/>
                </td>
            </tr>
            <tr>
                <td>描述</td>
                <td>
                    <textarea rows="3" cols="30" name="description"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="保存"/>
                </td>
            </tr>
        </table>


    </form>
    ${message}

</body>
</html>
