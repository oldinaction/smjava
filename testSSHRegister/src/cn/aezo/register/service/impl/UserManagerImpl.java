package cn.aezo.register.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.aezo.register.dao.UserDao;
import cn.aezo.register.model.User;
import cn.aezo.register.service.UserManager;

@Component("userManager")
public class UserManagerImpl implements UserManager {
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	@Resource//默认取找set方法名去掉set后首字母小写的那个变量名(userDao)
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public boolean exists(User user) throws Exception {
		return userDao.checkUserExistsWithName(user.getUsername());
	}
	
	@Override
	public void add(User user) throws Exception {
		userDao.save(user);
	}
	
	@Override
	public List<User> getUsers() {
		return userDao.getUsers();
	}
	
	@Override
	public User loadById(int id) {
		return this.userDao.loadById(id);
	}


}
