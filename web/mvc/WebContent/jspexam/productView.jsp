<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.vo.ProductVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
a {
	text-decoration: none;
	background-image: linear-gradient(to right, #ff99cc, #ffffcc ,#0066ff,#9966ff);
}
</style>
</head>
<body>
	<%
		ProductVO vo = (ProductVO) session.getAttribute("prodList");
	%>
	<h1>선택된 상품 정보는 다음과 같습니다.</h1>
	<hr>
	선택된 사과의 갯수 :
	<%=vo.getApplenum()%>
	<br> 선택된 바나나의 갯수 :
	<%=vo.getBananannum()%>
	<br> 선택된 한라봉의 갯수 :
	<%=vo.gethallabongnum()%>
	<br>
	<hr>
	<a href="<%=request.getHeader("referer")%>">상품 선택 화면</a>
</body>
</html>