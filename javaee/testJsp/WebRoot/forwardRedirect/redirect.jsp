<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>forward跳转和sendRedirect跳转区别</title>

  </head>
  <body>
	
	<!-- 访问redirect.jsp?a=1 -->
	<% 
		request.setAttribute("att", new String("smalle"));		
		response.sendRedirect("myTest.jsp");
	%>
	
  </body>
</html>
