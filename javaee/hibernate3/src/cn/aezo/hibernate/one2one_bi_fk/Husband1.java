package cn.aezo.hibernate.one2one_bi_fk;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//一对一的双向外键关联，在被约束表字段的get方法上加@0ne20ne

@Entity
public class Husband1 {
	private int id;
	private String name;
	private Wife1 wife1;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@OneToOne
	@JoinColumn(name="wife1Id")//指定生成的数据库字段名，否则默认生成外键名为wife_id
	public Wife1 getWife1() {
		return wife1;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWife1(Wife1 wife1) {
		this.wife1 = wife1;
	}
	
}
