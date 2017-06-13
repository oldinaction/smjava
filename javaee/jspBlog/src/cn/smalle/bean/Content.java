package cn.smalle.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 对应表blog_content
 * <BR>
 * Comment <BR>
 * @author oldinaction
 * @date 2014年11月23日-上午11:13:27
 * @version 1.0.0
 *
 */
public class Content implements Serializable{

	private static final long serialVersionUID = 1L;
	//主键id
	private Integer id;
	//标题
	private String title;
	//内容
	private String content;
	//作者id
	private Integer userId;
	//标签
	private String tag;
	//创建时间
	private Date createtime;
	//更新时间
	private Date updatetime;
	// 发布状态 1发布 0未发布
	private Integer status;
	// 删除状态 1删除 0未删除
	private Integer isDelete;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
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
	
	public void setUpdatetime(Date updatatime) {
		this.updatetime = updatatime;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}


}
