package cn.aezo.tag;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 集合的遍历(业务的自定义标签示例，和c:foreEach类似)
 * <BR>
 * LoopTag <BR>
 * @author oldinaction
 * @date 2014年11月30日-下午12:37:04
 * @version 1.0.0
 *
 */
public class LoopTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	/**标签解析
	 * <c:foreEach var="" items=""> <!-- 标签的开始-->
	 * 		<!-- 标签的主体 -->
	 * </c:foreEach> <!-- 标签的结束 -->
	 */
	//在标签里面定义的属性(如<c:foreEach var="" items="">中的var和item就是标签属性)一定要生成set方法
	private String var;
	private Object items;
	
	private Iterator iterator; //定义一个集合的迭代器
	private int index = 0; //定义一个循环的索引
	
	/**
	 * 标签开始时调用的方法
	 * 有两个固定的返回值 SKIP_BODY,EVAL_BODY_INCLUDE
	 * SKIP_BODY:忽略标签的主题内容（标签体）
	 * EVAL_BODY_INCLUDE:要去容器执行主体标签内容
	 */
	@Override
	public int doStartTag() throws JspException {
		if(items == null){
			return SKIP_BODY; //直接忽略标签体的执行
		}
		iterator = ((Collection)items).iterator(); //初始化迭代器
		if(iterator.hasNext()){ //如果集合中有数据
			this.pageContext.setAttribute(var, iterator.next());
			this.pageContext.setAttribute(var + "_index", index);
			return EVAL_BODY_INCLUDE; //要去容器执行主体标签内容（标签体）	
		}else{
			return SKIP_BODY;//忽略标签的主题内容（标签体）
		}
	}
	
	/**
	 * 标签循环的时候调用的方法(主体)
	 * 有两个固定的返回值 SKIP_BODY,EVAL_BODY_AGAIN
	 * SKIP_BODY:忽略标签的主题内容（标签体）
	 * EVAL_BODY_AGAIN：重复执行标签体重的内容
	 */
	@Override
	public int doAfterBody() throws JspException {
		if(iterator.hasNext()){ //如果集合中有数据
			index ++;
			this.pageContext.setAttribute(var, iterator.next());
			this.pageContext.setAttribute(var+"_index", index);
			return EVAL_BODY_AGAIN; //要求容器重复执行标签主体内容
		}else{
			index = 0;
			return SKIP_BODY; //忽略标签的主体内容（标签体）
		}
	}
	
	/**
	 * 标签结束时调用的方法
	 * 有两个固定的返回值 EVAL_PAGE,SKIP_PAGE(慎用)
	 * EVAL_PAGE:继续运行标签后面的页面内容
	 * SKIP_PAGE:忽略标签结束后面的页面内容(可能一些html标签或者内容都被忽略)
	 */
	@Override
	public int doEndTag() throws JspException {
		index = 0;
		iterator = null;
		var = null;
		return EVAL_PAGE;
	}

	//标签结束以后释放资源
	@Override
	public void release() {
		index = 0;
		iterator = null;
		var = null;
	}
	
	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}
	
}
