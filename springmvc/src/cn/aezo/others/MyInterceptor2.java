package cn.aezo.others;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//定义springmvc拦截器
public class MyInterceptor2 implements HandlerInterceptor {

	@Override//该方法在controller执行前执行，可以实现对数据的预处理，比如：编码、安全控制等。
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		System.out.println("preHandle...2");
		return true;
	}
	
	@Override//该方法在controller执行后，生成视图前执行。在这里，我们有机会修改视图层数据。
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("postHandle...2");
	}
	
	@Override//最后执行，通常用于释放资源，处理异常。
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion...2");
	}

}
