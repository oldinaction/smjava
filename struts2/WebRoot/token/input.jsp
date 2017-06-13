<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>使用token拦截器防止重复提交</title>

  </head>
  
  <body>

	<form action="token/user" method="post">
		用户名：<input type="text" name="username"/>
		
		<s:token></s:token>
		
		<input type="submit" value="提交"/>
	</form>


  </body>
</html>
