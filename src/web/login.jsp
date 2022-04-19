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
<%
    Cookie[] allCookies=request.getCookies();
    String username = null,password = null,rememberMeVal = null;
    if(allCookies!=null){
        for (Cookie c:allCookies){
            if (c.getName().equals("cUsername")){
                username=c.getValue();
            }
            if (c.getName().equals("cPassword")){
                password=c.getValue();
            }
            if (c.getName().equals("rememberMeVal")){
                rememberMeVal=c.getValue();
            }
        }
    }
%>
<form action="<%=request.getContextPath()%>/login" method="post">
    UserName:<input type="text" name="username" value="<%=username%>"> <br/>
    Password:<input type="password" name="password" value="<%=password%>"> <br/>
    <input type="checkbox" name="remember" value="1" <%= rememberMeVal.equals("1")?"checked":""%>/>Remember me<br/>
    <input type="submit" value="Login">
</form>
</body>
</html>

<%@include file="footer.jsp"%>
