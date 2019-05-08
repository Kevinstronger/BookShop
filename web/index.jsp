<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <jsp:forward page="/ClientController">
      <jsp:param name="operation" value="showIndexCategory"></jsp:param>
    </jsp:forward>
  </body>
</html>
