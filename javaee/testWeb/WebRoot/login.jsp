<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>教务管理登陆</title> 
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/login.css">

  </head>
  
  <body>
	<div class="logo">
		<a href="#">
			<img width="245" height="69" src="images/logo.png" alt=""/>
		</a>
	</div>
	
	<div class="middle">
		<div class="left">
			<img width="385" height="289" src="images/view.jpg" alt=""/>
		</div>
		<div class="loginbox">
			<div class="title">
				<img width="45" height="45" src="images/laugh.gif" alt=""/>
				用户登录
			</div>
			<form action="" method="">
				账号：<input type="text" id="username" placeholder="请输入学号/邮箱"/><br/>
				密码：<input type="password" id="password" placeholder="请输入密码"/><br/>
				<label class="label"><input type="radio" name="identity" value="0"/>老师 </label>
				<label><input type="radio" name="identity" value="1"/>学生</label>
				<br/>
				<!-- <input type="reset" value="重    置"> -->
				<input class="button" type="submit" value="登    陆"> 
				<label><input type="checkbox" name="cookieflag" value="0"/>记住我的账号</label>
			</form>
		</div>
	</div>
	
	<div style="clear:both;"></div>
	<div class="footer">
	服务热线：010-62751023&nbsp;Email：sermis@pku.edu.cn&nbsp;地址：北京大学理科一号楼 &copy;&nbsp;志当存高远
	</div>

  </body>
</html>
