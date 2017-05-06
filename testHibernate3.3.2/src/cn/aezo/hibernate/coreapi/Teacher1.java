package cn.aezo.hibernate.coreapi;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="_teacher1")
public class Teacher1 {
	private int id;//私有属性，不适合在此处做批注
	private String name;	
	//private TeacherPK pk;//定义id、name的联合主键类
	private String title;
	private Date birthDate;
	private ZhiChen1 zhiChen1;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="_title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@Enumerated(value=EnumType.STRING)
	public ZhiChen1 getZhiChen1() {
		return zhiChen1;
	}

	public void setZhiChen1(ZhiChen1 zhiChen1) {
		this.zhiChen1 = zhiChen1;
	}

	/*
	@EmbeddedId
	public TeacherPK getPk() {
		return pk;
	}

	public void setPk(TeacherPK pk) {
		this.pk = pk;
	}
	*/

}
