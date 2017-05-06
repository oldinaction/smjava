package cn.aezo.sh.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.aezo.sh.model.User;

public class testUserService {
	
	@Test
	public void testAdd() throws Exception {
		ClassPathXmlApplicationContext cac = new ClassPathXmlApplicationContext("beans.xml");
		
		UserService service = (UserService) cac.getBean("userService");
		
		User u = new User();
		u.setName("smalle");
		
		service.add(u);
		
	}
	
}
