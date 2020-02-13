<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP의 내장 객체들</title>
</head>
<body>
<h2>JSP의 내장 객체들 점검</h2>
[ request ]<br>
getMethod() : <%=  request.getMethod()  %><br>
getRequestURI() : <%=  request.getRequestURI()  %><br>
getHeader("user-agent") : <%=  request.getHeader("user-agent")  %><br>
<!-- user-agent : 클라이언트가 누구인지 알려주는 객체, 웹인지 폰인지 등등 -->
[ application ]<br> 
getContextPath() : <%=  application.getContextPath()  %><br>
getServerInfo() : <%=  application.getServerInfo()  %><br>
getMajorVersion() : <%=  application.getMajorVersion()  %><br>
<!-- 지원하는 서블릿 버전 -->
[ session ]<br>
getId() : <%=  session.getId()  %><br>
<!-- HTTP session의 아이디를 알려준다. -->
getCreationTime() : <%=  new Date(session.getCreationTime())  %><br>
<!-- 얘가 만들어진 시간 -->
[ response ]<br>
getStatus() : <%=  response.getStatus() %><br>
<!-- 응답 상태 코드 -->
getBufferSize() : <%=  response.getBufferSize() %><br>
getContentType() : <%=  response.getContentType() %>
<H4>Web Application(/sedu) 디렉토리의 파일 리스트 </H4>
<% 
java.util.Set<String> list = application.getResourcePaths("/");
if (list != null) {
   Object obj[] = list.toArray();
   for(int i=0; i < obj.length; i++) {
      out.print(obj[i]+", ");
   }
}
%>
</body>
</html>