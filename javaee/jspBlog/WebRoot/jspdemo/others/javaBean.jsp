<%@ page language="java" import="com.smalle.bean.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML> <!-- 这一行是html5的标准头，不写则下面的placeholder无效 -->
<html>
  <head>
    <title>javaBean的实例</title>
    
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
	<jsp:useBean id="user" class="com.smalle.bean.User" scope="page"></jsp:useBean>
	<jsp:setProperty property="username" name="user" value="小易"/>
	<jsp:getProperty property="username" name="user"/>
	<%--
		上面三行代码实际原型是
		User user = page.getAttribute("user");
		if(user == null) {
			user = new User();
			page.setAttribute("user",user);
		}
	
		user.setUsername("小易");
		page.setAttribute("user",user);
		
		page.getAttribute("user").getUsername();
	--%>
	
	<br />
	<!-- form表单提交过来的 --><!-- 注意是通过name属性进行提交的 -->
	<form action="javaBean.jsp" method="post">
		<input type="text" name="formAge" placeholder="请输入年龄(数字)"/>请输入年龄(数字)
		<input type="submit" value="提交"/>
	</form>
  	<jsp:setProperty property="age" name="user" param="formAge"/>
  	<jsp:getProperty property="age" name="user"/>
  </body>
</html>
