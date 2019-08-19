package cn.aezo.ognl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.aezo.ognl.model.Cat;
import cn.aezo.ognl.model.Dog;
import cn.aezo.ognl.model.User;

import com.opensymphony.xwork2.ActionSupport;

public class OgnlAction extends ActionSupport {

	private String name;
	private User user;
	private Cat cat;
	//Struts2会自动new OgnlAction,也会根据情况帮我们new Domain Model,但下面的就需要自己实例化
	private List<User> users = new ArrayList<User>();
	private Set<Dog> dogs = new HashSet<Dog>();
	private Map<String, User> userMaps = new HashMap<String, User>();

	public OgnlAction() {
		users.add(new User(1));
		users.add(new User(1));
		users.add(new User(2));
		users.add(new User(2));
		users.add(new User(3));
		

		dogs.add(new Dog("dog1"));
		dogs.add(new Dog("dog2"));
		dogs.add(new Dog("dog3"));

		userMaps.put("userm1", new User(4));
		userMaps.put("userm2", new User(5));
		userMaps.put("userm3", new User(6));
	}

	public String execute() {
		return "success";
	}

	public String func() {
		return "ActionSupport method";
	}

	public String getName() {
		return name;
	}

	public User getUser() {
		return user;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Set<Dog> getDogs() {
		return dogs;
	}

	public void setDogs(Set<Dog> dogs) {
		this.dogs = dogs;
	}

	public Map<String, User> getUserMaps() {
		return userMaps;
	}

	public void setUserMaps(Map<String, User> userMaps) {
		this.userMaps = userMaps;
	}

}
