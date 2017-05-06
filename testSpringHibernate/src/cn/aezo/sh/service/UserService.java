package cn.aezo.sh.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.aezo.sh.dao.LogDAO;
import cn.aezo.sh.dao.UserDAO;
import cn.aezo.sh.model.Log;
import cn.aezo.sh.model.User;

//对外的一个服务，在此处控制抽象的UserDAO来完成业务
@Component("userService")
public class UserService {
	private UserDAO userDAO;
	private LogDAO logDAO;
	
	//利用了AOP，在方法的首尾加入开始事物和提交事物；声明式事物加在服务层，如果logDAO.save(log)抛出运行时异常，则事物回滚，userDAO.save(user)就不会存入数据到数据库。
	//propagation=Propagation.REQUIRED表示调用add方法前如果有transaction则直接将此业务加到该transaction中，否则重新创建一个事物。
	//@Transactional(propagation=Propagation.REQUIRED)//一般使用xml方式来进行事物声明
	public void add(User user) {
		userDAO.save(user);
		Log log = new Log();
		log.setMsg("a user saved!");
		logDAO.save(log);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	@Resource(name="u")
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public LogDAO getLogDAO() {
		return logDAO;
	}
	@Resource
	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}
	
}
