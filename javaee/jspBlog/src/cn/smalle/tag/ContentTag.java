package cn.smalle.tag;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import cn.smalle.bean.Content;
import cn.smalle.dao.content.ContentDao;

/**
 * 业务自定义标签(把从表中获取数据的业务放在这里循环遍历，在页面上渲染的时候就不需要循环了)
 * <BR>
 * ContentTag <BR>
 * @author oldinaction
 * @date 2014年11月30日-下午2:04:10
 * @version 1.0.0
 *
 */
public class ContentTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	
	private Integer start;
	private Integer size;
	private Integer sortFlag;
	private String var;
	
	private Iterator<Content> iterator;
	private int index = 0;
	//标签开始时调用的方法
	public int doStartTag() throws JspException {
		//查询内容表中的数据
		//设置下面的start、size、sort更符合人的思维
		if(start == null || start <= 1){
			start = 0;
		}else{
			start --;
		}
		if(size == null) size = 10;
		String sort = "desc"; 
		if(sortFlag != null && sortFlag == 1) sort = "asc";
		//业务发生了变化
		List<Content> contents = ContentDao.findContents(start, size, sort);
		if(contents!=null && contents.size()>0){
			iterator = contents.iterator();//初始迭代器
			if(iterator.hasNext()){//如果你的集合中有数据
				this.pageContext.setAttribute(var, iterator.next());
				this.pageContext.setAttribute(var+"_index", index);
				return EVAL_BODY_INCLUDE;//要去容器执行主体标签内容（标签体）
				
			}else{
				return SKIP_BODY;//忽略标签的主题内容（标签体）
			}
		}else{
			return SKIP_BODY;//忽略标签的主题内容（标签体）
		}
		//有两个固定的返回值 SKIP_BODY,EVAL_BODY_INCLUDE
		//SKIP_BODY:忽略标签的主题内容（标签体）默认值
		//EVAL_BODY_INCLUDE:要去容器执行主体标签内容
	}
	
	//标签循环的时候调用的方法(主题)
	public int doAfterBody() throws JspException {
		if(iterator.hasNext()){//如果你的集合中有数据
			index++;
			this.pageContext.setAttribute(var, iterator.next());
			this.pageContext.setAttribute(var+"_index", index);
			return EVAL_BODY_AGAIN;//要求容器重复执行标签主体内容
		}else{
			index = 0;
			return SKIP_BODY;//忽略标签的主题内容（标签体）,往标签的下面
		}
		
		//有两个固定的返回值 SKIP_BODY,EVAL_BODY_AGAIN
		//SKIP_BODY:忽略标签的主题内容（标签体）
		//EVAL_BODY_AGAIN：重复执行标签体重的内容
	}
	

	public int doEndTag() throws JspException {
		iterator = null;
		var = null;
		index = 0;
		//有两个固定的返回值 EVAL_PAGE,SKIP_PAGE
		//EVAL_PAGE:继续运行标签后面的内容 =====默认值
		//SKIP_PAGE:忽略标签结束后面的内容
		return EVAL_PAGE;
	}

	//标签结束以后释放资源
	public void release() {
		iterator = null;
		var = null;
		index = 0;
	}
	
	public int getStrat() {
		return start;
	}

	public void setStrat(int strat) {
		this.start = strat;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Integer getSort() {
		return sortFlag;
	}

	public void setSort(Integer sort) {
		this.sortFlag = sort;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}
	
}
