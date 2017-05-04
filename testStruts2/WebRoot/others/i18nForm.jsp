<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 	<base href="<%=basePath%>">
    
    <title>i18n国际化</title>

  </head>
  
  <body>
  	
	<form action="others/i18n" method="post">
		<input type="text" name="username"/>
		<input type="submit" value="提交"/>
	</form>
	
  </body>
</html>
