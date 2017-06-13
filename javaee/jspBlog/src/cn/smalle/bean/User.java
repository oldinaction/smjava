package cn.smalle.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 对应表blog_user的bean(javaBean就相当于一张数据表)
 * <BR>
 * User <BR>
 * @author oldinaction
 * @date 2014年11月23日-上午11:10:37
 * @version 1.0.0
 *
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String username;
	private String password;
	private String desciption;
	private String headpic;
	private Date createtime;
	private Date updatetime;
	
	public User(){
		
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public String getHeadpic() {
		return headpic;
	}
	public void setHeadpic(String headpic) {
		this.headpic = headpic;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}
