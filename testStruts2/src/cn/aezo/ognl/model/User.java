package cn.aezo.ognl.model;

public class User {
	
	private String username;
	private String password;
	private int age = 18;
	
	//使用Domain Model时，在url地址中传user.username=smalle的话，Struts2会自动根据User类中无参构造方法帮忙new一个对象
	//所以此时如果我们有自己的构造方法，则系统不会帮我们自动生成无参构造方法，则一定要自己写上这个无参构造方法供Struts2调用
	public User() {
		System.out.println("user constructor!");
	}
	
	public User(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "user" + age;
	}
	
	
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

}
