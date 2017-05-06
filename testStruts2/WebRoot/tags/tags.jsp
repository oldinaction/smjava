<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<base href="<%=basePath%>">
	
    <title>Struts2标签</title>

  </head>
  <body>
	Struts2标签<br />
	
	1、property取action变量值(由于property的value的值是一个对象，他会将username解析为对象)：<s:property value="username" /><br />
	2、property取字符串值：<s:property value="'username'" /><br />
	3、property设定默认值：<s:property value="admin" default="管理员" /><br />
	4、property展现HTML文本,escape属性默认是true,要设成false：<s:property value="'<b>HTML</B>'" escape="false" /><br />
	<hr>
	<!-- 此时value中的username指action中的username，相当于在Stack Context中换了个变量名再设置了一遍 -->
	<!-- 如果标签对应的value的属性类型是Object时，且value中的值可以从值栈中获取就视为ognl表达式;官方文档set标签的value属性是String,是个bug -->
	5、set设定adminName(默认是设在request和actionContext/StackContext中)：<s:set var="adminName" value="username"/><br />
	5.1、从actionContext和StackContext中取adminName值：<s:property value="adminName" /> | <s:property value="#adminName" /><br />
	5.2、从StackContext的request中取adminName：<s:property value="#request.adminName" /><br />
	6、set设置adminPassword，并指定范围为session(默认是action)：<s:set var="adminPassword" value="password" scope="session"/><br />
	6.1、从session中取adminPassword值：<s:property value="#session.adminPassword" /><br />
	6.2、此时request中获取不到值<s:property value="#request.adminPassword" /><br />
	<hr>
	7、bean的定义，并使用param标签来设定bean的属性值
	<s:bean name="cn.aezo.tags.model.Dog" var="myDog">
		<s:param name="name" value="'myDogName'"></s:param><!-- 此时param中的name属性的值name和Dog的的成员变量name对应，注意param中的value属性类型是String -->
		<%--
		<s:property value="name" />
		<s:debug></s:debug><!-- debug在这里就可以在Value Stack中看到cn.aezo.tags.model.Dog的对象 -->
		--%>
	</s:bean><br />	
	7.1、查看bean：<s:property value="#myDog" /><br />	
	<hr>
	8、include导入外部文件(较少使用)：(1)<s:include value="include.html"/>(2)<s:include value="'include.html'"/><br />
	8.1、%{}，其中%可以将{}中的内容强制转换为ognl表达式。
	<s:set var="incFile" value="'include.html'"/><!-- 此处的必须加单引号 -->
	(1)<s:include value="%{#incFile}"/>
	(2)<s:include value="#incFile"/><br /><!-- include标签的类型是String，所有把#incFile当成字符串处理 -->
	<hr>
	9、fielderror标签：<s:fielderror fieldName="myError" />
	<hr>
	10、if elseif else：
	<s:property value="#parameters.age"/>
	<s:set var="age" value="#parameters.age[0]"></s:set><!-- 此处要带上[0],即取第一个. debug中显示有问题 -->

	<s:if test="#age < 0">wrong age!</s:if>
	<s:elseif test="#age < 20">too yong!</s:elseif>
	<s:else>yeah!</s:else>

	&nbsp;&nbsp;<s:if test="#parameters.abc == null">parameters.abc == null</s:if><br />
	<hr>
	11、iterator遍历
	<ul>
		<li>遍历数组：
		<s:iterator value="{1, 2, 3}">
			<s:property/> |
		</s:iterator>
		</li>
		<li>自定义变量：
		<s:iterator value="{'aa', 'b', \"c\"}" var="i"><!-- 如果''单引号中只有一个字符，则当成字符，如果有多个字符，则当成字符串；可以使用\转义双引号，如：\"a\"表示字符串 -->
			<s:property value="#i.toUpperCase()"/> |
		</s:iterator>
		</li>
		<li>使用status(保存这当前循环的相关信息)：<br />
		<s:iterator value="{'aa', 'b', 'cc'}" status="status">
			当前索引：<s:property value="#status.index"/> |<!-- 一定要加#号 -->
			遍历过元素的总数：<s:property value="#status.count"/> |
			遍历过元素的总数是偶数？：<s:property value="#status.even"/> |
			遍历过元素的总数是奇数？：<s:property value="#status.odd"/> |
			当前是第一个元素？：<s:property value="#status.first"/> |
			当前是最后一个元素？：<s:property value="#status.last"/>
			<br />
		</s:iterator>
		</li>
		<li>遍历集合：<br />
		<s:iterator value="#{1:'a', 2:'bb', 'c':'333'}">
			<s:property value="key"/> | <s:property value="value"/>
			<br />
		</s:iterator>
		</li>
		<li>自定义变量遍历集合：<br />
		<s:iterator value="#{1:'a', 2:'bb', 'c':'333'}" var="i">
			<s:property value="#i.key"/> | <s:property value="#i.value"/>
			<br />
		</s:iterator>
		</li>
	</ul>
	<hr>
	12、UI标签(较少使用)
	<s:form>
		<s:textarea value="textarea"></s:textarea>
	</s:form>
	<!-- UI主题为simple时，仍会给错误信息加上一个ul li标签，法一：可自己根据对应给的id/name进行css修改 -->
	<!-- 法二：自己定义了一个主题mytheme,除了fielderror.ftl做了处理，其他都和simple里文件一样，当使用mytheme时就不会加ul li标签了 -->
	<s:fielderror fieldName="myError" />
	
	
	
	<s:debug></s:debug>

	
  </body>
</html>
