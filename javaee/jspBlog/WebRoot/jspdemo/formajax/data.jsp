<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>   
    <title>form.jsp提交过来的数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<%request.setCharacterEncoding("UTF-8"); %>
	用户名：<%=request.getParameter("username") %> 【用EL表达式获取：${param.username}】<br /><br />
	密码：<%=request.getParameter("password") %> 【用EL表达式获取：${param.password}】<br /><br />
	年龄：<%=request.getParameter("age") %> 【用EL表达式获取：${param.age}】<br /><br />
	性别：<%=request.getParameter("sex") %> 【用EL表达式获取：${param.sex}】<br /><br />
	<%
		String[] hobbyes = request.getParameterValues("hobbyes");
		StringBuffer hobbysBuf = new StringBuffer();
		for(String str : hobbyes){
			hobbysBuf.append(str + " ");
		}
	%>
	爱好：<%=hobbysBuf %> <br /><br />
	个人说明：<%=request.getParameter("description") %> 【用EL表达式获取：${param.description}】<br /><br />
	

  </body>
</html>
