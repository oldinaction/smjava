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
  	<%
		pageContext.setAttribute("username", "小易page");
		pageContext.setAttribute("username", "小易request", PageContext.REQUEST_SCOPE);
		pageContext.setAttribute("username", "小易session", PageContext.SESSION_SCOPE);
		pageContext.setAttribute("username", "小易application", PageContext.APPLICATION_SCOPE);
		
		//转发(这种方法更方便)，url是给服务器使用的，所以绝对路径不用加项目名
		pageContext.forward("/pagecontext/c3.jsp");
	%> 
  </body>
</html>
