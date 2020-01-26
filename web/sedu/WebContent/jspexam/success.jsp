<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ 
	page import="java.time.LocalDateTime"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<%
	LocalDateTime now = LocalDateTime.now();
%>
<h2><%=now.getHour()+"시"+now.getMinute()+"분"%>에 당첨!! 추카추카</h2>
<img src=http://localhost:8000/sedu/images/happyOnion.gif" style="width : 80px"><br>


</body>
</html>