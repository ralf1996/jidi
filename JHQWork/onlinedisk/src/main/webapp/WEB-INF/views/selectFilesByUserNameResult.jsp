<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/3
  Time: 14:43
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
            <c:out value="查詢到用戶的信息："/><br>
            <c:out value="用戶ID："/>${allFilesUser.get(0).ID }<br>
            <c:out value="用戶名："/>${allFilesUser.get(0).name }<br>
            <c:out value="用戶密碼："/>${allFilesUser.get(0).password }<br>
            <c:out value="用戶的電子郵箱："/>${allFilesUser.get(0).email }<br>
            <c:out value="用戶是否管理員："/>${allFilesUser.get(0).getisAdministrator() }<br><br><br>

            <c:out value="該用戶上傳的全部文件："/><br><br><br>
            <c:forEach items="${allFilesUser }" var="userVO" >
                <c:out value="文件名："/>${userVO.userFile.name }<br>
                <c:out value="文件類型："/>${userVO.userFile.type }<br>
                <c:out value="文件大小(字節)："/>${userVO.userFile.size }<br><br><br>

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

