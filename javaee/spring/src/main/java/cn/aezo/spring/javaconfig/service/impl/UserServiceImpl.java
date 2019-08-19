package cn.aezo.spring.javaconfig.service.impl;

import cn.aezo.spring.javaconfig.dao.UserDao;
import cn.aezo.spring.javaconfig.model.User;
import cn.aezo.spring.javaconfig.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void add(User user) {
		this.userDao.save(user);
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
