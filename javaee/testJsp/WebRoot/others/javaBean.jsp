<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE HTML> <!-- 这一行是html5的标准头，不写则下面的placeholder无效 -->
<html>
  <head>
    <title>javaBean的实例/jsp:useBean的使用</title>

  </head>
  <body>
  
  	<%-- id="userBean"指的是使用User类new出来的对象的名称; scope默认是page,Bean的作用范围(此处是当前页面范围),还可取值request|session|application; type值Bean类型(可以设置为Object类型,从而使用多态) --%>
	<jsp:useBean id="userBean" class="cn.aezo.bean.User" scope="page"></jsp:useBean>
	
	<jsp:setProperty name="userBean" property="username" value="小易"/><%-- jsp:setProperty和jsp:getProperty需要jsp:useBean先实例化一个javaBean对象(此处的userBean) --%>
	<jsp:getProperty name="userBean" property="username" /><%-- 此处的property="username"中的username指的是User类中的成员变量username,注意变量名一致 --%>
	<%
		//上面两行代码类似标签的形式，下面两行代码是类似java的形式
		userBean.setUsername("smalle");//相当于<jsp:setProperty name="userBean" property="username" value="smalle"/>
		out.print(userBean.getUsername());
	%>
	<%--
		上面三行代码实际原型是
		User userBean = page.getAttribute("user");
		if(userBean == null) {
			userBean = new User();
			page.setAttribute("user", userBean);
		}
	
		userBean.setUsername("小易");
		page.setAttribute("user", userBean);
		
		page.getAttribute("user").getUsername();
	--%>
	
	
	<br /><br />
	<!-- form表单提交过来的 --><!-- 注意是通过name属性进行提交的 -->
	<form action="javaBean.jsp" method="post">
		<input type="text" name="age" placeholder="请输入年龄(数字)"/>
		<input type="submit" value="提交"/>
	</form>
	<%
  		request.getParameter("age");
  	%>
  	<jsp:setProperty property="age" name="userBean" param="age"/>
  	<jsp:getProperty property="age" name="userBean"/>
 
  
  <!-- 
  在servlet中使用Bean和在通常程序中使用Bean类似
（1）java本身是咖啡，bean是豆，javaBean是咖啡豆；
（2）狭义的javaBean是一个可视化的组件，广义的javaBean是一个特殊的类
（3）要求有：
==>属性名称第一个字母必须小写,且私有
==>一般具有getters/setters
（4）Web中的Bean一般没有GUI(图形)表现
（5）javaBean一般用来实现某一业务逻辑或取得特定结果
（6）jsp中使用javaBean不要使用裸体类
   -->
  
  
  </body>
</html>
