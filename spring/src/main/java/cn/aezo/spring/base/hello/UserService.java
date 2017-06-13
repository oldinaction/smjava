package cn.aezo.spring.base.hello;

import cn.aezo.spring.base.hello.dao.UserDAO;
import cn.aezo.spring.base.hello.model.User;

//对外的一个服务，在此处控制抽象的UserDAO来完成业务
public class UserService {
	private UserDAO userDAO;
	
	public void add(User user) {
		this.userDAO.save(user);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void init() {
		System.out.println("init[xml-init-method]");
	}
	
	public void destory() {
		System.out.println("destory[xml-destroy-method]");
	}
	
}
