package cn.aezo.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 小写转大写（一般使用函数的自定义标签，此时只是单标签的示例）
 * <BR>
 * ToUpperTag <BR>
 * @author oldinaction
 * @date 2014年12月1日-下午7:47:05
 * @version 1.0.0
 *
 */
public class ToUpperTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	private String str;

	/**
	 * 但是单标签(没有标签体)对应的类，所以没有doAfterBody等方法
	 */
	@Override
	public int doStartTag() throws JspException {
		String string = str.toUpperCase();
		//直接写出到页面
		try {
			this.pageContext.getOut().write(string);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//放到作用域中，再到页面调用（推荐）
		this.pageContext.setAttribute("string", string);
		return EVAL_PAGE;
	}
	
	@Override
	public void release() {
	}
	
	public String getStr() {
		return str;
	}
	
	public void setStr(String str) {
		this.str = str;
	}
}
