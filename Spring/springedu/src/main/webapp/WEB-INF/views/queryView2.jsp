<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="vo.QueryVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답용JSP</title>
</head>
<body>
<h2>Forward from QueryStringController2 to queryView2.jsp</h2>
<hr>
<%
	QueryVO vo = (QueryVO) request.getAttribute("queryVO");
    if(vo != null) {
%>
		<ul>
			<li>${ queryVO.testName }</li>
			<li>${ queryVO.testAge }</li>
			<li>${ queryVO.testAddr }</li>
		</ul>
<%
    }
%>
<h3>Total Query String : ${ spring }</h3>
</body>
</html>



