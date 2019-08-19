package cn.aezo.hibernate.one2one_bi_fk;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//一对一的双向外键关联，使用@0ne20ne(mappedBy="另一个类里定义的属性名")

@Entity
public class Wife1 {
	private int id;
	private String name;
	private Husband1 husband1;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@OneToOne(mappedBy="wife1")//此处表示Husband中对"getWife"中的wife字段设置的外键是主导。双向关系必须指明
	public Husband1 getHusband1() {
		return husband1;
	}
	
	public void setHusband1(Husband1 husband1) {
		this.husband1 = husband1;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
