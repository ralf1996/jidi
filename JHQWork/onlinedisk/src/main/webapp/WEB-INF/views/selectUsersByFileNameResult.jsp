<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/3
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="#CED3FF">

<center>

    <h1>管理員查詢結果顯示界面</h1><br>
    <hr>

    <c:choose>

        <c:when test="${resultCount>0 }">
            <c:out value="查詢到文件的信息："/><br>
            <c:out value="文件名："/>${fileAndUserInfo.get(0).name }<br>
            <c:out value="文件類型："/>${fileAndUserInfo.get(0).type }<br>
            <c:out value="文件大小(字節)："/>${fileAndUserInfo.get(0).size }<br><br><br>

            <c:out value="上傳同名文件的全部用戶："/><br><br><br>
            <c:forEach items="${fileAndUserInfo }" var="userFileVO" >
                <c:out value="用戶ID："/>${userFileVO.user.ID}<br>
                <c:out value="用戶名："/>${userFileVO.user.name}<br>
                <c:out value="用戶密碼："/>${userFileVO.user.password}<br>
                <c:out value="用戶電子郵箱："/>${userFileVO.user.email}<br>
                <c:out value="用戶是否管理員："/>${userFileVO.user.getisAdministrator()}<br><br><br>

            </c:forEach>

        </c:when>

        <c:otherwise>
            <h1>沒有查詢到相關結果</h1>
        </c:otherwise>

    </c:choose>

    <hr>
</center>

</body>
</html>
