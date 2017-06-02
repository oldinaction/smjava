<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>注册页面</title>

</head>

<body>

1、注册页面：<a href="/register.jsp">http://localhost:8080/register.jsp</a><br/>
2、用户列表界面：<a href="/user!list">http://localhost:8080/user!list</a><br/>


</body>
</html>