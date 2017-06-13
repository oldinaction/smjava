<%@ page pageEncoding="UTF-8" errorPage="/jspzhiling/1b.jsp"%>
<!DOCTYPE HTML>
<html>
  <head>  
    <title>jsp指令 - 关于page指令中errorPage属性的实例</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  表达式1/0是否有错？
	<% 
		out.print(1/0);
	%>
  </body>
</html>
