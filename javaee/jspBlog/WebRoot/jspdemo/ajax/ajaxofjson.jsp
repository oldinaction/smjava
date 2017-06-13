<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>ajax解析json文件</title>
    
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
  			业务：解析json文件
  			时间:2014-11-18 19:01
  			作者:小易
  			描述:利用ajax技术解析josn，获取josn的数据
  		*/
  	%>
  
  城市<select id="city" onchange="selectCity(this);"></select>
  <br /><br />
  介绍<textarea id="desc" style="width:400px;height:80px;"></textarea>
  
  
  <!-- 利用jQuery实现 --> 
  <script type="text/javascript">
  $(function(){
/* 	
	(1)服务器和js只能进行字符串的数据交换，不能进行对象交换
	(2)json相当于一个js对象，这样可以通过对象.属性更方便的获取值
	(3)使用josn数据的步骤：将java数据转成字符串(方便服务器和js进行数据交换)，再转成json对象(方便利用js展示)
	java对象集合，字符串，数组--jsonutil.searialize(object) ==> jsonstring--eval(jsonstring) ==> json对象--解析展示
	var jsonObj = eval(jsonString);//讲字符串形式的json字符串转换成 json对象 。将json对象转换成字符串形式在sm_util.js中封装了
	
	.json的文件就相当于对.txt文件在内部执行了一次eval()方法，这个方法是JavaScript的
 */
 
	  $.ajax({
			type:"post",
			url:"city.json",
			//data:{},
			beforeSend:function(data){},
			success:function(data){
				//如果直接alert(data); //则输出:[object Object],[object Object],[object Object],[object Object],[object Object],[object Object] 
				//alert(jsonToString(data)); //这样输出为[{"name":"北京","description":"北京是..."},{},{}]
				var jsonArr = data;
				if(jsonArr != null && jsonArr.length > 0){
					var html = "<option>请选择</option>";
					for(var i=0; i<jsonArr.length; i++){
						var cityName = jsonArr[i].name;
						var description = jsonArr[i].description;
						html += "<option value='" + cityName + "' description='" + description + "'>" + cityName + "</option>";
					}
					$("#city").html(html);
				}
			}
		});
  });
  
  function selectCity(obj){
	  var description = $(obj).find("option:selected").attr("description");
	  $("#desc").text(description);
  }
  
  
  
  //与这个实例没关系
  //可以打印json数据，如果直接alert(data)则输出:[object Object],[object Object]
  function jsonToString(obj) {
		var THIS = this;
		switch (typeof (obj)) {
		case 'string':
			return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';
		case 'array':
			return '[' + obj.map(THIS.jsonToString).join(',') + ']';
		case 'object':
			if (obj instanceof Array) {
				var strArr = [];
				var len = obj.length;
				for (var i = 0; i < len; i++) {
					strArr.push(THIS.jsonToString(obj[i]));
				}
				return '[' + strArr.join(',') + ']';
			} else if (obj == null) {
				return 'null';

			} else {
				var string = [];
				for ( var property in obj)
					string.push(THIS.jsonToString(property) + ':'
							+ THIS.jsonToString(obj[property]));
				return '{' + string.join(',') + '}';
			}
		case 'number':
			return obj;
		case false:
			return obj;
		}
	}
  </script>
  

  </body>
</html>
