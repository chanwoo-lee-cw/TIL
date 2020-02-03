<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, java.util.ArrayList, model.vo.NewsVO"%>
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
	text-align: center;
}

table #title {
	text-align: left;
}

tr {
	border-bottom: 5px dotted #99ccff;
	padding: 5px;
}

tr:hover {
	background-color: #ffe6ff;
}

form {
	text-align: center;
	margin : 10px;
}

form #columntitle, #columnwriter, #columncontents {
	width: 368px;
	margin: 3px;
}
</style>
</head>
<body>
	<h1>뉴스 게시판</h1>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="item" items="${requestScope.list }">
			<tr onclick="location.href='/mvc/news?action=read&newsid=${item.id}'">
				<td>${item.id}</td>
				<td id="title">${item.title}</td>
				<td>${item.writer}</td>
				<td>${item.writedate}</td>
				<td>${item.cnt}</td>
			</tr>
		</c:forEach>
	</table>
	
	<button onclick="displayDiv(1);">뉴스 작성</button>
	
	<form method = "post" action = "/mvc/news" id="colwrite" style="display: none">
		<input id="columntitle" name="title" placeholder="제목을 입력해주세요"><br> 
		<input id="columnwriter" name="writer" placeholder="작성자명을 입력해주세요"><br>
		<textarea id="columncontents" name="contents" rows="5" cols="50" placeholder="내용을 입력해주세요"></textarea>
		<br> 
		<input type="hidden" name="format" value="insert">
		<input type="submit" value="저장" > 
		<input type="reset" value="재작성"> 
		<input type="button" value="취소" onclick="displayDiv(0)">
	</form>

	<form method = "post" action = "/mvc/news?newsid=${column.id}" id="columnview" style="display: none">
		<input id="columntitle" name="title" placeholder="제목을 입력해주세요" value="${column.title }"><br> 
		<input id="columnwriter" name="writer" placeholder="작성자명을 입력해주세요" value="${column.writer }"><br>
		<textarea id="columncontents" name="contents" rows="5" cols="50" placeholder="내용을 입력해주세요">${column.content }</textarea>
		<br> 
		<input type="hidden" name="format" value="update">
		<input type="button" value="확인" onclick="displayDiv(0)"> 
		<input type="submit" value="수정"> 
		<input type="button" value="삭제" onclick="location.href='/mvc/news?action=delete&newsid=${column.id}'">
	</form>
	
	<script>
	function displayDiv(type) {
		if(type == 1){
			if(document.getElementById("columnview").style.display = 'block') {
				document.getElementById("columnview").style.display = 'none';
				document.getElementById("colwrite").style.display = 'block';
			}
			else {
				document.getElementById("colwrite").style.display = 'block';
			}
		}
		else if (type == 0) {
			document.getElementById("colwrite").style.display = 'none';
			document.getElementById("columnview").style.display = 'none';
		}
		
	}
	
	window.onload = function () {
		var id = <%=request.getParameter("newsid")%>;
		var action = "<%=request.getParameter("action")%>";
		if ( id != null && action== "read") {
			document.getElementById("columnview").style.display = 'block';
		}
	}
	</script>

</body>
</html>