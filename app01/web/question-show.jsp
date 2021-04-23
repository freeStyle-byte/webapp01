<%@ page import="java.util.List" %>
<%@ page import="com.bjpowernode.entity.Question" %>
<%--
  Created by IntelliJ IDEA.
  User: 牛青松
  Date: 2021/4/23
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <table border="2px" style="font-size: 13px">
        <tr>
            <td>编号</td>
            <td>题目</td>
            <td>选项A</td>
            <td>选项B</td>
            <td>选项C</td>
            <td>选项D</td>
            <td>正确答案</td>
            <td>操作</td>
        </tr>

        <%
            int count = 0;
            List<Question> questions = (List)request.getAttribute("qList");
            for (Question q : questions) {
                count += 1;
         %>
        <%
                if(count % 2 != 0) {
        %>
        <tr style="background-color: aquamarine">
            <td><%=q.getId()%></td>
            <td><%=q.getTitle()%></td>
            <td><%=q.getOptionA()%></td>
            <td><%=q.getOptionB()%></td>
            <td><%=q.getOptionC()%></td>
            <td><%=q.getOptionD()%></td>
            <td><%=q.getAnswer()%></td>
            <td><a href="http://localhost:8080/app01/question/delete?Id=<%=q.getId()%>">删除</a></td>
        </tr>
        <%
                }else {
        %>
        <tr style="background-color: hotpink">
            <td><%=q.getId()%></td>
            <td><%=q.getTitle()%></td>
            <td><%=q.getOptionA()%></td>
            <td><%=q.getOptionB()%></td>
            <td><%=q.getOptionC()%></td>
            <td><%=q.getOptionD()%></td>
            <td><%=q.getAnswer()%></td>
            <td><a href="http://localhost:8080/app01/question/delete?Id=<%=q.getId()%>">删除</a></td>
        </tr>
        <%
                }
        %>
    <%
        }
    %>
    </table>
</center>
</body>
</html>
