package cn.aezo.spring.base.annotation.service;

import cn.aezo.spring.base.annotation.dao.UserDAO;
import cn.aezo.spring.base.annotation.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

//对外的一个服务，在此处控制抽象的UserDAO来完成业务
@Component("userServiceAnno")
public class UserService {
	//当初始化UserService时需要UserDAO的一个实例(或者UserDAOImpl)
	private UserDAO userDAO;
	
	public void add(User user) {
		this.userDAO.save(user);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	/*自动注入法一：使用spring的批注;注意看注释
	@Autowired//默认根据Type自动注入(即xml文件中的bean的class属性)，当同类型的有多个对象时就包错，此时可以使用@Qualifier来标明根据name(id)来自动注入bean
	public void setUserDAO(@Qualifier(value="u1") UserDAO userDAO) {//当初始化UserService时需要UserDAO的一个实例(或者UserDAOImpl)，所有到xml文件中取找相应的bean，找到u1后，自动注入进来。这样UserService就实例化成功
		this.userDAO = userDAO;
	}
	*/
	
	//法二：使用j2ee的批注;较常用
	@Resource(name="u1")
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@PostConstruct//相当于在xml中配置init-method
	public void init() {
		System.out.println("init[annotation-@PostConstruct]");
	}
	
	@PreDestroy//相当于在xml中配置destroy-method
	public void destory() {
		System.out.println("destory[annotation-@PreDestroy]");
	}
	
}
