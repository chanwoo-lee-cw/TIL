<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>요청을 처리하는 동안 에러가 발생했어요</h1>
	<hr>
	<h3>
		에러의 원인 : 
		<span style="color: #ff9966"><%=request.getAttribute("errmsg")%></span><br>
		에러의 원인(EL) : 
		<span style="color: #ff9966">${ requestScope.errmsg }</span>
		
	</h3>
	<a href="<%=request.getHeader("referer")%>">입력화면</a>
</body>
</html>