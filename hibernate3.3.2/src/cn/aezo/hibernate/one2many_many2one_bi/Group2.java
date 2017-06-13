package cn.aezo.hibernate.one2many_many2one_bi;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//一对多、多对一双向关联

@Entity
@Table(name="t_group2")
public class Group2 {
	private int id;
	private String name;
	private Set<User2> users = new HashSet<User2>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@OneToMany(mappedBy="group")
	public Set<User2> getUsers() {
		return users;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUsers(Set<User2> users) {
		this.users = users;
	}
	
	
}
