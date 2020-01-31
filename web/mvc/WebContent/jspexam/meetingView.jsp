<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, java.util.ArrayList, model.vo.MeetingVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 100%;
	border-top: 5px dotted #99ccff;
	border-collapse: collapse;
}

tr {
	border-bottom: 5px dotted #99ccff;
	padding: 5px;
}

tr:hover {
	background-color: #ffe6ff;
}
</style>
</head>
<body>
	<h1>미팅 스케쥴</h1>
	<hr>
	<table>

		<%
			List<MeetingVO> list = (List<MeetingVO>) request.getAttribute("list");
		%>

		<%
			for (MeetingVO vo : list) {
		%>
		<tr>
			<td><%=vo.getName()%></td>
			<td><%=vo.getTitle()%></td>
			<td><%=vo.getMeetingDate()%></td>
			<td><img src="/mvc/images/banana.jpg" style="width: 50px"
				onclick="location.href='/mvc/meeting?delid=<%=vo.getId()%>'"></td>
		</tr>
		<%
			}
		%>

	</table>
	<a href="/mvc/htmlexam/meetingForm.html"> 홈으로</a>
</body>
</html>