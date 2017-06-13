package cn.aezo.spring.javaconfig;

import cn.aezo.spring.javaconfig.dao.UserDao;
import cn.aezo.spring.javaconfig.model.User;
import cn.aezo.spring.javaconfig.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigTest {
	
	@Test
	public void testSave() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);
		//Spring3.0以后getBean时支持泛型
		UserDao userDao = ac.getBean("userDaoConfig", UserDao.class);
		userDao.save(new User());
	}

	@Test
	public void testService() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);
		UserService userService = ac.getBean("userService", UserService.class);
		userService.add(new User());
	}

}