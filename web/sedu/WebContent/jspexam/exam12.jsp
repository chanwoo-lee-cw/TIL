<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 예제</title>
</head>
<body>
<h2>include 지시자와 액션태그 비교</h2>
 <hr>
 <%--
<%@ include file="date.jsp" %>
<hr>
<%@ include file="date.jsp" %>
<!-- 똑같은 이름의 변수가 2번 나오기 때문이다. -->
--%>
 
<hr>
<jsp:include page="date.jsp" />
<hr>
<jsp:include page="date.jsp" />


<!-- 무조건 JSP주석으로 막아야 된다. 아니면 HTML태그로 막으면 jsp 내용은 실행 되기 때문-->
</body>
</html>






