package cn.aezo.annotation.aop.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.aezo.annotation.aop.model.User;
import cn.aezo.annotation.aop.service.UserService;

//DI(inversion of control):动态注入：容器根据xml文件中配置的bean的不同，实例化出不同的对象
//也叫IoC(dependency injection):控制反转：本来需要我们自己控制实例化的UserService和UserDAO(不同的对象写不同的实例化语句)，最后交给了容器帮我们实例化不同的对象

//annotation方式的aop举例
public class testUserService {
	
	//测试自动装配
	@Test
	public void testAdd() throws Exception {
		ClassPathXmlApplicationContext cac = new ClassPathXmlApplicationContext("annotationAopBeans.xml");
		
		UserService service = (UserService) cac.getBean("userService");
		User u = new User();
		u.setUsername("smalle");
		u.setPassword("123456");
		service.add(u);
		
		cac.destroy();
	}
	
}
