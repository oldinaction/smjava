package cn.aezo.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import cn.aezo.model.User;

/**
 * Spring结合Junit测试
 * 方法：
 * 导入 spring-test-3.2.0.RELEASE.jar  ---- 提供与Junit的整合 
 * 继承AbstractJUnit4SpringContextTests类或者使用@RunWith(SpringJUnit4ClassRunner.class)
 * 通过@ContextConfiguration("classpath:beans.xml")获取spring配置文件中的bean
 * 还需要commons-logging的jar包
 */
@ContextConfiguration("classpath:beans.xml")
public class UserDaoTest2 extends AbstractJUnit4SpringContextTests {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Test
	public void testSave() {
		this.userDao.save(new User());
	}

}