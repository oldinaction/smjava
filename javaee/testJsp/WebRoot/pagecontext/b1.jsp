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
	<%
		pageContext.setAttribute("username", "小易SESSION", PageContext.SESSION_SCOPE);
	%>
	<a href="/testJsp/pagecontext/b2.jsp">访问b2.jsp</a>
  </body>
</html>
