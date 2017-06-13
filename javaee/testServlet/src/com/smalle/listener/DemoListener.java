package com.smalle.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DemoListener implements ServletContextListener {

	private ServletContext application = null;
	
	//应用上下文初始时会回调的方法
	@Override
	public void contextInitialized(ServletContextEvent e) {
	    //初始化一个application对象
System.out.println("DemoListener===启动服务器后，ServletContext就会初始化");
	    application = e.getServletContext();
	    this.application.setAttribute("key", "可以在其他地方调用到我");
	}
	
	public void contextDestroyed(ServletContextEvent e) {}
}
