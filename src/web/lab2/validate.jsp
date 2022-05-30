
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>validate</title>
</head>
<body>
<%--Todo 1: Use <jsp:useBean> to create a Login bean instance in request scope --%>
<jsp:useBean id="login" scope="request" class="com.huanghong.lab.Login" />
<%--Todo 2: Use <jsp:setProperty> to set  beans' property username and password--%>
<jsp:setProperty name="login" property="username" value='<%=request.getParameter("username")%>'/>
<jsp:setProperty name="login" property="password" value='<%=request.getParameter("password")%>'/>
<%
    //todo 3: use if check username is admin and password is admin
    if(login.getUsername().equals("admin") && login.getPassword().equals("admin")){
        request.getSession().setAttribute("username",login.getUsername());
%>
<%--todo 4: use jsp:forward to welcome.jsp page--%>
<jsp:forward page="welcome.jsp"/>
<%--todo 5: else part{ --%>
<%
}else{
%>

<%
    //  6: print username or password error message
    out.println("<font color='blue'>Username Password Error</font>");
%>
<%--todo 7: use jsp:include login.jsp page --%>
<jsp:include page="login.jsp"/>

<%--todo 8: close else --%>
<%
    }
%>
</body>
</html>