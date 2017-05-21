package cn.aezo.hibernate.hello;

import javax.persistence.Column;
import java.io.Serializable;

//当使用联合主键时,xml方式需要将联合主键定义为一个类
//必须实现Serializable接口，且重写equals方法和hashcode方法
//Serializable主键需要传递;equals靠主键区分pk，所以必须;hashCode()根据pk的哈希码去查找，所以必须
public class StudentPK implements Serializable {
	//将id、name作为联合主键
	private int id;
	private String name;

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof StudentPK) {
			StudentPK pk = (StudentPK) obj;
			if(this.id == pk.getId() && this.name.equals(pk.getName())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(length=100)//联合主键的长度不能超过256个字节(必须写在这个类里面)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
