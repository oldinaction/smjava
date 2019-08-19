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

	1、访问值栈(Value Stack)中action的普通属性:<a href="ognl/ognl?name=myname">ognl/ognl?name=myname</a><br />
	2、访问值栈(Value Stack)中对象的普通属性(get set,Struts2根据url参数帮忙构建user对象):<a href="ognl/ognl?user.age=10">ognl/ognl?user.age=10</a><br />
	2.1、访问值栈(Value Stack)中对象的普通属性(自己在Action中new一个user对象):<a href="ognl/ognl">ognl/ognl</a><br />
	3、访问值栈(Value Stack)中对象的普通属性(get set):<a href="ognl/ognl?cat.friend.name=mydog">ognl/ognl?cat.friend.name=mydog</a><br />
	<br />
	27、使用[]访问action集合对象<a href="ognl/domain?name=myname">ognl/domain?name=myname</a>
  </body>
</html>
