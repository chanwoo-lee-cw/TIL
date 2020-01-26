<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Query 문자열 추출(GET&amp;POST)</h1>
	<hr>
	<%-- 수행문으로 하면 error 왜나하면 수행문으로 하면 매서드 안에 매서드 들어가는 형태가 되기 때문이다. 그러니 선언문으로 해야 한다. --%>
	<%!int result = 0;

	int multiply(int n1, int n2) {
		return n1 * n2;
	}%>
	<%
		if (request.getMethod().equals("GET")) {
	%>
	<h3>숫자 2개를 입력하세요.</h3>
	<form method="post" action="/sedu/jspexam/exam5.jsp">
		숫자 1<input type="number" name="su1"><br> 숫자 2<input
			type="number" name="su2"><br> <input type="submit">
	</form>
	<%
		} else {
			int su1 = Integer.parseInt(request.getParameter("su1"));
			int su2 = Integer.parseInt(request.getParameter("su2"));
			result = multiply(su1, su2);
	%>
	<h2>
		수행 결과 :
		<%=result%></h2>
	<a href=<%=request.getHeader("referer")%>>입력 화면으로</a>
	<%
		}
	%>
</body>
</html>