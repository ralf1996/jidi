<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body bgcolor="#CED3FF">

<center>

    <c:if test="${name !=null and name!=''}">
        歡迎：${name }
    </c:if>


<h1>主界面</h1><br>
<hr>
    <c:choose>
        <c:when test="${isAdministrator !=null and isAdministrator=='true'}">
            <a href="${pageContext.request.contextPath }/files/prepareAllUserDirectory?name=${sessionScope.name }">管理用戶</a><br>
            <a href="${pageContext.request.contextPath }/users/prepareDataController?name=${sessionScope.name }">修改個人信息</a><br>
        </c:when>

        <c:otherwise>
            <a href="${pageContext.request.contextPath }/files/fileNamePrepare?command=FileNamePrepare&name=${sessionScope.name }">進入我的網盤</a><br>
            <a href="${pageContext.request.contextPath }/users/prepareDataController?name=${sessionScope.name }">修改個人信息</a><br>
        </c:otherwise>

    </c:choose>


<hr>

</center>

</body>
</html>