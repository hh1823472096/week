<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h2>Welcome,<%-- todo 8 use c:out to print username from parammeter --%>
    <%--<%=request.getParameter("username")%>--%>
    <c:out value="${param.username}"/>
</h2>
</body>
</html>