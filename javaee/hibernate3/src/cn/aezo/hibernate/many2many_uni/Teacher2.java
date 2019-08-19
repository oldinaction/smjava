package cn.aezo.hibernate.many2many_uni;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Teacher2 {
	private int id;
	private String name;
	private Set<Student2> student2s = new HashSet<Student2>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	@ManyToMany//多对多关联 Teacher是主的一方 Student是附属的一方 
	@JoinTable(
			joinColumns={@JoinColumn(name="teacherId")},//本类主键在中间表生成的对应字段名
			inverseJoinColumns={@JoinColumn(name="student2Id")}//对方类主键在中间表生成的对应字段名
	)
	public Set<Student2> getStudent2s() {
		return student2s;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStudent2s(Set<Student2> student2s) {
		this.student2s = student2s;
	}
	
	
}
