<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>java code</h1>
<table>
    <tr><td>name:<%=request.getParameter("name")%></td></tr>
    <tr><td>class:<%=request.getParameter("Class")%></td></tr>
    <tr><td>id:<%=request.getParameter("myId")%>></td></tr>
</table>
<%--    EL--%>
<h1>EL</h1>
<table>
    <tr><td>name:${param.name}</td></tr>
    <tr><td>class:${param.Class}</td></tr>
    <tr><td>id:${param.myId}</td></tr>
</table>
</body>
</html>