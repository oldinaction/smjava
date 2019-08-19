package cn.aezo.hibernate.component;

import javax.persistence.Entity;

@Entity
public class Wife3 {
	private String wifeName;
	private int age;
	
	public String getWifeName() {
		return wifeName;
	}
	
	public void setWifeName(String wifeName) {
		this.wifeName = wifeName;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
}
