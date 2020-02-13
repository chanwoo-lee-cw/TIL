<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <!-- %@ 태그는 어디다 작성하던 똑같다. 페이지 로딩하기 전에 쭉 읽어오는 것이기 때문이다 include도 마찬가지 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 학습</title>
</head>
<body>
<h2>include 지시자 태그 테스트</h2>
<hr>
<%@  include  file="part1.jspf"  %>
<hr>
<%@  include  file="part2.jspf"  %>
<hr>
<h3>이 페이지에서 직접 출력하는 내용입니다.</h3>
</body>
</html>

