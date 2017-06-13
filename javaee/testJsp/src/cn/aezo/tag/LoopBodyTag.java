package cn.aezo.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LoopBodyTag extends BodyTagSupport{
	private static final long serialVersionUID = 1L;
	
	private Integer count = 1; //定义循环几次
	private Integer flag = 1; //定义一个是否在后面增加字符串

	@Override
	public void doInitBody() throws JspException {
		//在运行doAfterBody之前运行的方法。可以起到初始化等作用。或者调用某个页面的处理方法
		try {
			if(flag >= 1) {
				this.bodyContent.append("z"); //此处获取的bodyContent是不包含页面标签中的内容
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int doAfterBody() throws JspException {
		if(count > 0) {		
			String str = this.getBodyContent().getString();//获取标签体的内容,要是getString
			try {
				if(null != str) {
					this.getPreviousOut().write(str);
				}
				this.bodyContent.clearBody(); //清楚标签体的主体内容
			} catch (IOException e) {
				e.printStackTrace();
			}
			count --;
			return EVAL_BODY_AGAIN;
		} else {
			count = 1;
			return EVAL_PAGE;
		}	
	}
	
	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getFlag() {
		return flag;
	}
	
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}
