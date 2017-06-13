<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/sm.tld" prefix="sm" %>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>函数的自定义标签</title>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  abc转换成大写:${sm:toUpper("abc")}
  
  <br />
  --------下面是c.jsp的测试内容--------<br />
  ${param.username}
  </body>
</html>
