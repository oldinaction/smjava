package com.smalle.listener;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineListener implements 
	ServletContextListener, HttpSessionAttributeListener, HttpSessionListener {
	
	private ServletContext application = null;

	//应用上下文初始时会回调的方法
	@Override
    public void contextInitialized(ServletContextEvent e) {
        //初始化一个application对象
        application = e.getServletContext();
        //设置一个列表属性，用于保存在线用户名
        this.application.setAttribute("online", new LinkedList<String>());
    }
	
	//往会话中添加属性时的回调方法
	@Override
    public void attributeAdded(HttpSessionBindingEvent e) {
        //取得用户名列表
        List<String> onlines = (List<String>) this.application.getAttribute("online");
        if("username".equals(e.getName())){
            onlines.add((String) e.getValue());
        }
        //将添加后的列表重新设置列application属性中.
        this.application.setAttribute("online", onlines);
    }

    //会话销毁时会回调的方法
	@Override
    public void sessionDestroyed(HttpSessionEvent e) {
        //取得用户名列表
        List<String> onlines = (List<String>) this.application.getAttribute("online");
        //取得当前用户名
        String username = (String) e.getSession().getAttribute("username");
        //将此用户从列表中删除
        onlines.remove(username);
        //讲删除后的列表重新设置到application属性中.
        this.application.setAttribute("online", onlines);
    }
	
	public void sessionCreated(HttpSessionEvent e) {}
	public void attributeRemoved(HttpSessionBindingEvent e) {}
	public void attributeReplaced(HttpSessionBindingEvent e) {}
	public void contextDestroyed(ServletContextEvent e) {}

}


