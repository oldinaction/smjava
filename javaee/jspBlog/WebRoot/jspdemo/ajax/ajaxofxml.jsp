<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>ajax解析xml文件</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script> 
  </head>
  
  <body>
    <%
  		/*
  			业务：解析xml文件
  			时间:2014-11-18 18:43
  			作者:小易
  			描述:利用ajax技术解析xml，获取xml的数据
  		*/
  	%>
  
  城市<select id="city" onchange="selectCity(this);"></select>
  <br /><br />
  介绍<textarea id="desc" style="width:400px;height:80px;"></textarea>
  
  
  <!-- 利用jQuery实现 -->
  <script type="text/javascript">
  $(function(){
	  $.ajax({
			type:"post",
			url:"city.xml",
			//data:{},
			beforeSend:function(data){},
			success:function(data){
				//如果直接alert(data)则输出:[Object Document] ，所以要对数据进行处理
				var html = "<option>请选择</option>";
				$(data).find("City").each(function(){
					var cityName = $(this).attr("Name");
					var description = $(this).find("Description").text();
					html += "<option value='" + cityName + "' description='" + description + "'>" + cityName + "</option>";
				});
				$("#city").html(html);	
			}
		});
  });
  
  function selectCity(obj){
	  var description = $(obj).find("option:selected").attr("description");
	  $("#desc").text(description);
  }
  </script>
  

  </body>
</html>
