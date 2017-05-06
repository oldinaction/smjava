package cn.aezo.hibernate.one2one_uni_fk;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//一对一的单向外键关联，在被约束表字段的get方法上加@0ne20ne @JoinColumn

@Entity
public class Husband {
	private int id;
	private String name;
	private Wife wife;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@OneToOne
	@JoinColumn(name="wifeId")//指定生成的数据库字段名，否则默认生成外键名为wife_id
	public Wife getWife() {
		return wife;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWife(Wife wife) {
		this.wife = wife;
	}
	
}
