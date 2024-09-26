package cn.aezo.smjava.proguard.entity;

public class User {
	private String name = "张三";
	private int age = 18;
	private String sex = "男";

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	private String information(){		
		return "姓名："+name+", 年龄："+age+", 性别:"+sex;
	}
}
