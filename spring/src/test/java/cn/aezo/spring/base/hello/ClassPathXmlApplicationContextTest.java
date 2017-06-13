package cn.aezo.spring.base.hello;

import cn.aezo.spring.base.hello.dao.UserDAO;
import cn.aezo.spring.base.hello.model.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassPathXmlApplicationContextTest {
	
	@Test
	public void testSave() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/aezo/spring/base/beans.xml");//一定记得写配置文件这个参数，否则报错java.lang.IllegalStateException: BeanFactory not initialized or already closed
		//Spring3.0以后getBean时支持泛型
		UserDAO userDao = ac.getBean("ubase", UserDAO.class);
		userDao.save(new User());
	}

}