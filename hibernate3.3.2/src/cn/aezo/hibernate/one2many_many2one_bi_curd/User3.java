package cn.aezo.hibernate.one2many_many2one_bi_curd;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//一对多、多对一双向关联的增删查改

@Entity
@Table(name="t_user3")
public class User3 {
	private Group3 group;
	private int id;
	private String name;
	
	@ManyToOne(cascade={CascadeType.ALL})//cascade=CascadeType.ALL表示存储user表时把与他相关联的表也存储，否则需要自己先手动存储关联的那个表
	public Group3 getGroup() {
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
	
	public void setGroup(Group3 group) {
		this.group = group;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
