<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String send = "";

		String snowman = request.getParameter("study");

		if (snowman == null)
			out.print("<h2>study 라는 이름으로 전달된 쿼리가 존재하지 않습니다.</h2>");
		else {
			switch (request.getParameter("study")) {
			case "javascript":
				send = "js/default.asp";
				break;
			case "dom":
				send = "js/js_htmldom.asp";
				break;
			case "ajax":
				send = "xml/ajax_intro.asp";
				break;
			case "json":
				send = "js/js_json_intro.asp";
				break;
			case "jsp":
	%>
	<jsp:forward page="/first.jsp">
		<jsp:param name="gname" value="unico" />
	</jsp:forward>
	<%
		case "html":
				RequestDispatcher rd = request.getRequestDispatcher("/first.html");
				rd.forward(request, response);
				return;
			}
		response.sendRedirect("http://www.w3schools.com/" + send);
		}
	%>
</body>
</html>