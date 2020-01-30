<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.vo.VisitorVO, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	td {
		border-bottom : 1px dotted green;
	}
	tr:hover {
		background-color : pink;
		font-weight : bold;
	}
	td:nth-child(3) {
		width : 400px;
	}
</style>
</head>
<body>
<%
	List<VisitorVO> list = (List<VisitorVO>)request.getAttribute("list");
    if (list != null) {
%>
    	<h2>방명록 글 리스트</h2><hr>
    	<table>    
<%	
    	for(VisitorVO vo : list) { 	   
%>
			<tr>
				<td><%= vo.getName() %></td>
				<td><%= vo.getWriteDate() %></td>
				<td><%= vo.getMemo() %></td>		
			</tr>
<%
    	}
%>
    	</table>
<%
    } else {
%>
		<h2>${msg}</h2>
<%
    }
%>
<hr>
<a href="/mvc/htmlexam/visitorForm.html ">방명록 홈 화면으로 가기</a>

</body>
</html>



