package cn.aezo.dao.impl;

import cn.aezo.dao.UserDao;
import cn.aezo.model.User;

public class UserDaoImpl implements UserDao {
	@Override
	public void save(User user) {
		System.out.println("a user saved!");
	}
}
