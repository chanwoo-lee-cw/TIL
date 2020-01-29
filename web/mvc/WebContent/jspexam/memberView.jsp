<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.vo.MemberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 정보</h1>
<hr>
<ul>
<% MemberVO vo = (MemberVO)request.getAttribute("member"); %>
	<li>회원 이름 : <%=vo.getMname() %></li>
	<li>회원 이름 : <%=vo.getMaccount() %></li>
	<li>회원 전화번호 : <%=vo.getMphone() %></li>
	<li>회원 패스워드 : <%=vo.getMpassword() %></li>
</ul>
</body>
</html>