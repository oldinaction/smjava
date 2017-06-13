<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>登陆页面</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/tm_cookie.js"></script>
	<script type="text/javascript" src="js/util.js"></script>
	<style type="text/css">#message{text-align: center;height: 35px;color: red;font-weight: bold;line-height: 35px;display: none;}</style>
</head>
<body >
	<!-- contact-form -->	
	<div class="message warning">
	<div class="inset">
	<div class="login-head">
		<h1>登陆</h1>
		<div class="alert-close"></div> 			
	</div>
	<div id="message"></div>
		<!-- jsp就是servlet  -->
		<div id="errormessage" style="font-family:'微软雅黑';font-size:14px;color:red; ">错误信息</div>
		<form action="login" method="post">
			<ul>
				<li>
					<input type="text" name="account" id="account" autocomplete="off"  placeholder="请输入用户名称!" class="text" ><a href="#" class=" icon user"></a>
				</li>
				<li>
					<input type="password" name="password" id="password" autocomplete="off"  placeholder="请输入用户密码!"> 
					<a href="#" class="icon lock"></a>
				</li>
			</ul>
			<div class="clear"> </div>
			<div class="submit">
				<label><input type="checkbox" id="cookie" name="cookieMark">七天免登陆</label>
				<input type="button" onclick="tm_login()"  value="登 陆" >
				<h4><a href="javascript:void(0);">忘记密码 ?</a></h4>
				<div class="clear">  </div>	
			</div>
		</form>
		</div>					
	</div>
	<div class="clear"> </div>
	<!--- footer --->
	<div class="footer">
		<p>Copyright &copy; 2014.</p>
	</div>
	<script type="text/javascript">
	
	
	</script>
</body>
</html>