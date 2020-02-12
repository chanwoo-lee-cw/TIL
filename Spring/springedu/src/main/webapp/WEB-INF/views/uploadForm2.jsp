<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 테스트 예제</title>
</head>
<body>
<h2>SpringMVC FileUpload(Multiple)</h2>
<hr>
<%
	if( request.getAttribute("msg") == null ) {
%>
	<form action="/springedu/upload3" 
	               enctype="multipart/form-data" method="post">
    	<input type="file" name="mfile" multiple/>
    	<!-- 파일의 갯수만큼을 나오고 올릴시 올린 파일들 전부 출력 -->
    	<input type="submit" value="파일올리기"/>
	</form>
<%
	} else {
%>
	 ${ msg }
<%
	}
%>
</body>
</html>