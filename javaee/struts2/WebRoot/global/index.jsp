<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>全局结果集</title>

  </head>
  
  <body>

	<a href="global/global?id=1">global/global?id=1</a><br />
	<a href="global/global?id=2">global/global?id=2</a><br />
	<a href="global/global?id=3">global/global?id=3</a><br />

  </body>
</html>
