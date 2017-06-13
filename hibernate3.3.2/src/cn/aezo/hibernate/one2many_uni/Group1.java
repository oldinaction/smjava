package cn.aezo.hibernate.one2many_uni;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//一对多

@Entity
@Table(name="t_group1")
public class Group1 {
	private int id;
	private String name;
	private Set<User1> users = new HashSet<User1>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@OneToMany
	@JoinColumn(name="groupId")//如果不定义外键名的话，则会产生中间表
	public Set<User1> getUsers() {
		return users;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUsers(Set<User1> users) {
		this.users = users;
	}
	
	
}
