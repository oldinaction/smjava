<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>利用juqery实现ajax - blog</title>
	<link href="css/style.css" rel='stylesheet' type='text/css' />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript" src="/servletBlog/js/util.js"></script>
</head>
<body oncontextmenu = self.event.returnValue = false> <!-- 这一行可以使鼠标右键失效 -->
	 <!-----start-main---->
	 <div class="main">
		<div class="login-form">
			<h1>小易个人博客登陆</h1>
			<div id="message"></div>
			<div class="head">
				<img src="images/user.png" alt=""/>
			</div>
			<form action="" method="">
				<input type="text" class="text" autocomplete="off" placeholder="请输入用户名" maxlength="16" name="account" id="account" value="smalle">
				<input type="password" placeholder="请输入密码" maxlength="16" name="password" id="password" value="123456">
				<div class="submit">
					<input type="button" value="登陆" onclick="mySubmit();" />
					<input type="checkbox" value="1" id="cookieMark"/><label for="cookieMark">7天免登陆</label>
				</div>	
				<p><a href="#">忘记密码 ?</a></p>
			</form>
		</div>
		<!--//End-login-form-->
		<!-----start-copyright---->
		<div class="copy-right">
			<p>Copyright &copy; 2014. 小易 All rights reserved.</p> 
		</div>
		<!-----//end-copyright---->
	</div>
	<!-----//end-main---->
		
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script> 
	<script type="text/javascript">	
	var account = null;
	var password = null;
	var cookieMark = null;
	//$(function(){}); 是jquery的入口函数，他会自动调用，打开页面就一会执行
	$(function(){	
		$("#account").focus();
		//敲下enter键进行登录
		$(document).keydown(function(e){
			if(e.keyCode==13){//enter键盘码是13
				mySubmit();
			}
		});	
	});
	
	function mySubmit(){
		if($("#cookieMark").prop("checked")) cookieMark = 1; //如果复选框被选中，这赋值cookieMark为1
		account = $("#account").val();
		password = $("#password").val();
		
		if(account == "" || account.length==0){ //不能写成"#account").val()==null
			sendMessage("account","用户名不能为空！");
			return false;	
		}
		
		if(account.length > 12){
			sendMessage("account","用户名长度要小于12位！");
			$("#account").select();
			return false;	
		}
		
		if(password == "" || password.length==0){
			sendMessage("password","密码不能为空！");
			return false;	
		}
		
		if(password.length > 12){
			sendMessage("password","密码长度要小于12位！");
			$("#password").select();
			return false;	
		}

		ajaxLogin();	
		return true;
	};
	
	function sendMessage(id,message){
		$("#"+id).css("border","1px solid red").focus();
		$("#message").show().html(message).fadeOut(3000,function(){
			$(this).empty(); //清空
			$("#"+id).css("border","none");
		});
	};
	
	function ajaxLogin(){
		//ajax-http请求
		$.ajax({
			type:"post",
			url:"http://127.0.0.1:8080/servletBlog/loginCookie",
			data:{
				"account" : account,
				"password" : password,
				"cookieMark" : cookieMark //最后一个没有逗号
			},
			beforeSend:function(){},
			error:function(){
				alert("ajax错误,如：ajax的url错误、mysql服务没有启动");
			},
			success:function(data){
				if(data.trim() == "success"){ //data是获取服务器给客户端的数据，即out.print()写出的内容
					var url = document.referrer;//获取页面的来访页面
					if(isEmpty(url)) {
						window.location.href = "index";
					}else {
						window.location.href = url;
					}	
				}else{
					//如果输入用户名或密码错误，页面不会跳转(浏览器地址不会改变)，这就是ajax实现了浏览器不跳转的与服务器进行数据交互
					$("#password").val("");
					$("#message").show().html("用户名或密码错误！").fadeOut(3000);
				}	
			} //最后一个没有逗号
		});
	};

	</script>
</body>
</html>