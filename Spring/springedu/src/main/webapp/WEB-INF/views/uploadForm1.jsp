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
    	<input type="file" name="uploadFile"  />
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


