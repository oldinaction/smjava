package cn.aezo.register.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.aezo.register.dao.UserDao;
import cn.aezo.register.model.User;

@Component("userDao")//由spring自动初始化批注有@Component(value="")的类，相应对象的变量名为value的值
public class UserDaoImpl implements UserDao {
	private HibernateTemplate hibernateTemplate;

	@Override
	public boolean checkUserExistsWithName(String username) {
		List<User> users = hibernateTemplate.find("from User u where u.username='"+ username +"'");
		if(users != null && users.size() > 0) return true;
		return false;
	}

	@Override
	public void save(User user) {
		hibernateTemplate.save(user);
	}
	
	@Override
	public List<User> getUsers() {
		return (List<User>) hibernateTemplate.find("from User u");
	}
	
	//使用load容易出现懒加载问题，可以在web.xml里面加OpenSessionInViewFilter的拦截器，这样hibernate的session在到jsp页面时就没有关闭，使用标签取值时才能取到
	//也可以使用get方法直接将结果取出到内存中，这样的缺点是如果存在关联就取值太多
	@Override
	public User loadById(int id) {
		return (User) this.hibernateTemplate.load(User.class, id);
	}


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	@Resource(name="hibernateTemplate")//由spring自动注入beans.xml中的相应id/name的bean到批注有@Resource(name="")的set方法中
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
