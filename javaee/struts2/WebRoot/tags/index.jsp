<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ognl表达式</title>
    
  </head>
  <body>

	1、property取action变量值：<a href="tags/tags?username=smalle">tags/tags?username=smalle</a><br />
	<br />
	5、set设定adminName：<a href="tags/tags?username=smalle">tags/tags?username=smalle</a><br />
	6、set设置adminPassword，并指定范围为session：<a href="tags/tags?password=123">tags/tags?password=123</a><br />
	<br />
	10、if elseif else：<a href="tags/tags?age=-1">tags/tags?age=-1</a>、<a href="tags/tags?age=10&age=25">tags/tags?age=10&age=25</a>、<a href="tags/tags?age=25">tags/tags?age=25</a>
	
	
  </body>
</html>
