<%--
  Created by IntelliJ IDEA.
  User: 自渡
  Date: 2022/3/8
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../header.jsp"%>
<html>
<head>
    <title> new register</title>
</head>
<body>
<span style="background: antiquewhite">
<h1>New Registration</h1>
<form action="${pageContext.request.contextPath}/register" method="post" onsubmit="return register()">
     <input type="text" name="username" id="username" placeholder="username"><span id="span1"></span><br>
     <input type="password" id="password" name="password" placeholder="password"><br>
     <input type="text" id="email" name="email" placeholder="email"> <br>
     <strong>Gender</strong> <input type="radio" value="gender" name="sex">Male
            <input type="radio" value="male" name="sex">Female <br>
     <input type="date" id="time" name="date"> <br>
     <input type="submit" value="Register">
</form>
<script>
    function register(){
        let username = document.getElementById("username").value;
        if(username == null || username === ''){
            alert("username be not null!")
            //document.getElementById("span1").innerHTML="不能为空"
            return false;
        }
        let password = document.getElementById("password").value;
        if(password == null || password ===''){
            alert("password be not null!")
            return false;
        }
        let email = document.getElementById("email").value;
        const reg = /^([a-zA-Z0-9])+(([a-zA-Z0-9])|([._-][a-zA-Z0-9])*)+@([a-zA-Z0-9-])+((\.[a-zA-Z0-9-]{2,3}){1,2})$/;
        if(!reg.test(email)){
            alert("invalid email! ")
            return false;
        }
    }
</script>
</span>
</body>
</html>
<%@include file="../footer.jsp"%>
