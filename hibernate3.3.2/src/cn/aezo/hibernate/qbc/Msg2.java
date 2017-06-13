package cn.aezo.hibernate.qbc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Msg2 {
	private int id;
	private String cont;
	private Topic2 topic;
	@ManyToOne
	public Topic2 getTopic() {
		return topic;
	}
	public void setTopic(Topic2 topic) {
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
