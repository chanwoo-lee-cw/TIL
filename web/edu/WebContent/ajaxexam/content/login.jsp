<%@ page contentType="application/json; charset=utf-8"%>
<%
   if (request.getParameter("id").equals("ajaxtest") && 
		request.getParameter("passwd").equals("12345")) {
%>
{ 
   "result" : "ok"
}
<%
} else {
%>
{ 
   "result" : "fail"
}
<%	
}
%>
<%--로그인 성공했는지 실패했는지 json 형식으로 리턴한다. --%>