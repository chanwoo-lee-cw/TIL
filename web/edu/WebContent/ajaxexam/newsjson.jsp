<%@ page language="java" contentType="text/plain; charset=UTF-8"%>
<%
response.setHeader("Access-Control-Allow-Origin", "*");
%> 
<% String news[] = { "CROSS DOMAIN 을 테스트합니다. ㅋ",
							   "CROSS DOMAIN 을 테스트합니다. ㅋㅋ",
							   "CROSS DOMAIN 을 테스트합니다. ㅋㅋㅋ",
							   "CROSS DOMAIN 을 테스트합니다. ㅋㅋㅋㅋ",
							   "CROSS DOMAIN 을 테스트합니다. ㅋㅋㅋㅋㅋ",
					         }; %>
<% response.setHeader("Cache-Control", "no-cache"); %>
<% int num = (int)(Math.random() * 5); %>
{ 
	"news" : "<%= news[num] %>"
}