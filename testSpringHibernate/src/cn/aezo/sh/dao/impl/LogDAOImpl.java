package cn.aezo.sh.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.aezo.sh.dao.LogDAO;
import cn.aezo.sh.model.Log;

@Component("logDAO")
public class LogDAOImpl implements LogDAO {
	//private SessionFactory sessionFactory;
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(Log log) {
		//Session session = sessionFactory.getCurrentSession();//原本要写try catch的，使用hibernateTemplate就交由他去处理了
		//session.save(log);
		
		hibernateTemplate.save(log);
		
		//throw new RuntimeException("error!");//@@Transactional声明式事物，如果logDAO.save(log)抛出运行时异常，则事物回滚，userDAO.save(user)就不会存入数据到数据库。
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/*	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name="mySessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	*/
	
}
