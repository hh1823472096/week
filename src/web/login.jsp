<%--
  Created by IntelliJ IDEA.
  User: 自渡
  Date: 2022/3/27
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
nes (22 sloc)  619 Bytes

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="header.jsp"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<%
    if (request.getAttribute("message")!=null){
        //error
        out.println(request.getAttribute("message"));
    }
%>
<form action="<%=request.getContextPath()%>/login" method="post">
    UserName:<input type="text" name="username"> <br>
    Password:<input type="password" name="password"> <br>
    <input type="submit" value="Login">
</form>
</body>
</html>

<%@include file="footer.jsp"%>
