<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>通配符测试主页面</title>

  </head>
  
  <body>
  
	<a href="wildcard/Teacher_add">Teacher_add</a><br />
	<a href="wildcard/Teacher_del">Teacher_del</a><br />
	<a href="wildcard/Student_add">Student_add</a><br />
	<a href="wildcard/Student_del">Student_del</a><br />

  </body>
</html>
