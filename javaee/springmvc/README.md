# spingmvc

1. spingmvc需要引入的jar包：

	- commons-logging-1.2.jar
	- spring-aop-4.3.2.RELEASE.jar
	- spring-beans-4.3.2.RELEASE.jar
	- spring-context-4.3.2.RELEASE.jar
	- spring-core-4.3.2.RELEASE.jar
	- spring-expression-4.3.2.RELEASE.jar
	- spring-web-4.3.2.RELEASE.jar
	- spring-webmvc-4.3.2.RELEASE.jar

2. Hello World说明

	- Dispatcherservlet
		- DispatcherServlet是前置控制器，配置在web.xml文件中的。拦截匹配的请求，Servlet拦截匹配规则要自已定义，把拦截下来的请求，依据相应的规则分发到目标Controller来处理，是配置spring MVC的第一步。
	- InternalResourceViewResolver
		- 视图名称解析器
	- 以上出现的注解
		- @Controller 负责注册一个bean 到spring 上下文中
		- @RequestMapping 为控制器指定可以处理哪些 URL请求
			
### 说明

Hello SpringMVC
第一步： 导入spring3.2.15 /libs/中的所有jar包
第二步： 配置请求分发器  DispatcherServlet(web.xml)
第三步： 在classpath下建立一个 spring-servlet.xml  配置文件,配置要扫描相应bean的包
第四步： 导入日志环境  slf4j  , 并添加log4j.properties配置文件（或者暂时使用commons-logging，spring慢慢不推荐导入此包）
第五步：  编写一个controller  发送请求访问  http://localhost:8080/项目名/hello.do


MVC框架的共同点
1. url请求分发，通过url定位到一个java类的方法。
	(1)类似struts2：/user/add	====>controller/UserController
	(2)类似servlet：====>controller/DemoServlet
	(3)类似struts1：====>controller/Struts1Controller
	
2. 表单参数的获取：servlet api或者DI。SpringMVC使用的是DI(方法参数的动态注入，被动式)，也可以使用servlet api(request.getParameter,主动式)，太原始，一般不用
	(1)方法参数直接获取
	(2)使用po(model对象)进行获取
	(3)http restFul风格
	注：
	一个Controller是一个单例
	SpringMVC方法参数的动态注入比struts2性能更高
	
3. 跳转到模板，执行一个动态页面
	struts2：使用result="success"
	springmvc：
	(1)使用方法返回的字符串->对应一个view页面(更便利，不用配置很多东西),推荐
	(2)使用ModelAndView
	注：
	springmvc在方法中添加ModelMap来存储变量，并在jsp页面上使用requet相同的scope范围，通过el表达式就可获取
	springmvc和struts2的好处是都实现了与环境解耦，从而自己模拟环境，进行自动化测试。springmvc使用ModelMap存放属性达到解耦
	sevlet api的跳转
		forward： request.getRequestDispatcher("/somePage.jsp").forward(request, response);
		redirect： response.sendRedirect("/somePage.jsp");
	
	
	
	
	
	
	

