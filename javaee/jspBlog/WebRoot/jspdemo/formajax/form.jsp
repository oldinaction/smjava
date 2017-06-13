<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML> <!-- html5标准头写法，如果没有则placeholder等html5新属性不能使用 -->
<html>
  <head>
    
    <title>表单提交</title>
    
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
  <%-- 
  	form表单对于checkbox的提交是以数组对象的方式进行提交的。可通过request.getParameterValues()获取此数组对象
  	通过submit按钮提交的话，会直接跳转到action页面。如果通过点击普通按钮异步提交的话，则form的action属性无效
  --%>
  
  <fieldset>
  	<legend>form表单</legend>
  	<form action="data.jsp" method="post">
  		用户名：<input type="text" name="username" placeholder="请输入用户名" value="小易"/>
  		密码：<input type="password" name="password" placeholder="请输入密码" maxlength="12" value="123"/>
  		<br /><br />
  		年龄：<select name="age">
  				<option value="18">18</option>
  				<option value="20">20</option>
  				<option value="25">25</option>
  			</select>
  		性别：<label><input type="radio" name="sex" value="1">男</label>
  			<label><input type="radio" name="sex" value="0">女 </label>
  		<br /><br />
  		爱好：<label><input type="checkbox" name="hobbys" value="读书">读书 </label>
  			<label><input type="checkbox" name="hobbys" value="写作">写作 </label>
  			<label><input type="checkbox" name="hobbys" value="游戏">游戏 </label>
  		<br /><br />
  		个人说明：<textarea name="description" style="width:400px;height:200px;">这个人很懒，什么也没留下!</textarea> <!-- 尽量不使用rows和cols来控制文本域的大小，存在浏览器兼容问题 -->
  		<br /><br />
  		<input type="reset" value="重    置"/><input type="submit" value="提    交"/>
  	</form>
  </fieldset>

  </body>
</html>
