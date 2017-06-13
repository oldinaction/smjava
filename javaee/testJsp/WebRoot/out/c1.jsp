<%@ page pageEncoding="UTF-8" buffer="none"%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>out对象实例2</title>
    
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
		out.print("aaa");
		response.getWriter().write("bbb");
	%>
  </body>
</html>
