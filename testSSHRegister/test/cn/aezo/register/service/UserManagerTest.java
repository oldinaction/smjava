package cn.aezo.register.service;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.aezo.register.model.User;

public class UserManagerTest {

	@Test
	public void testExists() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		UserManager um = (UserManager) ac.getBean("userManager");
		User user = new User();
		user.setUsername("a");
		boolean exists = um.exists(user);
		Assert.assertEquals(true, exists);//第一个参数表示希望的结果，第二个参数表示真正的结果
	}

	@Test
	public void testAdd() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		UserManager um = (UserManager) ac.getBean("userManager");
		User user = new User();
		user.setUsername("a");
		user.setPassword("1");
		boolean exists = um.exists(user);
		if(!exists) {
			um.add(user);
			Assert.assertEquals(true, um.exists(user));
		} else {
			fail("fail added");
		}
	}

}
