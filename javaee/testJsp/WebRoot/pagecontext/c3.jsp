<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>pageContext实例3</title>
    
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
  	findAttribute是按照page request session application的顺序来查找的，找到就停止。
  	结果为：
	<%
		//pageContext.getOut()获取out对象，同理pageContext还可以获取JSP的其他8个内置对象
  		pageContext.getOut().print(pageContext.findAttribute("username"));
	%>
  </body>
</html>
