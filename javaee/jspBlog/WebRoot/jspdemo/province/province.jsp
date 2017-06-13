<%@ page language="java" import="java.util.*,com.smalle.province.ProvinceDao" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
  <head>   
    <title>省市区级联</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<style type="text/css">
		form{display:block;width:800px;height:400px;margin:200px auto;}
	</style>
	
<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
  </head>
 
  <body>
    <%
    	/*
    	业务：省市区级联
    	时间：2014.11.09 10.39
    	作者：小易
    	*/ 
    %>
    
    <% 
    	List<HashMap<String, Object>> maps = ProvinceDao.findProvinces();
    	pageContext.setAttribute("provinces", maps);
    %>
    
    <form>
    	省<select id= "province" onchange="select_province(this);">
	    	<option>-请选择-</option>
	    	<c:forEach var="pro" items="${provinces}">
	    		<option value="${pro.id}">${pro.name}</option>
	    	</c:forEach>
	    </select>
	    &nbsp;&nbsp;&nbsp;&nbsp;
		市<select id= "city" onchange="select_city(this);">
	    	<option>-请选择-</option>
	    	<option>武汉</option>
	    	<option>宜昌</option>
	    </select>
	    &nbsp;&nbsp;&nbsp;&nbsp;
	          区<select id="area">
	    	<option>-请选择-</option>
	    	<option>武昌区</option>
	    	<option>洪山区</option>
	    </select>	
    </form>
    
    <script type="text/javascript">
    	//选择了省
    	function select_province(obj){
    		var provinceId = $(obj).val();
    		$.ajax({
    			type:"post",
    			url:"cityDao.jsp",
    			data:{"provinceId":provinceId},
    			beforeSend:function(){},
    			success:function(data){
    				if(data.trim() == "fail"){
    					alert("获取失败");
    				} else {
    					var jsonData = eval(data); //将字符串的json对象转换成json
						tm_append_template(jsonData,"city");
    				}		
    			}
    		});
    	}
    	
    	//选择了市
    	function select_city(obj){
    		var cityId = $(obj).val();
    		$.ajax({
    			type:"post",
    			url:"areaDao.jsp",
    			data:{"cityId":cityId},
    			beforeSend:function(){},
    			success:function(data){
    				if(data.trim() == "fail"){
    					alert("获取失败");
    				} else {
    					var jsonData = eval(data); //将字符串的json对象转换成json
						tm_append_template(jsonData,"area");
    				}		
    			}
    		});
    	}
    	
    	//通用数据模板
		function tm_append_template(jsonData,target){
			var length = jsonData.length;
			var html = "<option>-请选择-</option>";
			for(var i=0; i<length; i++){
				html += "<option value='" + jsonData[i].id + "'>" + jsonData[i].name + "</option>";
			}
			$("#" + target).html(html);
		};
    </script>
    
  </body>
</html>
