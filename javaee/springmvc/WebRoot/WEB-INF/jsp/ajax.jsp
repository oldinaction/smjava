<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ajax</title>
    

  </head>
  
  <body>
 
  	<button onclick="test();" >test</button>
  	
<script type="text/javascript">
	var user={
		id:1,
		name:"小易",
		password:"123"
	};
	
	var str = JSON.stringify(user);
	console.info(str);
	
	function test(){
		$.ajax({
			url : "/ajax/add.do",
			type : "post",
			contentType : "application/json;charset=utf-8",
			data : str,
			success : function(data) {   
				alert("设置成功！");    
		    },   
		    error : function() {   
		        alert("网络连接出错！");   
		    } 
		});
	}
</script>
	
  </body>
</html>
