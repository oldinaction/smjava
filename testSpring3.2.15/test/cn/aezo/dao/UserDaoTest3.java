package cn.aezo.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.aezo.model.User;

public class UserDaoTest3 {
	
	@Test
	public void testSave() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");//一定记得写配置文件这个参数，否则报错java.lang.IllegalStateException: BeanFactory not initialized or already closed
		//Spring3.0以后getBean时支持泛型
		UserDao userDao = ac.getBean("userDaoConfig", UserDao.class);
		userDao.save(new User());
	}

}