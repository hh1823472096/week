<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
This is my JSP page<br>
<h2>The "if" Tag</h2>
<ul>
    <c:forEach var="i" begin="1" end="10">
        <li>${i}</li>
        <c:if test="${i>7}">
            (greater than 7)
        </c:if>
    </c:forEach>
</ul>
<hr/>
<h2>The "choose" Tag</h2>
<ul>
    <c:forEach var="i" begin="1" end="10">
        <li>${i}
            <c:choose>
                <c:when test="${i<4}">
                    (small)
                </c:when>
                <c:when test="${i<0}">
                    (medium)
                </c:when>
                <c:otherwise>
                    (large)
                </c:otherwise>
            </c:choose>
        </li>
    </c:forEach>
</ul>
<hr/>
Risky Code
<c:catch var="e">
    <%int c=100/0;%>
</c:catch>
<c:out value="${e}"/>
<h2>Use c:import</h2>
<%--<c:import url = "https://www.baidu.com"/>--%>
<h2>Use c:url and c:param</h2>
<a href="<c:url value="/index.jsp"/> ">Home</a>
<!--add context path encode charecters and session id when cookies is blocked-->
<p>user c:param with c:url</p>
<c:url value="/index.jsp" var="url">
    <c:param name="productId" value="&1"/>
</c:url>
<a href="<c:out value="${url}"/>">Home</a>
<h2>c:redirect</h2>
<c:redirect url="http://sourabhdixit:8080/MyApp/"/>
</body>
</html>