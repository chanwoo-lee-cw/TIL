<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로또 결과</h1>
<hr>
<h2>${ lottoVO.result }</h2>
<%
	vo.LottoVO vo = (vo.LottoVO)request.getAttribute("lottoVO");
%>
<h2><%= vo.getResult() %></h2>
<hr>
<a href="/springedu/resources/lottoForm1.html">재시도.....</a>
</body>
</html>












