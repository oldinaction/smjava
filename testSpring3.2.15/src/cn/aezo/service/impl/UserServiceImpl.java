package cn.aezo.service.impl;

import cn.aezo.dao.UserDao;
import cn.aezo.model.User;
import cn.aezo.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
	@Override
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
