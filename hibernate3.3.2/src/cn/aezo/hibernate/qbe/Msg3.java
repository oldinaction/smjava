package cn.aezo.hibernate.qbe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Msg3 {
	private int id;
	private String cont;
	private Topic3 topic;
	@ManyToOne
	public Topic3 getTopic() {
		return topic;
	}
	public void setTopic(Topic3 topic) {
		this.topic = topic;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	
}
