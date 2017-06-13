<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>pageContext实例1</title>
    
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
	pageContext对象只针对本页面，所有想在a2.jsp中利用pageContext.getAttribute("username")
  	是获取不到a1.jsp中的值，所以结果是：
	<%
		out.print(pageContext.getAttribute("username"));
	%>
  </body>
</html>
