<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html> 
<head> 
	<title>输入验证码</title> 
</head> 
<body>
	<form action="validate.jsp" method="post" name="stringCodeForm"> 
		<input type="text" name="code" size="15">
		<img id="stringCode" src="stringCode.jsp" border="1" align="absmiddle" 
			onclick="document.getElementById('stringCode').src = 'stringCode.jsp?' + Math.random();">
		<a href="javascript:void(0)" onclick = "document.getElementById('stringCode').src = 'stringCode.jsp?' + Math.random();">看不清点我</a>
		<input type="submit" value="提交">
	</form>
	
	<form action="validate.jsp" method="post" name="countCodeForm"> 
		<input type="text" name="code" size="15">
		<img id="countCode" src="countCode.jsp" border="1" align="absmiddle" 
			onclick="document.getElementById('countCode').src = 'countCode.jsp?' + Math.random();">
		<a href="javascript:void(0)" onclick = "document.getElementById('countCode').src = 'countCode.jsp?' + Math.random();">看不清点我</a>
		<input type="submit" value="提交">
	</form> 
 
	<form action="validate.jsp" method="post" name="numCodeForm"> 
		<input type="text" name="code" size="15">
		<img id="randImage" src="numCode.jsp" border="1" align="absmiddle" 
			onclick="document.getElementById('randImage').src = 'numCode.jsp?' + Math.random();">
		<a href="javascript:loadimage();">看不清点我</a>
		<input type="submit" value="提交">
	</form> 
 

<script type="text/javascript"> 
	function loadimage(){ 
		document.getElementById("randImage").src = "numCode.jsp?" + Math.random(); 
	} 
</script> 
</body> 
</html>
