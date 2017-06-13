<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>  
    <title>c标签的使用(只需掌握c:forEach)</title>
  </head>
  
  <body>
  	<h1>c标签的使用(只需掌握c:forEach，其他做了解)</h1>
  	
	<h2>c:forEach</h2>
  	<c:forEach begin="1" end="9" var="count">${count}</c:forEach><br />
  	<!-- map不能通过${map.a}获取对应的值，这是对象的取值方法 -->
  	<%
  		Map<String,String> map = new HashMap<String,String>();
  		map.put("a", "AAA");
  		map.put("b", "BBB");
  		map.put("c", "CCC");
  		pageContext.setAttribute("maps", map);
  	%>
  	<c:forEach var="maps" items="${maps}" varStatus="mapIndex">
  		<p>${maps}</p>
  		<p style="<c:if test='${mapIndex.index == 2}'>color:red;</c:if>">
  			索引${mapIndex.index} == 键${maps.key} == 值${maps.value}
  		</p>		
  	</c:forEach>
  	
  	
  	<hr>
  	<h2>c:if</h2>
  	<!-- 可以插入在JSP页面的任何地方，包括html标签内部 -->
  	<c:if test="${1==1}">true</c:if><br />
  	<c:if test="${true}">true</c:if><br />
  	<c:if test="true">-true-</c:if><br />
  	<c:if test="${1==2}">不会打印</c:if>
  	<c:if test="1==1">不会打印</c:if>
  	<c:if test="1">不会打印</c:if>
	<hr>
	<h2>c:choose</h2>
	<!-- 相当于which case default -->
	<c:choose>
		<c:when test="${1==1}">1</c:when>
		<c:when test="${2==2}">2</c:when>
		<c:when test="${3==1}">3</c:when>
		<c:otherwise>4-otherwise只能有一个</c:otherwise>
	</c:choose>
	<hr>
	<h2>c:import</h2>
		这是c1.jsp的内容：<br />
	<c:import url="c1.jsp"></c:import><br />
		(这和jsp:include动态包含差不多，也不建议使用)<br />
	<hr>
	<h2>c:out</h2>
	<c:out value="可以输出"></c:out><br />
	<c:out value="${1+1}"></c:out>
	<hr>
	<h2>c:set  c:remove</h2>
	<!-- 相当于setAttribute() removeAttribute() -->
	<c:set var="user" value="smalle" scope="page"></c:set><!-- 此处为application/session/request/page 不要写成pageContext或者requestScope等 -->
	<c:set var="remove" value="remove" scope="request"></c:set>
	<c:remove var="remove" scope="request"/>
	<c:out value="${user}"></c:out>
	<c:out value="${remove}"></c:out>
	<hr>
	<h2>c:param 可以给一个页面传递参数</h2>
		<c:import url="a1.jsp">
	<c:param name="username" value="param-smalle"></c:param>
		</c:import>
	<hr>
	<h2>c:redirect相当于重定向</h2>
	<%--	
		<c:redirect url="a1.jsp"></c:redirect>相当于重定向
	--%>
	&lt;c:redirect url="a1.jsp"&gt;&lt;/c:redirect&gt;相当于重定向

  </body>
</html>
