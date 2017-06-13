<%@ page pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE HTML>
<html>
  <head>  
    <title>jsp指令 - 关于page指令中isErrorPage属性的实例</title>
    
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
	这是错误页面，如果1_1.jsp发生错误，就跳转到此页面<br />
	错误信息是：
	<%
		out.print(exception.getMessage());
	%>
  </body>
</html>
