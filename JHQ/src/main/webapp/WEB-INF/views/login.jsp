<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script type="text/javascript">

    function checkInput()
    {
        if(loginForm.username.value==""||loginForm.passwd.value=="")
        {
            window.alert("輸入不能為空!");
        
            return false;
        }
    }

</script>

</head>
<body bgcolor="#CED3FF">

<c:if test="${requestScope.info != null and requestScope.info !=''}">
    <h1>${requestScope.info }</h1>
</c:if>


<center>

<h1>我的網盤</h1><hr>

<form action="${pageContext.request.contextPath }/users/checkUserController" method="post" name="loginForm">
<input type="hidden" name="command" value="checkuser">
用戶名:<input type="text" name="username"><br>
密&nbsp;&nbsp;&nbsp;碼:<input type="password" name="passwd"><br>

<input type="submit" value="確定" onclick="return checkInput();">

</form>
&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/users/sentRedirectToRegister">註冊新用戶</a>
<hr>




</center>
</body>
</html>