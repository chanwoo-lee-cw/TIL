<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align:center">
<h2>오류 발생했어욤ㅠㅠ</h2>
<h3>빠른시일내에 복구하겠습니다...</h3>
<img src="error.jpg">
<%	
    String msg = "오류 원인 : " + exception;
	// exception이란 내장 객체는 page isErrorPage="true" 가 선언된 페이지에서만 쓸 수 있는 내장객체
	System.out.println("----------------------------------------");
	System.out.println(msg);
	System.out.println("----------------------------------------");	
	exception.printStackTrace();
	// exception.printStackTrace();로 어떤 에러가 났는지 출력한다.
%>
</body>
</html>






