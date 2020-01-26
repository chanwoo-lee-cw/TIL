<%@ page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%
	String path = "C:/uploadtest/";
	File isDir = new File(path);
	if (!isDir.isDirectory()) {
		isDir.mkdirs();
	}
    FileReader reader = new FileReader(path+"test.txt");
    char[] buffer = new char[(int)(new File(path+"test.txt").length())];
    reader.read(buffer);
    out.println(buffer);
	reader.close();
%>