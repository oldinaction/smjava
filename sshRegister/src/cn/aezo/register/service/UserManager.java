package cn.aezo.register.service;

import java.util.List;

import cn.aezo.register.model.User;

public interface UserManager {

	public abstract boolean exists(User user) throws Exception;

	public abstract void add(User user) throws Exception;

	public abstract List<User> getUsers();

	public abstract User loadById(int id);

}