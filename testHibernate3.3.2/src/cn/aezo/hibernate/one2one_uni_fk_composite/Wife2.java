package cn.aezo.hibernate.one2one_uni_fk_composite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Wife2 implements Serializable {
	private int id;
	private String name;
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Wife2) {
			Wife2 wife2 = (Wife2) obj;
			if(this.id == wife2.getId() && this.name.equals(wife2.getName())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@Column(length=100)//联合主键的长度不能超过256个字节
	public String getName() {
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
