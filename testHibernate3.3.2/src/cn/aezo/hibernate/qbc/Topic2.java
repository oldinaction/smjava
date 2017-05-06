package cn.aezo.hibernate.qbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries(
		{
			@NamedQuery(name="topic.selectCertainTopic", query="from Topic t where t.id = :id")
		}
		)
		/*
@NamedNativeQueries(
		{
			@NamedNativeQuery(name="topic.select2_5Topic", query="select * from topic limit 2, 5")
		}
		)
		*/

public class Topic2 {
	private int id;
	private String title;
	private Category2 category;
	private Date createDate;
	private List<Msg2> msgs = new ArrayList<Msg2>();
	@OneToMany(mappedBy="topic")
	public List<Msg2> getMsgs() {
		
		return msgs;
	}
	public void setMsgs(List<Msg2> msgs) {
		this.msgs = msgs;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	public Category2 getCategory() {
		return category;
	}
	public void setCategory(Category2 category) {
		this.category = category;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
