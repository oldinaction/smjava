package cn.aezo.register.action;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.aezo.register.dto.UserRegisterInfo;

public class UserActionTest {

	@Test
	public void testExecute() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		UserAction ua = (UserAction) ac.getBean("u");
		UserRegisterInfo info = new UserRegisterInfo();
		info.setUsername("a");
		info.setPassword("1");
		info.setPassword2("1");
		ua.setInfo(info);
		String ret = ua.execute();
		Assert.assertEquals("success", ret);
	}
	
	@Test
	public void testList() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		UserAction ua = (UserAction) ac.getBean("u");
		ua.list();
		Assert.assertTrue(ua.getUsers().size() > 0);
	}
	
	//session已经关闭不能测试出来，在浏览器上可以调试成功(在web.xml中加了拦截器)
	@Test
	public void testLoad() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		UserAction ua = (UserAction) ac.getBean("u");
		UserRegisterInfo info = new UserRegisterInfo();
		info.setId(30);
		ua.setInfo(info);
		ua.load();
		System.out.println(ua.getUser().getClass());
		Assert.assertTrue(ua.getUser().getUsername() != null);
	}

}
