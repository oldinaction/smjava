package cn.aezo.hibernate.one2one_uni_fk_composite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

//一对一的单向联合主键的外键关联

@Entity
@IdClass(Wife2.class)
public class Husband2 {
	private int id;
	private String name;
	private Wife2 wife2;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@Id
	public String getName() {
		return name;
	}
	
	@OneToOne
	@JoinColumns({
		@JoinColumn(name="wife2Id", referencedColumnName="id"),
		@JoinColumn(name="wife2Name", referencedColumnName="name")
	})
	public Wife2 getWife2() {
		return wife2;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setWife2(Wife2 wife2) {
		this.wife2 = wife2;
	}
	
}
