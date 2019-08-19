<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>welcome</title>
    
  </head>
  
  <body>
   
   欢迎：<br />
  requestScope.name=> ${rname}<br />
  sessionScope.sname=> ${sessionScope.sname}<br />
  ModelMap=>${mname}（使用ModelMap和环境解耦）<br />
  
   
  </body>
</html>
