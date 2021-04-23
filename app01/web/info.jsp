<%--
  Created by IntelliJ IDEA.
  User: 牛青松
  Date: 2021/4/22
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <center>
        <%
            String info = (String) request.getAttribute("info");
        %>
        <p style="color: red;font-size: 30px"><%=info%></p>
    </center>
</body>
</html>
