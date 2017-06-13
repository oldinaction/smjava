package cn.aezo.spring.base.annotation.dao.impl;

import cn.aezo.spring.base.annotation.dao.UserDAO;
import cn.aezo.spring.base.annotation.model.User;
import org.springframework.stereotype.Component;

//UserDAO的一个现实
@Component("u1")//不写value="u1"，则默认生成的这个bean的id/name的值就是首字母小写的类名(userDaoImpl)
public class UserDAOImpl implements UserDAO {

	@Override
	public void save(User user) {
		System.out.println("a user saved!");
		
		//throw new RuntimeException("error!");//测试@AfterThrowing
	}

}
