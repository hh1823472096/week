<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<%--用户信息编辑页面--%>
<form action="<c:url value="/admin/updateUser"/>" method="post">
    <input type="hidden" name="id" value="${userbyId.id}">
    <table>
        <tr>
            <td>Username</td>
            <td><input type="text" name="username" value="${userbyId.username}"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="password" value="${userbyId.password}"></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="${userbyId.email}"></td>
        </tr>
        <tr>
            <td>Gender</td>
            <td><input type="text" name="gender" value="${userbyId.gender}"></td>
        </tr>
        <tr>
            <td>Date of Birth</td>
            <td><input type="text" name="birthday" value="${userbyId.birthday}"></td>
        </tr>
        <tr>
            <td>操作</td>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>
</body>
</html>