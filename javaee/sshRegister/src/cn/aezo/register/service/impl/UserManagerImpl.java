package cn.aezo.register.service.impl;

import cn.aezo.register.dao.LogDAO;
import cn.aezo.register.dao.UserDao;
import cn.aezo.register.model.Log;
import cn.aezo.register.model.User;
import cn.aezo.register.service.UserManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("userManager")
public class UserManagerImpl implements UserManager {
	private UserDao userDao;
	private LogDAO logDAO;
	
	public UserDao getUserDao() {
		return userDao;
	}

	@Resource//默认取找set方法名去掉set后首字母小写的那个变量名(userDao)
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public LogDAO getLogDAO() {
		return logDAO;
	}

	@Resource
	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}

	@Override
	public boolean exists(User user) throws Exception {
		return userDao.checkUserExistsWithName(user.getUsername());
	}

	//利用了AOP，在方法的首尾加入开始事物和提交事物；声明式事物加在服务层，如果logDAO.save(log)抛出运行时异常，则事物回滚，userDAO.save(user)就不会存入数据到数据库。
	//propagation=Propagation.REQUIRED表示调用add方法前如果有transaction则直接将此业务加到该transaction中，否则重新创建一个事物。
	//@Transactional(propagation=Propagation.REQUIRED)//一般使用xml方式来进行事物声明
	@Override
	public void add(User user) throws Exception {
		userDao.save(user);

		Log log = new Log();
		log.setMsg("a user saved!");
		logDAO.save(log);
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
