package cn.aezo.spring.base.hello.dao.impl;

import cn.aezo.spring.base.hello.dao.UserDAO;
import cn.aezo.spring.base.hello.model.User;

//UserDAO的一个现实
public class UserDAOImpl implements UserDAO {
	private int daoId;//测试自动注入用的
	
	@Override
	public void save(User user) {
		System.out.println("a user saved!");
	}
	
	@Override
	public String toString() {
		return "daoId:" + daoId;
	}

	public int getDaoId() {
		return daoId;
	}

	public void setDaoId(int daoId) {
		this.daoId = daoId;
	}

}
