package cn.aezo.hibernate.many2one_uni;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//多对一

@Entity
@Table(name="t_user")
public class User {
	private Group group;
	private int id;
	private String name;
	
	@ManyToOne
	public Group getGroup() {
		return group;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
