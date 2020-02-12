<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Test Example</title>
</head>
<body>
<h2>SpringMVC FileUpload</h2>
<hr>
<%
	if( request.getAttribute("msg") == null ) {
%>
	<form action="/springedu/upload" 
	               enctype="multipart/form-data" method="post">
	               <!-- enctype="multipart/form-data"을 지정하고, 파일 업로드는 무조건 post방식 -->
    	<input type="file" name="uploadFile"  />
    	<!-- 인푹 타입을 file 네임이 uploadFile이건 FileVO에도 선언되 있음. 객체를 맞추기 위해서.  -->
    	<input type="submit" value="업로드"/>
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


