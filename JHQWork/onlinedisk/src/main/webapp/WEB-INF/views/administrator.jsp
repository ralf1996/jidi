<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/2
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>

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

<center>

    <h1>用戶管理界面</h1><br>
    <hr>

        <form action="${pageContext.request.contextPath }/files/selectFilesByUserName">
            根據用戶名查找該用戶上傳的文件:<input type="text" name="userName">
            <input type="submit" value="查找" onclick="return checkInput();"><br>
        </form>

    <form action="${pageContext.request.contextPath }/users/selectUsersByFileName">
            根據文件名查找上傳該文件的用戶:<input type="text" name="fileName">
        <input type="submit" value="查找" onclick="return checkInput();"><br>

    </form>
    <hr>
<h1>所有已註冊的用戶</h1>
    <c:choose>

        <c:when test="${directoryCount>0 }">

            <c:forEach items="${allUserDirectory }" var="userName" >
                &nbsp;&nbsp;<a href="${pageContext.request.contextPath }/files/fileNamePrepare?name=${userName }&command=administrator">${userName}</a>
                <br>
            </c:forEach>

        </c:when>

        <c:otherwise>
            <h1>空空如也,還沒有用戶註冊呢!</h1>
        </c:otherwise>

    </c:choose>

    <hr>
</center>

</body>
</html>
