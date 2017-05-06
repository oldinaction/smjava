package cn.aezo.hibernate.collections_mapping;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//集合映射

@Entity
@Table(name="t_group4")
public class Group4 {
	private int id;
	private String name;
	//private Set<User4> users = new HashSet<User4>();//Set较常用
	//private List<User4> users = new ArrayList<User4>();//List
	private Map<Integer, User4> users = new HashMap<Integer, User4>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy="group",cascade={CascadeType.ALL})
	@MapKey(name="id")//以users中user的id作为map的key
	public Map<Integer, User4> getUsers() {
		return users;
	}

	public void setUsers(Map<Integer, User4> users) {
		this.users = users;
	}
	
	/*
	@OneToMany(mappedBy="group",cascade={CascadeType.ALL})
	@OrderBy(value="name ASC")//users中的name属性升序排列
	public List<User4> getUsers() {
		return users;
	}
	public void setUsers(List<User4> users) {
		this.users = users;
	}
	*/
	
	/*	
	@OneToMany(
			mappedBy="group",
			cascade={CascadeType.ALL}//cascade=CascadeType.ALL表示存储user表时把与他相关联的表也存储，否则需要自己先手动存储关联的那个表
			//,fetch=FetchType.EAGER//取一对多时，默认只会取出一不会取出多,即fetch默认是lazy，此时设置了eager则会在取组的同时取出用户信息。一般不这么用	
		)//cascade设定CUD，fetch设定R
	public Set<User4> getUsers() {
		return users;
	}
	public void setUsers(Set<User4> users) {
		this.users = users;
	}
	*/
	
}
