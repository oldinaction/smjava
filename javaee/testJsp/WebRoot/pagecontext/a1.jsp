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
	<%
		pageContext.setAttribute("username", "小易");
	%>
	利用pageContext对象的getAttribute方法从a1.jsp(此页面)获得的username的值是：
	<%
		out.print(pageContext.getAttribute("username"));
	%>
  </body>
</html>
