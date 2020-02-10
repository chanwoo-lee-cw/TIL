<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.StepVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table, th, td  {
	border : 1px solid black;
	border-collapse: collapse;
}
td {
	text-align: center;
}
</style>
</head>
<body>
<h2>The registered step information is as follows.</h2>
<table>
<tr><th>Step Name</th><th>Telephone Number</th><th>AGe</th></tr>
<tr>
<td>${ kkk.name }</td>
<td>${ kkk.phoneNumber }</td>
<td>${ kkk.age }</td>
</tr> 
</table> 
<hr>
<a href="/springedu/resources/stepForm.html">Input screen...</a>
</body>
</html>
