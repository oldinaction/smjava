<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>后台管理</title>   
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		*{margin:0;padding:0;}
		body{background:url(images/bg.png) repeat;}
		.box{width:1200px;margin:100px auto 0;}
		.box button{width:100px;height:50px;margin-bottom:10px;}
		.box .left{width:550px;float:left;}
		.box .left .students{float:right;}
		.box .split{width:100px;float:left;}
		.box .split img{display:block;margin:0 auto;}
		.box .right{width:550px;float:left;}
		.box .right .test{float:left;}
		.box .img1{float:left;transform:rotate(20deg);}
		.box .img2{float:right;transform:rotate(-20deg); }
	</style>
  </head>
<body>
	
	<div class="box">
		<div class="left">
			<img class="img1" width="300" height="300" src="images/img1.jpg" alt=""/>
			<%@ include file="students.jsp" %>
		</div>
		
		<div class="split">
			<img src="images/split.png"/>
		</div>
		
		<div class="right">
			<%@ include file="test.jsp" %>
			<img class="img2" width="300" height="300" src="images/img2.jpg" alt=""/>
		</div>
	
	</div>

</body>	
	
</html>




