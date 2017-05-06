package cn.aezo.sh.dao;

import cn.aezo.sh.model.User;

//UserDAO：主要用来调用model，并将数据写入到数据库。定义一个接口，如果使用不同的数据库就可以写不同的实现
public interface UserDAO {
	public void save(User user);
}
