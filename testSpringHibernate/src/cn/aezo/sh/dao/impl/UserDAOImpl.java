package cn.aezo.sh.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.aezo.sh.dao.UserDAO;
import cn.aezo.sh.model.User;

//UserDAO的一个现实
@Component("u")
public class UserDAOImpl implements UserDAO {
	private SessionFactory sessionFactory;
	
	@Override
	public void save(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name="mySessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
