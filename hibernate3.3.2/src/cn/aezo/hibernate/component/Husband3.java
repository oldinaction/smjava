package cn.aezo.hibernate.component;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//组件映射

@Entity
public class Husband3 {
	private int id;
	private String name;
	private Wife3 wife3;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Embedded
	public Wife3 getWife3() {
		return wife3;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setWife3(Wife3 wife3) {
		this.wife3 = wife3;
	}
	
}
