package cn.aezo.hibernate.tree;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Org {
	private Set<Org> children = new HashSet<Org>();
	private int id;
	private String name;
	private Org parent;
	
	@OneToMany(
		mappedBy="parent",
		cascade={CascadeType.ALL}
		//,fetch=FetchType.EAGER//只适合小级别的树，同时取出所有的，打印就可以打印在一起了；否则就在需要的时候发起sql语句
	)
	public Set<Org> getChildren() {
		return children;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@ManyToOne
	@JoinColumn(name="parent_id")//只需写在关联处即可，所有也可写在@OneToMany的下面
	public Org getParent() {
		return parent;
	}
	
	public void setChildren(Set<Org> children) {
		this.children = children;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setParent(Org parent) {
		this.parent = parent;
	}
	
}
