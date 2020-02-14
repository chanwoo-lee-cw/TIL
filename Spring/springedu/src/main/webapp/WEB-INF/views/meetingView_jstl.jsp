<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, java.util.ArrayList, vo.MeetingVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<h1>미팅 스케쥴(JSTL)</h1>
	<hr>
	<table>
		<%-- <c:forEach var="item" items="<%=request.getAttribute(\"list\") %>">--%>
		<c:forEach var="item" items="${requestScope.list }">
		<tr>
			<td>${item.name }</td>
			<td>${item.title }</td>
			<td>${item.meetingDate}</td>
			<td><img src="/mvc/images/banana.jpg" style="width: 50px"
				onclick="location.href='/springedu/deleteMeeting?delid=${item.id}'"></td>
		</tr>
		</c:forEach>


		<%-- 
		<tr>
			<td><%=vo.getName()%></td>
			<td><%=vo.getTitle()%></td>
			<td><%=vo.getMeetingDate()%></td>
			<td><img src="/mvc/images/banana.jpg" style="width: 50px"
				onclick="location.href='/mvc/meeting?delid=<%=vo.getId()%>'"></td>
		</tr>
		--%>
	</table>
	<a href="/springedu/resource/meetingForm.html"> 홈으로</a>
</body>
</html>