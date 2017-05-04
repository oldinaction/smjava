<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
//获取项目路径
String path = request.getContextPath();
//获取整个项目的绝对路径。getScheme()获取字符串"http",getServerName()获取主机名,getServerPort()获取端口,在加上上面获取的项目路径
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href=<%=basePath %>> <!-- 以后本页面所有href路径最终都会加上整个项目的绝对路径basePath，即"http://localhost:8080/testStruts2/" -->
    
    <title>struts2路径问题-使用basePath</title>

  </head>
  
  <body>

	<!-- 由于上面定义了base标签，所有最终以绝对路径"http://localhost:8080/testStruts2/others/index.jsp"显示 -->
	<a href="others/index.jsp">struts2路径问题，此时访问index.jsp</a>

  </body>
</html>
