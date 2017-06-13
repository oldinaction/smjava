<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>JSP动态包含实例</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 	 后面的数据是a1.jsp传递的参数的值:
  	${param.user}
  	<%--
  		el表达式获取jsp页面超链接参数的值,用${param.参数名}获取;或者使用<%=request.getParameter("user")%>获取
  		el表达式获取session里保存的数据,用${session.变量名},其他同理
  	--%>   
  </body>
</html>
