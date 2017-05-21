package cn.aezo.hibernate.one2one_uni_fk_composite;

import javax.persistence.Column;
import java.io.Serializable;

public class Wife2PK implements Serializable {
	private int id;
	private String name;
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Wife2PK) {
			Wife2PK wife2 = (Wife2PK) obj;
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

	public int getId() {
		return id;
	}

	@Column(length=100) // 联合主键的长度不能超过256个字节(必须写在这个类里面)
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
