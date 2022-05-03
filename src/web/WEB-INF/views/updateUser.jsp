<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>

<%
    User u=(User) session.getAttribute("user");
%>
<form action="updateUser" align="center" name="form1" method="post" onsubmit="return on_submit()">
    <h2>User Update</h2><br>
    <input type="hidden" name="id" value="<%=u.getId()%>">
    <input type="text"  name="Username"id="1" placeholder="Username" value="<%=u.getUsername()%>"><br>
    <input type="password" name="password" id="2" placeholder="password" value="<%=u.getPassword()%>" ><br>
    <input type="text" name="Email" id="3" placeholder=Email value="<%=u.getEmail()%>"><br>
    <strong>Gender</strong> <input type="radio" name="gender" value="male" <%="male".equals(u.getGender())?"checked":""%>>Male
    <input type="radio" name="gender" value="female"<%="fe male".equals(u.getGender())?"checked":""%>>Female<br>
    <input type="date" name="Birthdate" placeholder="Date of birth(yyyy-mm-dd)"  value="<%=u.getBrithdate()%>"><br>
    <input type="submit" value="Save Changes">
</form>
<%@include file="footer.jsp"%>