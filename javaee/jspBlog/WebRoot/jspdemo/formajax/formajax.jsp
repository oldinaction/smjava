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
  	<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
  </head>
  
  <body>
    <%-- 
  	form表单对于checkbox的提交是以数组对象的方式进行提交的。可通过request.getParameterValues()获取此数组对象
  	通过submit按钮提交的话，会直接跳转到action页面。如果通过点击普通按钮异步提交的话，则form的action属性无效（页面不跳转）
 	--%>
  
  <fieldset>
  	<legend>form表单</legend>
  	<form id="userform" action="data.jsp" method="post"> <!-- 通过ajax提交此时action和method将会被覆盖，可以省略 -->
  		用户名：<input type="text" id="username" name="username" placeholder="请输入用户名" value="小易"/>
  		密码：<input type="password" id="password" name="password" placeholder="请输入密码" maxlength="12" value="123"/>
  		<br /><br />
  		年龄：<select id="age" name="age">
  				<option value="18">18</option>
  				<option value="20">20</option>
  				<option value="25">25</option>
  			</select>
  		性别：<label><input type="radio" class="sex" name="sex" value="1" checked>男</label>
  			<label><input type="radio" class="sex" name="sex" value="0">女 </label>
  		<br /><br />
  		爱好：<label><input type="checkbox" class="hobbys" name="hobbys" value="读书">读书 </label>
  			<label><input type="checkbox" class="hobbys" name="hobbys" value="写作" checked="checked">写作 </label>
  			<label><input type="checkbox" class="hobbys" name="hobbys" value="游戏" checked>游戏 </label>
  		<br /><br />
  		个人说明：<textarea id="description" name="description" style="width:400px;height:200px;">这个人很懒，什么也没留下!</textarea> <!-- 尽量不使用rows和cols来控制文本域的大小，存在浏览器兼容问题 -->
  		<br /><br />
  		<input type="reset" value="重    置"/><input type="button" value="保    存" onclick="saveuser();"/>
  	</form>
  </fieldset>
  	
  	<script type="text/javascript">
  		function saveuser() {
  		//1==>从提交页面获取表单元素的值，存放在对象{}中。2==>ajax。3==>服务器页面获取数据，做出反应(out.print)。4==>提交页面做出反应
  				//序列化表单值，表单元素必须要有name属性(name="key")。最终是一个json数据 [{name: "key", value: "value"},{},{}]
			var params = $("#userform").serializeArray();
				//不从form中获取数据，额外的增加数据
			params.push({name: "weibo", value: "http://weibo.com/oldinaction"});
  			$.ajax({
  				type:"post",
  				url:"dataajax.jsp",
  				data:params,
  				beforeSend:function(){},
  				success:function(data){
  					//此处的data是url(dataajax.jsp)页面out.print();中的数据，此时必须trim()一下来去除左右空格
  					if(data.trim() != "success"){
  						alert("提交失败");
  					}else{
  						alert("数据提交到服务器成功");
  					} 
  				}
  			});


/* Jquery获取值
  			//获取表单元素的值要用val(),不要用text()
  			var username = $("#username").val();
			var password = $("#password").val();
			var age = $("#age").val();
				//使用元素方式获取获取
				//var sex = $("input[type='radio'][name='sex']:checked").val();
				//var hobbys = $("input[type='checkbox'][name='hobbys']:checked"); //此处不能有val()
			//使用class获取
			var sex =$(".sex").val();
				
				//$(".hobbys").val();获取的总是匹配到的第一个元素的值，此时一直是"读书"
				//$(".hobbys:checked").val();获取的总是匹配到的第一个元素的值，此时一直是选择的第一个选项
				//直接alert($(".hobbys:checked"));或者alert($(".hobbys:checked").toString());都返回[object Object]
			var hobbys = $(".hobbys:checked");//下面这样获取的就是一个jQuery对象(里面有多个匹配到的元素)。
			var hobbysArr = [];
			hobbys.each(function(){
				hobbysArr.push($(this).val());
			});
			
			var description = $("#description").val();
			var params = {
				"username":username,
				"password":password,
				"age":age,
				"sex":sex,
				"hobbys":hobbysArr.toString(),//浏览器和服务器之间不能传递对象，只能传递字符串
				"description":description,
			};
			
  			$.ajax({
  				type:"post",
  				url:"dataajax.jsp",
  				data:params,
  				beforeSend:function(){},
  				success:function(data){
  					//此处的data是url(dataajax.jsp)页面out.print();中的数据，此时必须trim()一下来去除左右空格
  					if(data.trim() != "success"){
  						alert("提交失败");
  					}else{
  						alert("数据提交到服务器成功");
  					} 
  				}
  			});
*/

/* js获取值 
			var username = document.getElementById("username").value;
			var password = document.getElementById("password").value;
			var age = document.getElementById("age").value;
			var sex = checkValues("sex").toString();
			var hobbys = checkValues("hobbys").toString();
			var description = document.getElementById("description").value;
			var params = {
					"username":username,
					"password":password,
					"age":age,
					"sex":sex,
					"hobbys":hobbys,
					"description":description
			};
  			$.ajax({
  				type:"post",
  				url:"dataajax.jsp",
  				data:params,
  				beforeSend:function(){},
  				success:function(data){
  					//此处的data是url(dataajax.jsp)页面out.print();中的数据，此时必须trim()一下来去除左右空格
  					if(data.trim() != "success"){
  						alert("提交失败");
  					}else{
  						alert("数据提交到服务器成功");
  					} 
  				}
  			});
  			
  			//封装的获取checkbox或者radio的选中的值，返回一个数组，name表示同一类checkbox的name属性值
			function checkValues(name){
			  var check = document.getElementsByName(name); 
			  var Values = [];
			  for(var i=0; i<check.length; i++){
				if(check[i].checked){
					Values.push(check[i].value);
			    }
			  }
			  return Values;  	
  			} */
  		}
  	</script>

  </body>
</html>
