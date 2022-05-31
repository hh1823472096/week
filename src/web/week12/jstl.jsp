<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
This is my JSP page
<!--in page-->
<c:set var="foo" value="in page" scope="page"/>
<!--in request-->
<c:set var="foo" value="in request" scope="request" />

<!--in session-->
<c:set var="foo" value="in session" scope="session" />
<!--in application-->
<c:set var="foo" value="in application" scope="application" />

<br>
Get(use jstl) :<c:out value="${foo}"/>
<br>
Get(use El):${foo}
<br>
Get(java code):<%=pageContext.findAttribute("foo")%>
<h2>use c:set to set bean property</h2>
<%@ page import="com.luohongyun.week11.*" %>
<%
    Person person = new Person();
    Dog dog = new Dog();
    request.setAttribute("persion",person);
    request.setAttribute("dog",dog);
%>
<c:set target="${persion}" property="name" value="persion1" />
<c:set target="${dog}" property="name" value="dog1" />
<c:set target="${persion}" property="dog" value="${dog}" />


Dog Name :<c:out value="${persion.dog.name}" />

<h2> Use C:remove </h2>
<c:out value="${pageScope.foo}" /><br>
<c:out value="${requestScope.foo}" /><br>
<c:out value="${sessionScope.foo}" /><br>
<c:out value="${applicationScope.foo}" /><br>

<c:remove var="foo" scope="session" />
<p>After session removed</p>
<c:out value="${pageScope.foo}" /><br>
<c:out value="${requestScope.foo}" /><br>
<c:out value="${sessionScope.foo}" /><br>
<c:out value="${applicationScope.foo}" /><br>

<h3>C:catch</h3>
<c:catch var="myException">
    <%=10/0%>
</c:catch>
Message :<c:out value="${myException}" />
</body>
</html>