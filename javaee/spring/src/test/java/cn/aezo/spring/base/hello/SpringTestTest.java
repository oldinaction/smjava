package cn.aezo.spring.base.hello;

import cn.aezo.spring.base.hello.dao.UserDAO;
import cn.aezo.spring.base.hello.model.User;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;

/**
 * Spring结合Junit测试，方法：
 * (1) 导入 spring-test-3.2.0.RELEASE.jar  ---- 提供与Junit的整合
 * (2) 继承AbstractJUnit4SpringContextTests类或者使用@RunWith(SpringJUnit4ClassRunner.class)
 * (3) 通过@ContextConfiguration("classpath:beans.xml")获取spring配置文件中的bean
 * (4) 还需要commons-logging的jar包
 */
@ContextConfiguration("classpath:cn/aezo/spring/base/beans.xml")
public class SpringTestTest extends AbstractJUnit4SpringContextTests {

	@Resource(name="ubase")
	private UserDAO userDao;
	
	@Test
	public void testSave() {
		this.userDao.save(new User());
	}

}