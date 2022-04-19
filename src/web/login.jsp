<%@ page import="jakarta.servlet.http.Cookie" %><%--
  Created by IntelliJ IDEA.
  User: 自渡
  Date: 2022/3/27
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
nes (22 sloc)  619 Bytes

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="header.jsp"%>
<h1>Login</h1>
<%
    if (request.getAttribute("message")!=null){
        //error
        out.println(request.getAttribute("message"));
    }
%>
<form action="${pageContext.request.contextPath}/Login" method="post">
    username:<input type="text"  name="username" placeholder="username"><br>
    password:<input type="password" name="password" placeholder="password"><br>
    <input type="submit" value="Login">
</form>
<%@include file="footer.jsp"%>
