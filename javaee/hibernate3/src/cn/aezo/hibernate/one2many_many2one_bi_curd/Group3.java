package cn.aezo.hibernate.one2many_many2one_bi_curd;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//一对多、多对一双向关联的增删查改

@Entity
@Table(name="t_group3")
public class Group3 {
	private int id;
	private String name;
	private Set<User3> users = new HashSet<User3>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@OneToMany(
		mappedBy="group",
		cascade={CascadeType.ALL}//cascade=CascadeType.ALL表示存储user表时把与他相关联的表也存储，否则需要自己先手动存储关联的那个表
		//,fetch=FetchType.EAGER//取一对多时，默认只会取出一不会取出多,即fetch默认是lazy，此时设置了eager则会在取组的同时取出用户信息。一般不这么用	
	)//cascade设定CUD，fetch设定R
	public Set<User3> getUsers() {
		return users;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUsers(Set<User3> users) {
		this.users = users;
	}
	
	
}
