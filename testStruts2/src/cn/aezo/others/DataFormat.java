package cn.aezo.others;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DataFormat extends ActionSupport {

	private int id;
	private Date date;
	private List<String> interests;
	private Map<String, String> map;
	
	
	public String execute() {
		return SUCCESS;
	}

	public String myexception() throws Exception {
		if(id < 0) {
			throw(new Exception());
		}
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}
