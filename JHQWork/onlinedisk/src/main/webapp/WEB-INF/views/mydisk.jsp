<%@ page language="java"  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script type="text/javascript">
</script>
</head>
<body bgcolor="#CED3FF">


<form action="${pageContext.request.contextPath }/files/upLoad" method="post" enctype="multipart/form-data" accept-charset="utf-8">
    <p>
        <input type="hidden" name="userId" value="${sessionScope.userId}"/>
        上傳文件：<input id="myfile" name="myfile" type="file" onchange="showpreview(this)" />
    </p>

    <p>
        <input type="submit" value="提交" />
    </p>

<%--<div id="previewFile"></div>--%>

</form>

<hr>

<center>

<h1>我的文件</h1>

<c:choose>

<c:when test="${fileCount>0 }">

<c:forEach items="${allFilesName }" var="fileName" >
${fileName }
&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/files/downLoad?filename=${fileName }&name=${sessionScope.name }">下載</a>
&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/files/deleteFile?filename=${fileName }&name=${sessionScope.name }" onclick="return window.confirm('您確定要刪除嗎?刪除后文件不可恢復。')">刪除</a>
<br>
</c:forEach>

</c:when>

<c:otherwise>
<h1>空空如也,上傳點東西吧!</h1>
</c:otherwise>

</c:choose>

</center>
</body>
</html>