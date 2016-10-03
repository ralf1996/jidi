<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/3
  Time: 1:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body bgcolor="#CED3FF">

<hr>

<center>

    <h1>${name }的文件</h1>

    <c:choose>

        <c:when test="${fileCount>0 }">

            <c:forEach items="${allFilesName }" var="fileName" >
                ${fileName }

                <br>
            </c:forEach>

        </c:when>

        <c:otherwise>
            <h1>他很懶，什麼也沒上傳!</h1>
        </c:otherwise>

    </c:choose>

</center>

<hr>

</body>
</html>
