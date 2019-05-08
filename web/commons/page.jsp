<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
第${page.pageNum}页/共${page.totalPages}页&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}${page.url}&pageNum=${page.pageNum-1>0?page.pageNum-1:1}">上一页</a>&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}${page.url}&pageNum=${page.pageNum+1>page.totalPages?page.totalPages:page.pageNum+1}">下一页</a>&nbsp;&nbsp;