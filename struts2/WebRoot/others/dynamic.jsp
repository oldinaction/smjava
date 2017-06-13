<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>动态结果集</title>

  </head>
  
  <body>

	<a href="others/dynamicResult?id=1">others/dynamicResult?id=1</a><br />
	<a href="others/dynamicResult?id=2">others/dynamicResult?id=2</a>

  </body>
</html>
