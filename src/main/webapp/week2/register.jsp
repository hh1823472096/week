<%--
  Created by IntelliJ IDEA.
  User: 自渡
  Date: 2022/3/8
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
<span style="background: antiquewhite">
<center>
<h1>New Registration</h1>
<form action="${pageContext.request.contextPath}/week" method="post" onsubmit="return register()">
    <input type="text" name="username" id="username" placeholder="username"><br>
    <input type="password" name="password" id="password" placeholder="password"><br>
    <input type="email" name="name" id="name" placeholder="email"><br>
    <input type="date" name="date" id="date"><br>
     Gender<input type="radio" name="sex" >Male  <input type="radio" name="sex">Female<br>
    <input type="submit" name="submit" id="submit" value="register"><br>
</form>
<script>
    function register(){
        let username =document.getElementById("username");
        if(username=null){
            alert("username can not be null");
            return false;
        }
        let password =document.getElementById("password");
        if(password=null){
            alert("password can not be null");
            return false;
        }
        if(password.length<8){
            alert("password at lest 8");
            return false;
        }
    }

</script>
</center>
</span>
</body>
</html>
