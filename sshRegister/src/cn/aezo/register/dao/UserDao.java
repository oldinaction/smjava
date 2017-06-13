package cn.aezo.register.dao;

import java.util.List;

import cn.aezo.register.model.User;

public interface UserDao {
	public boolean checkUserExistsWithName(String username);
	public void save(User user);//save常识更像是数据层的取名，而UserManager中的add则更像是业务层的取名
	public List<User> getUsers();
	public User loadById(int id);
}
