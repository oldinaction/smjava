<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>action中访问web元素(request、session、application)</title>

  </head>
  
  <body>

	<form name="f" action="" method="post">
		<input type="button1" value="提交1" onclick="javascript:document.f.action='web/web1';document.f.submit();">
		<input type="button2" value="提交2" onclick="javascript:document.f.action='web/web2';document.f.submit();">
		<input type="button3" value="提交3" onclick="javascript:document.f.action='web/web3';document.f.submit();">
		<input type="button4" value="提交4" onclick="javascript:document.f.action='web/web4';document.f.submit();">
	</form>
	

  </body>
</html>
