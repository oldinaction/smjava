<%@ page language="java" import="java.util.*,com.smalle.bean.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
  <head>  
    <title>el表达式</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
	<%
		//EL:全称:Expression Language:表达式语言，格式${表达式/作用域的key}
	 	//表达式:算术运算(+-*/%);逻辑判断 >(gt) ,<(lt) ,>=(ge), <=(le),==(eq),!=(ne);条件运算  ? : empty || && (位移运算符不能用) 
		//只要值放在了application,session,request,pageContext作用域里面。那么都可以用el表达式获取通过作用域的key获取对应的值
	 	//jsp里面el表达式对null或者空的判断不严格，就算为空也可能不报错，所以可用empty来判断
	%>
  		
	算数运算<br />
	&#36;{1+1} === ${1+1} 结果为 2<br /> <!-- $的转义字符为&#36; -->
	&#36;{10%7} === ${10%7} 结果为 3<br /><br />  		
	逻辑判断<br />
	&#36;{10 > 7} === ${10 > 7} 结果为 true<br />
	&#36;{10 lt 7} === ${10 lt 7} 结果为 false<br /><br />		
	条件运算<br />
  	&#36;{10 > 7 ? "对" : "错"} === ${10 > 7 ? "对" : "错"} 结果为 对<br />
  	&#36;{empty people ? "空" : "非空"} === ${empty people ? "空" : "非空"} 结果为 空<br />
  	<jsp:useBean id="user" class="com.smalle.bean.User"></jsp:useBean>
  	&#36;{!empty user ? "非空" : "空"} === ${!empty user ? "非空" : "空"} 结果为 非空<br />
  	&#36;{empty user && empty people ? "对" : "错"} === ${empty user && empty people ? "对" : "错"} 结果为 错<br />	
  	<br /><br />
  	
  	作用域<br />
  	<%
  		String name = "小易";
  		pageContext.setAttribute("name", "pageContext" + name);
  		request.setAttribute("name", "request" + name);
  		session.setAttribute("name", "session" + name);
  		application.setAttribute("name", "application" + name);	
  	%>
  	【优先获取顺序为pageContext request session application】，所以获取pageContext的值<br />
  	${name}<br />
  	获取其他作用域中的值<br />
  	${requestScope.name}<br />
  	${sessionScope.name}<br />
  	${applicationScope.name}<br />
  	<br /><br />
  	
  	关于集合和对象中的值的获取<br />
  	<%
  		User user1 = new User(); //EL表达式获取对象的属性值
  		user1.setUsername("小易");
  		user1.setAge(18);
  		user1.setSex(true);
  		pageContext.setAttribute("user1", user1);
  		
  		List<User> users = new ArrayList<User>(); //EL表达式获取集合中某个对象的属性值
  		for(int i=0; i<3; i++) {
  			User user2 = new User();
  			user2.setUsername("小易" + i);
  	  		user2.setAge(18 + i);
  	  		users.add(user2);
  		}
  		pageContext.setAttribute("users", users);
  		
  		Map<String,Object> map = new HashMap<String,Object>();
  		map.put("Map1", 1);
  		map.put("Map2", 2);
  		map.put("Map3", 3);
  		pageContext.setAttribute("map", map);	
  	%>
  	
  	<!-- EL表达式获取对象的属性值 -->
  	姓名:${user1.username} ===> 年龄:${user1.age} ===> 性别(true代表boy):${user1.sex}<br />
  	<br /> 	
  	<!-- EL表达式获取集合中某个对象的属性值 -->
  	<c:forEach var="user" items="${users}">
  		姓名:${user.username} ===> 年龄:${user.age}<br />
  	</c:forEach>
  	<br />
  	<!-- map中的key，value默认就有的，可直接使用 -->
  	法一(只能获取对于key的value)：${map.Map1} === ${map.Map2} === ${map.Map3}<br />
  	法二(可以获取单个map，同时获取此map中key和value)：<br />
  	<c:forEach var="map" items="${map}">
  		key:${map.key} ===> value:${map.value}<br />
  	</c:forEach>

  	
  	 
  	
  	
  	
  	
  	
  </body>
</html>
