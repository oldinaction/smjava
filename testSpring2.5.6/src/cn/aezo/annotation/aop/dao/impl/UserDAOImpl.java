package cn.aezo.annotation.aop.dao.impl;

import org.springframework.stereotype.Component;

import cn.aezo.annotation.aop.dao.UserDAO;
import cn.aezo.annotation.aop.model.User;

//UserDAO的一个现实
@Component("u1")//不写value="u1"，则默认生成的这个bean的id/name的值就是首字母小写的类名(userDaoImpl)
public class UserDAOImpl implements UserDAO {

	@Override
	public void save(User user) {
		System.out.println("a user saved!");
		
		//throw new RuntimeException("error!");//测试@AfterThrowing
	}

}
