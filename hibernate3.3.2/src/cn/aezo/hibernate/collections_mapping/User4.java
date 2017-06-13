package cn.aezo.hibernate.collections_mapping;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//集合映射

@Entity
@Table(name="t_user4")
public class User4 {
	private Group4 group;
	private int id;
	private String name;
	
	@ManyToOne(cascade={CascadeType.ALL})//cascade=CascadeType.ALL表示存储user表时把与他相关联的表也存储，否则需要自己先手动存储关联的那个表
	public Group4 getGroup() {
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
	
	public void setGroup(Group4 group) {
		this.group = group;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
