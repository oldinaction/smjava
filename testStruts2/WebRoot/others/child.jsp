<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>包的继承</title>

  </head>
  
  <body>

	<a href="child/global?id=1">child/global?id=1</a><br />
	<a href="child/global?id=2">child/global?id=2</a><br />
	<a href="child/global?id=3">child/global?id=3</a>继承了global包的global-results

  </body>
</html>
