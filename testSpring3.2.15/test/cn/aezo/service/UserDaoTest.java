package cn.aezo.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.aezo.model.User;
import cn.aezo.service.UserService;

public class UserDaoTest {
	
	@Test
	public void testSave() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");//一定记得写配置文件这个参数，否则报错java.lang.IllegalStateException: BeanFactory not initialized or already closed
		UserService userService = ac.getBean("userService", UserService.class);
		userService.add(new User());
	}

}