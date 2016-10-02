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
        if(userInfoRenew.uname.value==""||userInfoRenew.passwd.value==""||userInfoRenew.email.value=="")
        {
            window.alert("輸入不能為空!");
        
            return false;
        }
    }

</script>

</head>
<body  bgcolor="#CED3FF">

<center>

<h1>用戶信息修改界面</h1><br>
<hr>

<form action="${pageContext.request.contextPath }/users/userUpDataController" name="userInfoRenew" method="post">
<input type="hidden" name="command" value="update">
<input type="hidden" name="id" value="${userbean.ID }">
用戶名:<input type="text" name="uname" value="${userbean.name }" readonly><br>
密&nbsp;&nbsp;&nbsp;碼:<input type="text" name="passwd" value="${userbean.password }"><br>
郵&nbsp;&nbsp;&nbsp;箱:<input type="text" name="email" value="${userbean.email }"><br>
<input type="submit" value="確認修改" onclick="return checkInput();"><br>
<hr>
</form>

</center>

</body>
</html>