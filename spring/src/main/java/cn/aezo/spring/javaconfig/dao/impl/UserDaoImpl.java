package cn.aezo.spring.javaconfig.dao.impl;

import cn.aezo.spring.javaconfig.dao.UserDao;
import cn.aezo.spring.javaconfig.model.User;

public class UserDaoImpl implements UserDao {
	public void save(User user) {
		System.out.println("a user saved!");
	}
}
