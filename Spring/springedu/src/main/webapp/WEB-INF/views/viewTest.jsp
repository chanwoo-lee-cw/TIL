<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2><%= request.getAttribute(
		"javax.servlet.forward.request_uri") %> Request successful!!!</h2>
		<!-- 클라이언트가 요청할때 사용한 URI리턴한다. -->
		
<h2><%= request.getRequestURI() %> Request successful!!!</h2>
<!-- forward된 URI에 대한 정보를 요청한다. 클라이언트가 요청한건 contoler지만 컨트롤러에서 넘어간 자원의 URI를 알려준다. -->
<!-- /springedu/select
	/springedu/WEB-INF/views/viewTest.jsp 
	위에가 클라이언트가 요청한 URI이다.
	-->
<hr>
<h2>Request Method : ${ pageContext.request.method }</h2>
<hr>
<h2>Query Value : ${ param.pageno }</h2>
<hr>
<a href='${ header.referer }'>To Form Page....</a>
</body>
</html>