<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.MemberVO"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 정보</h1>
<hr>
<h1>스크립트 태그</h1>
<ul>
<% MemberVO vo = (MemberVO)request.getAttribute("member"); %>
	<li>회원 이름 : <%=vo.getMname() %></li>
	<li>회원 이름 : <%=vo.getMaccount() %></li>
	<li>회원 전화번호 : <%=vo.getMphone() %></li>
	<li>회원 패스워드 : <%=vo.getMpassword() %></li>
</ul>

<h1>액션태크</h1>

<ul>
<jsp:useBean id="member" class="vo.MemberVO" scope="request"/>
<!-- 꺼냈는데 객체가 없으면 새로 만든다. id객체의 용도 2가지 객체의 이름, 인스턴스의 이름 이 2가지 용도로 쓰인다. -->
	<li>회원 이름 : <jsp:getProperty name="member" property="mname"/></li>
	<li>회원 이름 :  <jsp:getProperty name="member" property="maccount"/></li>
	<li>회원 전화번호 :  <jsp:getProperty name="member" property="mphone"/></li>
	<li>회원 패스워드 :  <jsp:getProperty name="member" property="mpassword"/></li>
</ul>
<h1>EL</h1>
<ul>

	<li>회원 이름 : ${ requestScope.member.mname }</li>
	<!-- get을 빼고 첫 글자를 소문자로 하면 된다 -->
	<li>회원 이름 : ${ requestScope.member.maccount }</li>
	<li>회원 전화번호 : ${ requestScope.member.mphone }</li>
	<li>회원 패스워드 : ${ requestScope.member.mpassword }</li>
</ul>
</body>
</html>