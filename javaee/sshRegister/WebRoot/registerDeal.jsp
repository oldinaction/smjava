<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="UTF-8"%>
<%@ page import="cn.aezo.register.model.*"%>
<%@ page import="cn.aezo.register.service.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

User user = new User();
user.setUsername(request.getParameter("username"));
user.setPassword(request.getParameter("password"));

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册处理页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

  </head>
  
  <body>

中间版本页面，最后没用

  </body>
</html>
