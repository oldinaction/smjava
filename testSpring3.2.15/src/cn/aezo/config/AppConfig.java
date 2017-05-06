package cn.aezo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.aezo.dao.UserDao;
import cn.aezo.dao.impl.UserDaoImpl;
import cn.aezo.service.UserService;
import cn.aezo.service.impl.UserServiceImpl;

//Spring3.0的一种新的生成bean的方式(xml、annotation、javaConfig)
@Configuration//把这个类当成一个配置文件看待。还需要导入cglib和asm的jar包
public class AppConfig {
	
	//相当于xml的配置<bean id="userDao" class="cn.aezo.dao.impl.UserDaoImpl" />
	@Bean(name="userDaoConfig")//如果不写name默认生成的bean的id/name为方法名
	public UserDao userDao() {
		return new UserDaoImpl();
	}
	
	//带参数的bean
	@Bean
	public UserService userService() {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setUserDao(userDao());
		return userServiceImpl;
	}
	
	
}
