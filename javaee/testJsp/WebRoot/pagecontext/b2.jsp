<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>pageContext实例2</title>
    
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
  	在b2.jsp中利用pageContext操作ServletRequest域对象来获取b1.jsp中的username的值，结果为：
	<%
		//或者为session.getAttribute("username");
		out.print(pageContext.getAttribute("username",PageContext.SESSION_SCOPE));
	%>
  </body>
</html>
