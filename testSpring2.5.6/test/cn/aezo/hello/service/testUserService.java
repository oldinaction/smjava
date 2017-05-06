package cn.aezo.hello.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.aezo.hello.model.User;

//DI(inversion of control):动态注入：容器根据xml文件中配置的bean的不同，实例化出不同的对象
//也叫IoC(dependency injection):控制反转：本来需要我们自己控制实例化的UserService和UserDAO(不同的对象写不同的实例化语句)，最后交给了容器帮我们实例化不同的对象
public class testUserService {
	
	@Test
	public void testAdd() throws Exception {
		//读取xml配置的bean文件。一定记得写配置文件这个参数，否则报错java.lang.IllegalStateException: BeanFactory not initialized or already closed
		//BeanFactory bf = new ClassPathXmlApplicationContext("beans.xml");//BeanFactory实现了ApplicationContext
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");//使用ApplicationContext可以完成更多的业务，还可以使用ClassPathXmlApplicationContext
		
		UserService service = (UserService)ac.getBean("userService");//通过读取xml获取对外服务的那个对象
		
		UserService service1 = (UserService)ac.getBean("userService");//
		System.out.println(service == service1);//由于配置文件中设置生命范围scope="prototype"，所有此处返回false。不设置的话就是true,表示getBean得到的是同一个对象
		
		User u = new User();
		u.setUsername("smalle");
		u.setPassword("123456");
		
		service.add(u);
		
	}
	
	//测试自动装配
	@Test
	public void testAoutwire() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		
		UserService service2 = (UserService)ac.getBean("userService1");
		System.out.println(service2.getUserDAO());
	}
	
	//测试生命周期
	@Test
	public void testLife() throws Exception {
		ClassPathXmlApplicationContext cac = new ClassPathXmlApplicationContext("beans.xml");//要使用ClassPathXmlApplicationContext才能调destroy方法
		
		UserService service1 = (UserService)cac.getBean("userService2");
		cac.destroy();
	}
	
	@Test
	public void testAop() throws Exception {
		ClassPathXmlApplicationContext cac = new ClassPathXmlApplicationContext("beans.xml");
		
		UserService service = (UserService)cac.getBean("userService");//通过读取xml获取对外服务的那个对象
		
		User u = new User();
		u.setUsername("smalle");
		u.setPassword("123456");
		
		service.add(u);
		
	}
	
}
