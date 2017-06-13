package cn.aezo.hibernate.one2many_many2one_bi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//一对多、多对一双向关联

@Entity
@Table(name="t_user2")
public class User2 {
	private Group2 group;
	private int id;
	private String name;
	
	@ManyToOne
	public Group2 getGroup() {
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
	
	public void setGroup(Group2 group) {
		this.group = group;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
