<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
This is loop JSP page <hr>
<c:set var="user" value="admin"/>
<h2>Welcome,<c:out value="${user}" default="<font color=red>Guest</font>" escapeXml="false"/> </h2>
<h2> Looping with Simple Numeric Values</h2>
<ul>
    <c:forEach var="i" begin="1" end="10">
        <li>${i}</li>
    </c:forEach>
</ul>
<hr />
<h2>Looping with a Designated Step Size</h2>

<ul>
    <c:forEach var="i" begin="1" end="10" step="2">
        <li>${i}</li>
    </c:forEach>
</ul>
<hr />
<h2>The "choose" Tag</h2>
<ul>
    <c:forEach var="i" begin="1" end="10">
        <li>${i}
            <c:choose>
                <c:when test="${i<4}">
                    {small}
                </c:when>
                <c:when test="${i<8}">
                    {mesium}
                </c:when>
                <c:otherwise>
                    {large}
                </c:otherwise>
            </c:choose>
        </li>
    </c:forEach>
</ul>
</body>
</html>